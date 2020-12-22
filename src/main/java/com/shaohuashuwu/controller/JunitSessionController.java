package com.shaohuashuwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/junitSessionController")
public class JunitSessionController {

    /**
     * 添加排行类型到seesion中
     * 功能点：排行信息
     * @param rankingType
     * @param request
     * @param response
     */
    @RequestMapping("/addRankingTypeToSession")
    public void addRankingTypeToSession(Integer rankingType,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("rankingType", rankingType);
        System.out.println("接收信息"+rankingType);
    }

    /**
     * 获取session中存储的排行类型
     * 功能点：排行信息
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping("/getRankingTypeInSession")
    public int getRankingTypeInSession(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("rankingType");

        int rankingType=Integer.parseInt(String.valueOf(msg));
        return rankingType;
    }

}
