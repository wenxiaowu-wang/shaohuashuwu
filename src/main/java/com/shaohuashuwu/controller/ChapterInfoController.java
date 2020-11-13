package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.service.ChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/chapterInfoController")
public class ChapterInfoController {

    @Autowired
    public ChapterInfoService chapterInfoService;


    private  ChapterInfo chapterInfo;

    @RequestMapping(value = "/insertchapter_info")
    public void insertchapter_info(@RequestBody ChapterInfo chapterInfo, HttpServletRequest request, HttpServletResponse response) {



//        获取session中存储的work_id值
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");
        int work_id=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取----"+work_id);


        System.out.println("根据id修改作品状态-------------"+chapterInfo);
        int a = chapterInfoService.insertchapter_info(chapterInfo,work_id);
        System.out.println("controller层显示结果:"+a);

    }

    @ResponseBody
    @RequestMapping(value = "/selectUserInfoByChapter_id")
    public ChapterInfo selectUserInfoByChapter_id(HttpServletRequest request, HttpServletResponse response) {


        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();
        session.setAttribute("chapter_id",10);
        Object msg = session.getAttribute("chapter_id");
        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取----"+a);

         chapterInfo= chapterInfoService.selectchapterInfoByChapter_id(a);



        return chapterInfo;
//

    }



}
