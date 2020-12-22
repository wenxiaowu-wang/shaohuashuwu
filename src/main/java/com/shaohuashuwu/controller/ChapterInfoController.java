package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.service.ChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/chapterInfoController")
public class ChapterInfoController {

    @Autowired
    public ChapterInfoService chapterInfoService;

    /**
     * 根据作品id查询最新章节信息
     * 功能点：作品详情时获取最新章节信息，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getnewChapterInfoByword_id")
    public ChapterInfo getnewChapterInfoByword_id(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");
        int work_id=Integer.parseInt(String.valueOf(msg));
        return chapterInfoService.getnewChapterInfoByword_id(work_id);
    }

    /**
     * 将章节id保存进入session
     * @param chapter_id
     * @param request
     * @param response
     */
    @RequestMapping("/saveChapter_idSession")
    public void saveChapter_idSession(Integer chapter_id, HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        session.setAttribute("chapter_id",chapter_id);
    }

    /**
     * 根据章节id获取章节信息
     * 功能点：阅读小说界面获取章节信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getChapterInfoByChapter_id")
    public ChapterInfo getChapterInfoByChapter_id(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("chapter_id");
        int chapter_id=Integer.parseInt(String.valueOf(msg));
        return chapterInfoService.getchapterInfoByChapter_id(chapter_id);
    }

    /**
     * 新建章节
     * 功能点：添加章节功能添加章节
     * @param chapterInfo
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addchapter_info")
    public int addchapter_info(@RequestBody ChapterInfo chapterInfo, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");
        int work_id=Integer.parseInt(String.valueOf(msg));
        session.setAttribute("user_id",1);
        Object user_id_o = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(user_id_o));
        int num = chapterInfoService.addchapter_info(chapterInfo,work_id,user_id);
        return num;
    }


}
