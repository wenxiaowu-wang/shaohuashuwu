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

    private UserInfo userInfo;



    /**
     * 将user_id存入sesssion
     * @param author_id
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addAuthor_idSession")
    public void addAuthor_idSession(Integer author_id, HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        session.setAttribute("author_id",author_id);
    }


    /**
     *  获取用户登录信息
     * @return
     */
    //需要去掉设置额session——id
    @ResponseBody
    @RequestMapping("/getUserLoginInfo")
    public UserInfo getUserLoginInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user_id",0);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        return userInfoService.getUserLoginInfo(user_id);
    }

    /**
     * 获取网站注册人数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserNum")
    public int getUserNum(){
        return userInfoService.getUserNum();
    }





    /**
     *依据作品id查询作者信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserInfoByWork_id")
    public UserInfo getUserInfoByWork_id(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");
        int work_id=Integer.parseInt(String.valueOf(msg));
        userInfo = userInfoService.getUserInfoByWork_id(work_id);
        return userInfo;
    }


    /**
     * 根据章节id获取用户信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getauthorInfoBychapterid")
    public UserInfo getauthorInfoBychapterid(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("chapter_id");
        int chapter_id=Integer.parseInt(String.valueOf(msg));
        userInfo = userInfoService.getauthorInfoBychapterid(chapter_id);
        return userInfo;
    }













    /***********修改未完成功能******************/


    //依据章节id查询作品信息

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectuserInfoByuserid")
    public UserInfo selectuserInfoByuserid(HttpServletRequest request, HttpServletResponse response){

        System.out.println("查询作者信息selectworkByid测试输出数据：");
        HttpSession session = request.getSession();
//        session.setAttribute("chapter_id",);
        Object msg = session.getAttribute("chapter_id");
        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("获取作者功能输出chapter——id："+a);

        userInfo = userInfoService.getauthorInfoBychapterid(a);



        return userInfo;
    }













}
