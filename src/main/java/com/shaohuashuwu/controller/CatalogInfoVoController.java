package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import com.shaohuashuwu.service.CatalogInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/catalogInfoVoController")
public class CatalogInfoVoController {

    @Autowired
    public CatalogInfoVoService catalogInfoVoService;

    private ChapterInfo chapterInfo;

    /**
     * 依据作品id，作者id，获取章节目录信息
     * 功能点：作品详情时获取目录新信息，
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getchaptercatalogBywork_id")
    public List<CatalogInfoVo> getchaptercatalogBywork_id(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("获取目录信息---------------------------------------");
        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object work_id_o = session.getAttribute("work_id");
        Object user_id_o = session.getAttribute("user_id");
        int work_id=Integer.parseInt(String.valueOf(work_id_o));
        int user_id=Integer.parseInt(String.valueOf(user_id_o));

        System.out.println("目录信息为+++++++++++++++++++++++++++++++=="+catalogInfoVoService.getchaptercatalogBywork_id(user_id,work_id));

        return catalogInfoVoService.getchaptercatalogBywork_id(user_id,work_id);
    }


    /**
     * 依据作品id，获取章节目录信息
     * 功能点：添加作品获取章节信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getchaptercatalogBywork_id2")
    public List<CatalogInfoVo> getchaptercatalogBywork_id2(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("获取目录信息---------------------------------------");
        HttpSession session = request.getSession();
        Object work_id_o = session.getAttribute("work_id");

        int work_id=Integer.parseInt(String.valueOf(work_id_o));

        System.out.println("目录信息为+++++++++++++++++++++++++++++++=="+catalogInfoVoService.getchaptercatalogBywork_id2(work_id));

        return catalogInfoVoService.getchaptercatalogBywork_id2(work_id);
    }



    /**
     * 依据章节id，获取章节目录信息
     * 功能点：阅读小说界面获取目录信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getchaptercatalogBychapter_id")
    public List<CatalogInfoVo> getchaptercatalogBychapter_id(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object chapter_id_o = session.getAttribute("chapter_id");
        Object user_id_o = session.getAttribute("user_id");
        int chapter_id=Integer.parseInt(String.valueOf(chapter_id_o));
        int user_id=Integer.parseInt(String.valueOf(user_id_o));

        return catalogInfoVoService.getchaptercatalogBychapter_id(user_id,chapter_id);
    }



}
