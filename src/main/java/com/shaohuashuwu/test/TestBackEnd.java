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
     * 在王洪斌的分支里，为所欲为，改名字
     */
    @Test
    public void testFindAll(){
        List list = accountService.findAll();
        System.out.println(list);
    }

    @Autowired
    private AdminInfoService adminInfoService;

    @Test
    public void deleteTest(){
        if (adminInfoService.deleteAdminInfo("334455")){
            System.out.println("test over");
        }
    }

    @Test
    public void deleteTest1(){
        if (adminInfoService.deleteAdminInfo("334455")){
            System.out.println("test over");
        }
    }
    
}
