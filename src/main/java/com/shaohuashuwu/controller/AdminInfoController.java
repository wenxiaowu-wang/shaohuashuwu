package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.AdminInfo;
import com.shaohuashuwu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String adminLogin(@RequestBody AdminInfo adminInfo){
            //POJO对象
           // @RequestParam(value = "admin_id",required = true)String admin_id,
           // @RequestParam(value = "admin_password",required = true)String admin_password){

        System.out.println("进入登录函数。。。");
        System.out.println(adminInfo.toString());
        String adminLoginResult = "loginFaile";
        if (adminInfoService.isAdmainRight(adminInfo)){
            adminLoginResult = "loginSuccess";
            System.out.println("登录成功了...");
            System.out.println("进入panduan函数。。。");
            System.out.println(adminInfo.toString());
        }
        return adminLoginResult;
    }

    /**
     * 测试带着数据跳转页面
     * @param adminInfo
     * @return modelAndView
     */
    @RequestMapping(path = "/forwoardWithData")
    public ModelAndView forwoardWithData(@RequestBody AdminInfo adminInfo){
        ModelAndView modelAndView = new ModelAndView();
        //跳转到loginResult页面
        modelAndView.setViewName("loginSuccess");

        //添加对象
        modelAndView.addObject("admin_name",adminInfo.getAdmin_id());

        return modelAndView;
    }

    @RequestMapping(path = "/testChuanZhi")
    public @ResponseBody String testChuanZhi(@RequestBody AdminInfo adminInfo){
            //POJO对象
           // @RequestParam(value = "admin_id",required = true)String admin_id,
           // @RequestParam(value = "admin_password",required = true)String admin_password){

        System.out.println(adminInfo.toString());
        return adminInfo.toString();
    }

    /**
     * 测试前后连接
     */
    @RequestMapping(path = "/findAdminDataByName")
    public @ResponseBody AdminInfo findAdminData(){
        System.out.println("进入后端。。。。。。。");
        String name = "abin";
        AdminInfo getResult = adminInfoService.getOneByName(name);
        System.out.println(getResult.toString());
        return getResult;
    }

    /**
     * 跳转登录页面
     */
    @RequestMapping(path = "/adminLoginInterface")
    public String toLoginInterface(){
        return "hello.html";
    }
    /**
     * 跳转登录页面
     */
    @RequestMapping(path = "/adminLoginInterface2")
    public String toLoginInterface2(){
        return "login.html";
    }
}
