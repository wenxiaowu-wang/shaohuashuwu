package com.shaohuashuwu;

import com.shaohuashuwu.domain.ReadSettingInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.ReadSettingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/readSettingInfoController")
public class ReadSettingInfoController {

    @Autowired
    public ReadSettingInfoService readSettingInfoService;

    @ResponseBody
    @RequestMapping(value = "/selectReadSettinginfo")
    public ReadSettingInfo selectReadSettinginfo(HttpServletRequest request, HttpServletResponse response) {

//        System.out.println("根据id修改作品状态-------------");
//        int num = worksInfoService.updateWorkSerialStateByid(worksInfodata);
//        System.out.println("controller层显示结果:"+num);
        HttpSession session = request.getSession();
        session.setAttribute("user_id",4);
        Object msg = session.getAttribute("user_id");
        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取----"+a);

        return  readSettingInfoService.selectReadSettinginfo(a);
    }


    //获取设置信息
    @ResponseBody
    @RequestMapping(value = "/updateReadSettingInfoByid")
    public int updateReadSettingInfoByid(@RequestBody ReadSettingInfo readSettingInfo) {

        System.out.println("根据id修改作品状态-------------");
        int num = readSettingInfoService.updateReadSettingInfoByid(readSettingInfo);
        System.out.println("controller层显示结果:"+num);
        return num;

    }




}
