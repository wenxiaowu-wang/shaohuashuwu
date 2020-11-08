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

    @RequestMapping(path = "/adminLogin/{admin_id}/{admin_password}")
    @ResponseBody
    public Boolean adminLogin(@PathVariable(value = "admin_id")String admin_id,@PathVariable(value = "admin_password")String admin_password){
            //POJO对象
           // @RequestParam(value = "admin_id",required = true)String admin_id,
           // @RequestParam(value = "admin_password",required = true)String admin_password){

        System.out.println("admin_id = "+admin_id+",admin_password = "+admin_password);
        Boolean adminLoginResult = false;
        adminLoginResult = adminInfoService.isAdmainRight(admin_id,admin_password);
        return adminLoginResult;
    }

    @RequestMapping(path = "/updateAdminPassword/{admin_id}/{admin_password}")
    @ResponseBody
    public Boolean updateAdminPassword(@PathVariable(value = "admin_id")String admin_id,@PathVariable(value = "admin_password")String admin_password){
        Boolean updateResult = false;
        updateResult = adminInfoService.updateAdminPassword(admin_id,admin_password);
        return updateResult;
    }

    @RequestMapping(path = "/forwardToLoginResult")
    public String forwardToLoginResult(){
        String result = "loginSuccess.html";
        return result;
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

    /**
     * 传值测试，测试成功
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(path = "/testChuanZhi/{admin_id}/{admin_password}")
    public @ResponseBody String testChuanZhi(@PathVariable(value = "admin_id") String id,@PathVariable(value = "admin_password")String password){
            //POJO对象
           // @RequestParam(value = "admin_id",required = true)String admin_id,
           // @RequestParam(value = "admin_password",required = true)String admin_password){
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdmin_id(id);
        adminInfo.setAdmin_password(password);
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
     * 跳转登录页面(假)
     */
    @RequestMapping(path = "/adminLoginInterface")
    public String toLoginInterface(){
        return "hello.html";
    }
    /**
     * 跳转登录页面(假)
     */
    @RequestMapping(path = "/adminLoginInterface2")
    public String toLoginInterface2(){
        return "adminLoginInterface.html";
    }

    @RequestMapping(path = "/toAdminLoginInterface")
    public String toAdminLoginInterface(){
        return "adminLoginInterface.html";
    }
}
