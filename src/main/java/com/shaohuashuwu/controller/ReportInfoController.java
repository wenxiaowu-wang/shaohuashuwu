package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(path = "/reportInfoController")
@SessionAttributes(value = {"user_name","user_id","work_id","work_name","chapter_id","chapter_title"},types = {String.class,Integer.class})
public class ReportInfoController {

    @Autowired
    private ReportInfoService reportInfoService;

    /**
     * 将report_id保存进入session
     * @param report_id
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addReport_idtoSession")
    public void addReport_idtoSession(Integer report_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.将获取数据存入session
        HttpSession session = request.getSession();
        session.setAttribute("report_id",report_id);
    }


    /**
     * 修改举报信息的举报状态
     * 功能点：处理作品信息修改处理信息，更改处理结果
     * @param
     */
    @ResponseBody
    @RequestMapping("/updateReportInfoByReport_id")
    public int updateReportInfoByReport_id(Integer handle_state,HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("report_id");
        int report_id=Integer.parseInt(String.valueOf(msg));
        return reportInfoService.updateReportInfoByReport_id(report_id,handle_state);

    }

    /**
     * 阿斌
     */
    @RequestMapping(path = "/reportDetectionChapter/{checkedList}")
    @ResponseBody
    public int reportDetectionChapter(ModelMap modelMap, @PathVariable(value = "checkedList") List<Integer> checkedList){
        Integer user_id = (Integer)modelMap.get("user_id");
        Integer chapter_id = (Integer)modelMap.get("chapter_id");
        Integer work_id = (Integer)modelMap.get("work_id");
        return reportInfoService.reportChapter(user_id,chapter_id,work_id, checkedList.get(0));
    }

    @RequestMapping(path = "/toReportingWorks")
    public String toReportingWorks(){
        return "reportingWorksInterface.html";
    }


}
