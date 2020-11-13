package com.shaohuashuwu.test;

import com.shaohuashuwu.service.AuthorInfoVoService;
import com.shaohuashuwu.service.WorksInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAuthorInfoService {

    @Autowired
    private AuthorInfoVoService authorInfoVoService;

    @Test
    public void testselectAuthorInfoVo(){
        authorInfoVoService.selectAuthorInfoVo(11);
    }


}
