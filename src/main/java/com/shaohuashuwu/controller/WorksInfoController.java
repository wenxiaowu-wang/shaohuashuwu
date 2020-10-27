package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/worksInfoController")
public class WorksInfoController {
    @Autowired
    private WorksInfoService worksInfoService;

//    @Autowired
    private WorksInfo worksInfo;


    /**
     * 根据work_id查询作品信息
     * @param work_id
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/selectworksByworkid")
    public void selectworksByworkid(Integer work_id, HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("controller层根据work_id查询");
        System.out.println(work_id);
        HttpSession session = request.getSession();
        session.setAttribute("work_id",work_id);
//        return "mangagementWorksInterface.html";
    }



    @ResponseBody
    @RequestMapping("/selectworkByid")
    public WorksInfo selectworkByid(HttpServletRequest request, HttpServletResponse response){

        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();

        Object msg = session.getAttribute("work_id");
        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取----"+a);
        session.removeAttribute("work_id");
        int b=Integer.parseInt(String.valueOf(msg));
        System.out.println("销毁后的session----"+b);

        WorksInfo worksInfoList = worksInfoService.selectworkByid(a);



//        List<WorksInfo> list = worksInfoService.selectworkByid(a);
//        System.out.println(list);
        return worksInfoList;
    }

    @ResponseBody
    @RequestMapping(value = "/updateWorkSerialStateByid")
    public int updateWorkSerialStateByid(@RequestBody WorksInfo worksInfodata) {

        System.out.println("根据id修改作品状态-------------");
        int num = worksInfoService.updateWorkSerialStateByid(worksInfodata);
        System.out.println("controller层显示结果:"+num);
        return  num;
    }

    @ResponseBody
    @RequestMapping(value = "/difvolenum")
    public Difvolenum selectdifvolenum(){
        Difvolenum difvolenum = worksInfoService.selectdifvolenum();
        System.out.println("con层---"+difvolenum);
        return difvolenum;
    }

    @ResponseBody
    @RequestMapping(value = "/selectworksneed")
    public List<WorksInfo> selectworksneed(@RequestBody PageInfo pageInfo) {

        System.out.println("根据id修改作品状态-------------");
        List<WorksInfo> list = worksInfoService.selectworksneed(pageInfo);
        System.out.println("controller层显示结果:"+list);
        return  list;
    }

    @ResponseBody
    @RequestMapping(value = "/selectworkstotal")
    public int selectworkstotal(@RequestBody PageInfo pageInfo) {

        System.out.println("根据id修改作品状态-------------");
        int total = worksInfoService.selectworkstotal(pageInfo);
        System.out.println("controller层显示结果:"+total);
        return  total;
    }

    @RequestMapping("/selectworkbyinfo")
    public String selectworkbyinfo(@RequestBody WorksInfo worksInfodta,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("controller层搜索条件");
        System.out.println(worksInfodta.getWork_name());
        String select_input = worksInfodta.getWork_name();
        System.out.println(select_input);
        HttpSession session = request.getSession();
        session.setAttribute("selectinput",select_input);

        Object msg = session.getAttribute("selectinput");
//        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session自己获取----"+String.valueOf(msg));

        return "keywordSearchResultInterface.html";
        //response.sendRedirect(request.getContextPath()+"/worksInfoController/selectworkbyinfoResult");
    }

    @ResponseBody
    @RequestMapping("/selectworkbyinfoResult")
    public List<WorksInfo> selectworkbyinfoResult(HttpServletRequest request, HttpServletResponse response){


        System.out.println("controller层查看session--");
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("selectinput");
//        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取1----"+String.valueOf(msg));
        String a =String.valueOf(msg);
        System.out.println("session获取----"+a);
        session.removeAttribute("selectinput");
        String b=String.valueOf(msg);
        System.out.println("销毁后的session----"+b);

        worksInfo = new WorksInfo();
        worksInfo.setWork_name(a);
        System.out.println("worksInfo---"+worksInfo);
        List worksInfoList =worksInfoService.selectworkbyinfoResult(worksInfo);;
        System.out.println(worksInfoList);
        return null;
    }


}
