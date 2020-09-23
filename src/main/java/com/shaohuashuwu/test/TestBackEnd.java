package com.shaohuashuwu.test;

import com.shaohuashuwu.service.AccountService;
import com.shaohuashuwu.service.AdminInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestBackEnd {
    @Autowired
    private AccountService accountService;

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
    
}
