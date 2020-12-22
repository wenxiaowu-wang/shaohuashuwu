package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.ReportInfoService;
import com.shaohuashuwu.service.ReportWholeInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/reportInfoController")
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

}
