package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.ReadSettingInfo;
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


    /**
     * 依据用户id获取设置信息
     * 功能点：用阅读小说界面设置获取，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getReadSettinginfo")
    public ReadSettingInfo getReadSettinginfo(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        return  readSettingInfoService.getReadSettinginfo(user_id);
    }


    /**
     * 根据用户id修改设置信息
     * 功能点：阅读小说界面修改设置，
     * @param readSettingInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateReadSettingInfoByuser_id")
    public int updateReadSettingInfoByuser_id(@RequestBody ReadSettingInfo readSettingInfo) {
        int num = readSettingInfoService.updateReadSettingInfoByid(readSettingInfo);
        return num;
    }


}
