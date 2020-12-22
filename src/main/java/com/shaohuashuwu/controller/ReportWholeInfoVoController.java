package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.vo.AdminSelectInfoVo;
import com.shaohuashuwu.domain.vo.ReportWholeInfoVo;
import com.shaohuashuwu.service.ReportWholeInfoVoService;
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
@RequestMapping("/reportWholeInfoVoController")
public class ReportWholeInfoVoController {

    @Autowired
    private ReportWholeInfoVoService reportWholeInfoVoService;

    /**
     * 分页获取举报信息
     * 功能点：获取举报信息列表
     * @param adminSelectInfoVo
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getreportWholeInfoVoList")
    public List<ReportWholeInfoVo> getreportWholeInfoVoList(@RequestBody AdminSelectInfoVo adminSelectInfoVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return reportWholeInfoVoService.getreportWholeInfoVoList(adminSelectInfoVo);
    }

    /**
     * 获取举报信息数量
     * 功能点：获取举报信息数量
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getreportWholeInfoVoTotal")
    public int getreportWholeInfoVoTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return reportWholeInfoVoService.getreportWholeInfoVoTotal();
    }


    /**
     * 获取举报的详细信息
     *功能点：举报详情获取举报信息
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getreportWholeInfoVoByreport_id")
    public ReportWholeInfoVo getreportWholeInfoVoByreport_id(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("report_id");
        int report_id=Integer.parseInt(String.valueOf(msg));

        return reportWholeInfoVoService.getreportWholeInfoVoByreport_id(report_id);
    }


    /**
     * 分页获取处理结果信息
     * 功能点：更改处理结果的举报信息列表
     * @param adminSelectInfoVo
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/gethandleResultList")
    public List<ReportWholeInfoVo> gethandleResultList(@RequestBody AdminSelectInfoVo adminSelectInfoVo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return reportWholeInfoVoService.gethandleResultList(adminSelectInfoVo);
    }

    /**
     * 获取处理结果数量
     *  功能点：更改处理结果的举报信息数量
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/gethandleResultListTotal")
    public int gethandleResultListTotal(@RequestBody AdminSelectInfoVo adminSelectInfoVo,HttpServletRequest request, HttpServletResponse response) throws Exception {
        return reportWholeInfoVoService.gethandleResultListTotal(adminSelectInfoVo);
    }


}
