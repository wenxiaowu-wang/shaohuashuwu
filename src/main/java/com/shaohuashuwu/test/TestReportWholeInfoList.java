package com.shaohuashuwu.test;

import com.shaohuashuwu.dao.ReportWholeInfoVoDao;
import com.shaohuashuwu.domain.vo.AdminSelectInfoVo;
import com.shaohuashuwu.service.ReportWholeInfoVoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestReportWholeInfoList {

    @Autowired
    ReportWholeInfoVoService reportWholeInfoVoService;

    @Autowired
    ReportWholeInfoVoDao reportWholeInfoVoDao;

    @Test
    public void test(){
        System.out.println("输出"+reportWholeInfoVoService.getreportWholeInfoVoList(null));
    }


    @Test
    public void  tset(){
        AdminSelectInfoVo adminSelectInfoVo =new AdminSelectInfoVo("不可思议","2020-12-12",1,8);
        System.out.println("输出："+reportWholeInfoVoDao.selecthandleResultList(adminSelectInfoVo));
    }
}
