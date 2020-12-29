package com.shaohuashuwu.test;

import com.shaohuashuwu.dao.ChapterInfoDao;
import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.service.ChapterInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestChapterInfo {

    @Autowired
    ChapterInfoService chapterInfoService;

    @Autowired
    ChapterPostInfoDao chapterPostInfoDao;

//    @Autowired
    ChapterInfo chapterInfo;

    @Test
    public void TestChapterInfoService(){
        chapterInfo = new ChapterInfo(null,null,"东方龙",null,null,null,null,null,null);
        chapterInfoService.addchapter_info(chapterInfo,75,1);
    }

    @Test
    public void TestChapterInfoService2(){
//        chapterInfo = new ChapterInfo(null,null,"东方龙",null,null,null,null,null,null);
//        chapterInfoService.insertchapter_info(chapterInfo,33);
        System.out.println("text输出"+chapterPostInfoDao.selectMaxchapter_idBywork_id(33));
    }


//    @Test
//    public  void Testselectchaptercatalog() throws Exception {
//        System.out.println(chapterInfoService.selectchaptercatalog(1,34));
//    }

//    @Test
//    public void tset1(){
//        System.out.println("----"+chapterInfoService.getchapterInfoByChapter_id(34));
//    }



}
