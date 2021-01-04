package com.shaohuashuwu.test;

import com.shaohuashuwu.util.SensitivewordFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSenditive {

    @Autowired
    SensitivewordFilter sensitivewordFilter;

    @Test
    public void testUserNum(){

        String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动G点刺激棒就流泪，"
                + "FL功难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯A片红酒一G点刺激棒G点刺激棒G点刺激棒G点刺激棒部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";

        System.out.println("数量"+sensitivewordFilter.sensitiveWordNum(string)
        );
    }

}
