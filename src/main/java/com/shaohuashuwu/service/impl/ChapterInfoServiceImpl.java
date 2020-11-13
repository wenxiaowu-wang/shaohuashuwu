package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterInfoDao;
import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.dao.NoticeInfoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.ChapterPostInfo;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.ChapterInfoService;
import com.shaohuashuwu.util.ThisCurrentTime;
import org.apache.ibatis.annotations.Select;
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



    public ChapterPostInfo chapterPostInfo;
    public ChapterPostInfo nullchapterPostInfo;
    public WorksInfo worksInfo;

    @Autowired
    public ThisCurrentTime thisCurrentTime;

    @Override
    public int insertchapter_info(ChapterInfo chapterInfo,int work_id) {

        System.out.println("-----");
        worksInfo = worksInfoDao.selectworkByid(work_id);

//        先判断work_id是否存在，若不存在创建一个，chapter_id指向1，user_id指向作者id

        int num = chapterPostInfoDao.selectwork_idNum(work_id);
        System.out.println("章节数量："+num);
        if(num == 0){
            nullchapterPostInfo = new ChapterPostInfo(worksInfo.getUser_id(),worksInfo.getWork_id(),1);
            chapterPostInfoDao.insertchapter_post_info(nullchapterPostInfo);

        }




        /**
         * 设置chapterInfo,添加chapter_info
         */
//        1.先获取最大的父章节id，为传入的小说设置上个章节
        int chapter_pid_max = 0;
        try {
            chapter_pid_max = chapterPostInfoDao.selectMaxchapter_idBywork_id(work_id);
            System.out.println("chapter_pid显示："+chapter_pid_max);
        }catch (NullPointerException e){
            System.out.println("空");
        }



        chapterInfo.setChapter_pid(chapter_pid_max);
        chapterInfo.setChapter_time(thisCurrentTime.currentTime());
        int a = chapterInfoDao.insertchapter_info(chapterInfo);
        System.out.println("添加章节Service层"+chapterInfo);
        System.out.println("受影响行数"+a);


        /**
         *添加chapter_post_info信息
         */
//        在此获取刚才添加章节的id

//        先获取chapter_pid数量
        int chapter_pid = chapterInfoDao.selectchapter_pidNum(chapter_pid_max);
        int maxchapter_id = 1;
        if(chapter_pid == 1){
            maxchapter_id = chapterInfoDao.selectChapter_idByChapter_pid(chapter_pid_max);
        }else {
            maxchapter_id = chapterInfoDao.sleectMaxChapter_idByChapter_pid(1);
        }


        System.out.println("maxchapter_id显示："+maxchapter_id);



        System.out.println("work_id:"+worksInfo.getWork_id());
        System.out.println("user_id:"+worksInfo.getUser_id());

        chapterPostInfo = new ChapterPostInfo(worksInfo.getUser_id(),worksInfo.getWork_id(),maxchapter_id);

        System.out.println(chapterPostInfo);
        chapterPostInfoDao.insertchapter_post_info(chapterPostInfo);



        return a;

    }

    //    依据chapter_id查询
    @Override
    public ChapterInfo selectchapterInfoByChapter_id(int chapter_id) {
        return chapterInfoDao.selectchapterInfoByChapter_id(chapter_id);
    }
}
