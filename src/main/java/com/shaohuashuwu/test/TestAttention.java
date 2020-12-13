package com.shaohuashuwu.test;


import com.shaohuashuwu.service.AttentionInfoService;
import com.shaohuashuwu.service.WorksInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAttention {

//    @Autowired
//    private AttentionInfoService attentionInfoService;
//
//    @Test
//    public void tseAttentionInfoService(){
//        int attentionnum = attentionInfoService.selectCountAttentionNum(11);
//        System.out.println("输出数量"+attentionnum);
//    }
}
