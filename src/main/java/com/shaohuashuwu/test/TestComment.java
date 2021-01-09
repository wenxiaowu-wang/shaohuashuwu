package com.shaohuashuwu.test;


import com.shaohuashuwu.dao.CommentInfoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.WorksInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestComment {

    @Autowired
    private CommentInfoDao commentInfoDao;

    @Test
    public void test001(){

        System.out.println(commentInfoDao.selectCommentChildInfoByWorkId(76));
    }
}
