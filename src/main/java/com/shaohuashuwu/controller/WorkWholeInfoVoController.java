package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import com.shaohuashuwu.service.WorkWholeInfoVoService;
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
@RequestMapping("/workWholeInfoVoController")
public class WorkWholeInfoVoController {

    @Autowired
    private WorkWholeInfoVoService workWholeInfoVoService;

    /**
     * 获取主页不同分类的作品信息
     * 功能点：获取主页信息
     * @return 分类数量
     */
    @ResponseBody
    @RequestMapping("/getdifferentStateWork")
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork){

        return workWholeInfoVoService.getdifferentStateWork(differentStateWork);
    }

    /**
     * 通过搜索信息获取作品全部信息
     * 功能点：关键字搜索搜索内容，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWorkWholeInfoBySelectinput")
    public List<WorkWholeInfoVo> getWorkWholeInfoBySelectinput(@RequestBody WorksInfo worksInfo, HttpServletRequest request, HttpServletResponse response){
        //获取搜索内容
        HttpSession session = request.getSession();
        Object selectinputObj = session.getAttribute("selectinput");
        String selectinput =String.valueOf(selectinputObj);
        //设置作品信息
        worksInfo.setWork_name(selectinput);
        return workWholeInfoVoService.getWorkWholeInfoBySelectinput(worksInfo);
    }

    /**
     * 通过作者id信息获取作品全部信息
     * 功能点：作品信息内容，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getworkWholeInfoVoByauthor_id")
    public List<WorkWholeInfoVo> getworkWholeInfoVoByauthor_id(HttpServletRequest request, HttpServletResponse response){
        //获取搜索内容
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("author_id");
        int author_id=Integer.parseInt(String.valueOf(msg));
        return workWholeInfoVoService.getworkWholeInfoVoByauthor_id(author_id);
    }

    /**
     * 通过用户id信息获取作品全部信息
     * 功能点：个性推荐作品
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getworkWholeInfoVoByuser_id")
    public List<WorkWholeInfoVo> getworkWholeInfoVoByuser_id(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户id
        HttpSession session = request.getSession();
        session.setAttribute("user_id",0);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        return workWholeInfoVoService.getworkWholeInfoVoByuser_id(user_id);
    }


}
