package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.AdminInfo;
import com.shaohuashuwu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/adminInfoController")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    @RequestMapping(path = "/adminLogin")
    public String adminLogin(AdminInfo adminInfo){
            //POJO对象
           // @RequestParam(value = "admin_id",required = true)String admin_id,
           // @RequestParam(value = "admin_password",required = true)String admin_password){

        String adminLoginResult = "loginFaile";
        if (adminInfoService.isAdmainRight(adminInfo)){
            adminLoginResult = "loginSuccess";
        }
        return adminLoginResult;
    }

}
