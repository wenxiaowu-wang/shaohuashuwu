package com.shaohuashuwu.test;

import com.shaohuashuwu.dao.WorkWholeInfoVoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.WorkWholeInfoVoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestWorkWholeInfoVo {

    @Autowired
    private WorkWholeInfoVoService workWholeInfoVoService;

    @Autowired
    private WorkWholeInfoVoDao workWholeInfoVoDao;

    WorksInfo worksInfo;

    @Test
    public void test(){

        workWholeInfoVoService.getdifferentStateWork(1);
        workWholeInfoVoService.getdifferentStateWork(2);
        workWholeInfoVoService.getdifferentStateWork(3);
    }

    @Test
    public void testgetWorkWholeInfoBySelectinput(){
        worksInfo = new WorksInfo("不可思议",null,null,null);
        System.out.println("相应数据"+workWholeInfoVoService.getWorkWholeInfoBySelectinput(worksInfo));
    }
}
