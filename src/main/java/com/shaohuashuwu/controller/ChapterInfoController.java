package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
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


//        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();
//        session.setAttribute("chapter_id",34);
        Object msg = session.getAttribute("chapter_id");
        int a=Integer.parseInt(String.valueOf(msg));
//        System.out.println("aaa"+a);
        System.out.println("查询章节功能session获取chapter_id----"+a);

         chapterInfo= chapterInfoService.selectchapterInfoByChapter_id(a);



        return chapterInfo;
//

    }

    @ResponseBody
    @RequestMapping(value = "/selectchaptercatalog")
    public List<CatalogInfoVo> selectchaptercatalog(HttpServletRequest request, HttpServletResponse response) throws Exception {


//        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();
//        session.setAttribute("chapter_id",34);
        session.setAttribute("user_id",1);
        Object chapter_id_o = session.getAttribute("chapter_id");
        Object user_id_o = session.getAttribute("user_id");
        int chapter_id=Integer.parseInt(String.valueOf(chapter_id_o));
        int user_id=Integer.parseInt(String.valueOf(user_id_o));
        System.out.println("查询章节session获取得到的chapter_id----"+chapter_id);

        List<CatalogInfoVo> list = chapterInfoService.selectchaptercatalog(user_id,chapter_id);
        System.out.println("contrroler显示出应该相应的章节目录"+list);
        return list;
//

    }

    @RequestMapping("/saveChapter_idSession")
    public void saveChapter_idSession(Integer chapter_id, HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("将要存储的session信息chapter_id："+chapter_id);
        HttpSession session = request.getSession();
        session.setAttribute("chapter_id",chapter_id);

    }

    @ResponseBody
    @RequestMapping(value = "/selectchaptercatalogBywork_id")
    public List<CatalogInfoVo> selectchaptercatalogBywork_id(HttpServletRequest request, HttpServletResponse response) throws Exception {


        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object work_id_o = session.getAttribute("work_id");
        Object user_id_o = session.getAttribute("user_id");
        int work_id=Integer.parseInt(String.valueOf(work_id_o));
        int user_id=Integer.parseInt(String.valueOf(user_id_o));
        System.out.println("session获取work_id----"+work_id);

        return chapterInfoService.selectchaptercatalogBywork_id(user_id,work_id);
//

    }


//    selectchapterInfoByChapter_pid
//    @ResponseBody
//    @RequestMapping("/selectchapterInfoByChapter_pid")
//    public ChapterInfo selectchapterInfoByChapter_pid(Integer chapter_pid, HttpServletRequest request, HttpServletResponse response)  {
//
//        System.out.println();
//        return chapterInfoService.selectchapterInfoByChapter_pid(chapter_pid);
//
//    }

}
