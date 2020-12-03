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

    private WorksInfo worksInfo;

    /**
     * 将关键字搜索信息存入session，跳转后获取搜索的session
     * @param worksInfodta
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addSelectInfotoSession")
    public void addSelectInfotoSession(@RequestBody WorksInfo worksInfodta,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取前端数据
        String select_input = worksInfodta.getWork_name();
        //2.将获取数据存入session
        HttpSession session = request.getSession();
        session.setAttribute("selectinput",select_input);
    }


    /**
     * 获取分类统计的作品信息
     * @return 分类数量
     */
    @ResponseBody
    @RequestMapping("/getdifvolenum")
    public Difvolenum getdifvolenum(){
        Difvolenum difvolenum = worksInfoService.getdifvolenum();
        return difvolenum;
    }


    /**
     * 将work_id存入sesssion
     * @param work_id
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addWork_idSession")
    public void addWork_idSession(Integer work_id, HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        session.setAttribute("work_id",work_id);
    }


    /**
     * 获取全部作品页面，作品信息
     * @param pageInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getworksneed")
    public List<WorksInfo> getworksneed(@RequestBody PageInfo pageInfo) {
        return  worksInfoService.getworksneed(pageInfo);
    }

    /**
     * 获取全部作品页面，作品数量
     * @param pageInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getworkstotal")
    public int getworkstotal(@RequestBody PageInfo pageInfo) {
        return  worksInfoService.getworkstotal(pageInfo);
    }

    /**
     * 根据作品id获取作品信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getworkInfoByWork_id")
    public WorksInfo getworkInfoByWork_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");
        int work_id=Integer.parseInt(String.valueOf(msg));
        return worksInfoService.getworkInfoByWork_id(work_id);
    }


    /**
     * 根据作品id获取作者的其他作品信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOtherWorkInfoByWork_id")
    public List<WorksInfo> getOtherWorkInfoByWork_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");
        int work_id=Integer.parseInt(String.valueOf(msg));
        return worksInfoService.getOtherWorkInfoByWork_id(work_id);
    }


    /**
     *依据章节id查询作品信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getworkInfoByChapter_id")
    public WorksInfo getworkInfoByChapter_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("chapter_id");
        int chapter_id=Integer.parseInt(String.valueOf(msg));

        return worksInfoService.getworkInfoByChapter_id(chapter_id);
    }

    /**
     *依据用户id查询该用户作品数量
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWorksNumByUser_id")
    public int getWorksNumByUser_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));

        return worksInfoService.getWorksNumByUser_id(user_id);
    }

    /**
     *根据用户id获取作品信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWorksInfoByUser_id")
    public List<WorksInfo> getWorksInfoByUser_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));

        return worksInfoService.getWorksInfoByUser_id(user_id);
    }


    /**
     *根据作品名称判断作品是否存在
     * @param worksInfodata
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isworkname")
    public int isworkname(@RequestBody WorksInfo worksInfodata) {
        String works_name = worksInfodata.getWork_name();
        int num = worksInfoService.isworkname(works_name);
        return  num;
    }

    @ResponseBody
    @RequestMapping("/addworkInfo")
    public int addworksdate(@RequestBody WorksInfo worksInfodata,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));


        int isaddok = worksInfoService.addworksdate(worksInfodata,user_id);
        return  isaddok;
    }


    /**
     * 修改作品信息
     * @param worksInfodata
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWorkSerialStateByid")
    public int updateWorkSerialStateByid(@RequestBody WorksInfo worksInfodata) {
        int num = worksInfoService.updateWorkInfoByworkid(worksInfodata);
        return  num;
    }
















    /***********修改未完成功能******************/

    /**
     * 将work_id存入sesssion
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
//        session.setMaxInactiveInterval(60);
//        session.removeAttribute("selectinput");
//        String b=String.valueOf(msg);
//        System.out.println("销毁后的session----"+b);

        worksInfo = new WorksInfo();
        worksInfo.setWork_name(a);
        System.out.println("worksInfo---"+worksInfo);
        List<WorksInfo> list =worksInfoService.selectworkbyinfoResult(worksInfo);;
        System.out.println("listssss====输出:"+list);
        return list;
    }


    @ResponseBody
    @RequestMapping("/selectworkbyinfoResult2")
    public List<WorksInfo> selectworkbyinfoResult2(@RequestBody WorksInfo worksInfomation, HttpServletRequest request, HttpServletResponse response){


        System.out.println("controller层查看session--");
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("selectinput");
//        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("session获取1----"+String.valueOf(msg));
        String a =String.valueOf(msg);
        System.out.println("session获取----"+a);
//        session.removeAttribute("selectinput");
//        String b=String.valueOf(msg);
//        System.out.println("销毁后的session----"+b);


        worksInfomation.setWork_name(a);
        System.out.println("worksInfomation---"+worksInfomation);
        List<WorksInfo> list =worksInfoService.selectworkbyinfoResult(worksInfomation);;
        System.out.println("listssss====输出:"+list);
        return list;
    }

    //依据章节id查询作品信息
    @ResponseBody
    @RequestMapping("/selectworkInfoByChapter_id")
    public WorksInfo selectworkInfoByChapter_id(HttpServletRequest request, HttpServletResponse response){

        System.out.println("selectworkByid测试输出数据");


        HttpSession session = request.getSession();
//        session.setAttribute("chapter_id",10);
        Object msg = session.getAttribute("chapter_id");
        int a=Integer.parseInt(String.valueOf(msg));
        System.out.println("依据章节获取作者信息的章节id："+a);

         worksInfo = worksInfoService.getworkInfoByChapter_id(a);



        return worksInfo;
    }




}
