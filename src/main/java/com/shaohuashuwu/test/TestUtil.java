package com.shaohuashuwu.test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaohuashuwu.util.IdWorker;
import com.shaohuashuwu.util.ThisCurrentTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Currency;

import static java.lang.Character.getType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestUtil {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ThisCurrentTime thisCurrentTime;

    @Test
    public void testIdworker(){
        System.out.println(idWorker.nextId());
        int a = 1;
        System.out.println(getType(idWorker.nextId()));
    }
    public static String getType(Object test) {
        return test.getClass().getName().toString();

    }


    @Test
    public void testCurrentTime(){
        System.out.println("获取当前时间"+thisCurrentTime.currentTime());
//        thisCurrentTime.currentTime();
    }

    @Test
    public void testCurrentTime2(){
        System.out.println("获取当前时间"+thisCurrentTime.currentMonthTime());
//        thisCurrentTime.currentTime();
    }


}
