package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.*;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import com.shaohuashuwu.domain.vo.IsChapterHaveVo;
import com.shaohuashuwu.service.ChapterInfoService;
import com.shaohuashuwu.util.ThisCurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("chapterInfoService")
public class ChapterInfoServiceImpl implements ChapterInfoService {

    @Autowired
    public ChapterInfoDao chapterInfoDao;
    @Autowired
    public ChapterPostInfoDao chapterPostInfoDao;
    @Autowired
    public TransactionInfoDao transactionInfoDao;
    @Autowired
    public ThisCurrentTime thisCurrentTime;
    @Autowired
    public WorksInfoDao worksInfoDao;

    ChapterPostInfo chapterPostInfo;
    ChapterPostInfo nullchapterPostInfo;
    ChapterInfo needchapterInfo;
    WorksInfo worksInfo;


    /**
     * 根据作品id查询最新章节信息
     * 功能点：作品详情时获取最新章节信息，
     * @param work_id
     * @return
     */
    @Override
    public ChapterInfo getnewChapterInfoByword_id(int work_id) {
        return chapterInfoDao.selectnewChapterInfoByword_id(work_id);
    }

    /**
     * 依据章节id查询章节信息
     * 功能点：阅读小说界面获取章节信息
     * @param chapter_id
     * @return
     */
    @Override
    public ChapterInfo getchapterInfoByChapter_id(int chapter_id,int user_id) {



        System.out.println("获取章章节信息--------==="+chapter_id+"用户id======="+user_id);
        //获取章节信息
        needchapterInfo = chapterInfoDao.selectchapterInfoByChapter_id(chapter_id);

        IsChapterHaveVo isChapterHaveVo = new IsChapterHaveVo(chapter_id,user_id);
        //判断小说是否是付费章节

        int needmoney = chapterInfoDao.selectNeedMoneyBychapter_id(chapter_id);
        //付费
        if (needmoney == 1){
            System.out.println("作品付费");
            //判断用户是否订阅
            int issubscribe = transactionInfoDao.selectsubscribeResultBychapterAnduser_id(isChapterHaveVo);

            if (issubscribe == 0){
                System.out.println("用户未付费");
                needchapterInfo.setChapter_content(null);
                needchapterInfo.setChapter_other_word(null);
            }
        }


        return needchapterInfo;
    }


    /**
     *添加章节
     * 功能点：添加章节功能添加章节
     * @param chapterInfo
     * @param work_id
     * @return
     */
    @Override
    public int addchapter_info(ChapterInfo chapterInfo,int work_id,int user_id) {
        //1.先判断work_id是否存在，若不存在创建一个，创建一个chapterpostInfo，chapter_id指向1，user_id指向作者id
        int num = chapterPostInfoDao.selectchapterNum(work_id);
        if(num == 0){
            nullchapterPostInfo = new ChapterPostInfo(user_id,work_id,1);
            chapterPostInfoDao.insertchapter_post_info(nullchapterPostInfo);
        }

        /**
         * 设置chapterInfo,添加chapter_info
         */
        //2.先获取最大的父章节id，为传入的小说设置上个章节
        int chapter_pid_max = 0;
        try {
            chapter_pid_max = chapterPostInfoDao.selectMaxchapter_idBywork_id(work_id);
        }catch (NullPointerException e){
            System.out.println("空");
        }
        //设置上一章节id
        chapterInfo.setChapter_pid(chapter_pid_max);
        chapterInfo.setChapter_time(thisCurrentTime.currentTime());
        //添加章节信息
        int a = chapterInfoDao.insertchapter_info(chapterInfo);

        /**
         *添加chapter_post_info信息
         */
        //先获取chapter_pid数量，如果pidnum!=1表示pid==1，表示这是新建的章节，获取章节id时，需要获取最大章节，
        //如果pidnum==1表示pid!=1，表示已经建立过章节，直接获取pid的id就可以
        int chapter_pidNum = chapterInfoDao.selectchapter_pidNum(chapter_pid_max);
        int maxchapter_id = 1;
        if(chapter_pidNum == 1){
            maxchapter_id = chapterInfoDao.selectChapter_idByChapter_pid(chapter_pid_max);
        }else {
            maxchapter_id = chapterInfoDao.selectMaxChapter_idByChapter_pid(1);
        }
        chapterPostInfo = new ChapterPostInfo(user_id,work_id,maxchapter_id);
        chapterPostInfoDao.insertchapter_post_info(chapterPostInfo);

        //修改作品中作品总字数
        worksInfo = new WorksInfo(work_id,null,null,null,null,null,null,null,null,chapterInfo.getChapter_word_num(),null,null,null,null);
        int updateResult = worksInfoDao.updateWork_word_numByWork_id(worksInfo);


        return a;
    }



    /*
    * 郝振威
    *
    * */

    //根据用户的ID，作品的ID 获取该用户未订阅章节的信息
    @Override
    public List<ChapterInfo> getChapterInfoByUserIdWorkId(int work_id, int user_id) {
        return chapterInfoDao.selectChapterInfoByUserIdWorkId(work_id,user_id);
    }


    //根据用户的ID，作品的ID 获取该用户未订阅章节数量
    @Override
    public int getChapterCountByUserIdWorkId(int work_id) {
        return chapterInfoDao.selectChapterCountByUserIdWorkId(work_id);
    }


    //根据用户的ID，作品的ID 获取该用户已订阅章节的信息
    @Override
    public List<ChapterInfo> getChapterInfoByUserIdWorkId2(int work_id, int user_id) {
        return chapterInfoDao.selectChapterInfoByUserIdWorkId2(work_id,user_id);
    }
}
