package com.shaohuashuwu.test;

import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestUser {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void testUserNum(){
        System.out.println("注册人数"+userInfoDao.selectUserNum());
    }

    @Test
    public void tsetLogin(){

        System.out.println("登录信息"+userInfoService.getUserLoginInfo(0));
    }

    @Test
    public void tsetLogin2(){

        System.out.println("登录信息"+userInfoService.getUserInfoByWork_id(76));
    }
}
