package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.*;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import com.shaohuashuwu.service.ChapterInfoService;
import com.shaohuashuwu.util.ThisCurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public WorksInfoDao worksInfoDao;
    @Autowired
    public TransactionInfoDao transactionInfoDao;



    public ChapterPostInfo chapterPostInfo;
    public ChapterPostInfo nullchapterPostInfo;
    public WorksInfo worksInfo;
    public CatalogInfoVo catalogInfoVo;
    public ChapterInfo chapterInfo;
    public TransactionInfo transactionInfo;

    @Autowired
    public ThisCurrentTime thisCurrentTime;


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
    public ChapterInfo getchapterInfoByChapter_id(int chapter_id) {
        return chapterInfoDao.selectchapterInfoByChapter_id(chapter_id);
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

        return a;
    }

}
