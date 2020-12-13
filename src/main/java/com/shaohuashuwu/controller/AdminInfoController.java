package com.shaohuashuwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class AdminInfoController {

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




}
