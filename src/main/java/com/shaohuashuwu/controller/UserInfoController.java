package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userInfoController")
public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;

//    @Autowired
    private UserInfo userInfo;


    //依据章节id查询作品信息
    @ResponseBody
    @RequestMapping("/selectuserInfoByuserid")
    public UserInfo selectuserInfoByuserid(HttpServletRequest request, HttpServletResponse response){

        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();
        session.setAttribute("chapter_id",10);
        Object msg = session.getAttribute("chapter_id");
        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取----"+a);

        userInfo = userInfoService.selectUserInfoByChapter_id(a);



        return userInfo;
    }




}
