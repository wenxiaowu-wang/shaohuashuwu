package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/adminInfoController")
@SessionAttributes(value = {"admin_id"},types = {String.class})
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    /**
     *根据光伏案例元id获取管理员信息
     * 功能点：管理员顶部顶部管理信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getadmin_id")
    public String getadmin_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("admin_id","admin1111");
        Object msg = session.getAttribute("admin_id");
        String admin_id=String.valueOf(msg);

        return admin_id;
    }

    /**
     * 阿斌
     */
    @RequestMapping(path = "/toAdminLoginInterface")
    public String toAdminLoginInterface(){
        return "adminLoginInterface.html";
    }

    @RequestMapping(path = "/adminLogin//{admin_id}/{admin_password}")
    @ResponseBody
    public Boolean adminLogin(@PathVariable(value = "admin_id")String admin_id, @PathVariable(value = "admin_password")String admin_password){
        //POJO对象
        // @RequestParam(value = "admin_id",required = true)String admin_id,
        // @RequestParam(value = "admin_password",required = true)String admin_password){

        boolean adminLoginResult = false;
        adminLoginResult = adminInfoService.isAdmainRight(admin_id,admin_password);
        return adminLoginResult;
    }

    @RequestMapping(path = "/updateAdminPassword/{old_pass}/{admin_password}")
    @ResponseBody
    public int updateAdminPassword(ModelMap modelMap, @PathVariable(value = "old_pass")String old_pass, @PathVariable(value = "admin_password")String admin_password){
        String admin_id = (String)modelMap.get("admin_id");
        int updateResult = 0;
        if (adminInfoService.isAdmainRight(admin_id,old_pass)){
            if (adminInfoService.updateAdminPassword(admin_id,admin_password)){
                updateResult = 1;
            }else{
                updateResult = 2;
            }
        }
        return updateResult;
    }



}
