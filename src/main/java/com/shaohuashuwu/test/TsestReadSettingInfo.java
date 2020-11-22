package com.shaohuashuwu.test;


import com.shaohuashuwu.domain.ReadSettingInfo;
import com.shaohuashuwu.service.ReadSettingInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TsestReadSettingInfo {

    @Autowired
    ReadSettingInfoService readSettingInfoService;
//    @Autowired
    ReadSettingInfo readSettingInfo;

    @Test
    public void testSelectReadSettinginfo(){

       readSettingInfo =  readSettingInfoService.selectReadSettinginfo(1);
        System.out.println(readSettingInfo);
    }

    @Test
    public void testupdateWorkSerialStateByid(){


        readSettingInfo = new ReadSettingInfo(null,4,1,1,32);

        int result =  readSettingInfoService.updateReadSettingInfoByid(readSettingInfo);
        System.out.println(result);
    }

}
