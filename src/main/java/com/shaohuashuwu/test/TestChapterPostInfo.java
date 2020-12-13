package com.shaohuashuwu.test;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.ChapterPostInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.ChapterPostInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestChapterPostInfo {

    @Autowired
    ChapterPostInfoService chapterPostInfoService;
    @Autowired
    ChapterPostInfoDao chapterPostInfoDao;

    List<ChapterPostInfo> chapterPostInfoList;
    List<ChapterInfo> chapterInfoList;

//    @Test
//    public void testgetWorkWholeInfoBySelectinput(){
//
//
//        chapterInfoList = chapterPostInfoDao.getTest(9);
//        System.out.println("章节信息"+ chapterInfoList);
//    }

}
