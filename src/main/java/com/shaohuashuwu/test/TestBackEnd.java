package com.shaohuashuwu.test;

import com.shaohuashuwu.service.AccountService;
import com.shaohuashuwu.service.AdminInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestBackEnd {
    @Autowired
    private AccountService accountService;

    /**
     * 测试推送 3
     */
    @Test
    public void testFindAll(){
        List list = accountService.findAll();
        System.out.println(list);
    }


    
}
