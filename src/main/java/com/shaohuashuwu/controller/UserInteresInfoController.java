package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.UserinterestInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.UserInterestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userInteresInfoController")
public class UserInteresInfoController {
    @Autowired
    private UserInterestInfoService userInterestInfoService;

    /**
     * 根据作品id获取作品信息
     * 功能点：作品详情时获取作品信息，添加章节获取作品信息，作品设置中获取作品信息，下架作品中获取作品信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUserInterestInfo")
    public int updateUserInterestInfo(@RequestBody UserinterestInfo userinterestInfo, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        userinterestInfo.setUser_id(user_id);
        return userInterestInfoService.updateUserInterestInfo(userinterestInfo);
    }


}
