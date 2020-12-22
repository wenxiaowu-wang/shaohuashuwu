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
import java.util.List;

@Controller
@RequestMapping("/worksInfoController")
public class WorksInfoController {
    @Autowired
    private WorksInfoService worksInfoService;

    /**
     * 将关键字搜索信息存入session，跳转后获取搜索的session
     * @param worksInfo
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addSelectInfotoSession")
    public void addSelectInfotoSession(@RequestBody WorksInfo worksInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取前端数据
        String select_input = worksInfo.getWork_name();
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
        System.out.println("作品id"+work_id);
        HttpSession session = request.getSession();
        session.setAttribute("work_id",work_id);
    }


    /**
     * 获取全部作品页面，作品信息
     * 功能点：获取用全部作品界面的作品信息
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
     * 功能点：获取用全部作品界面的作品数量
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
     * 功能点：作品详情时获取作品信息，添加章节获取作品信息，作品设置中获取作品信息，下架作品中获取作品信息
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
        System.out.println("chan");
        return worksInfoService.getworkInfoByWork_id(work_id);
    }


    /**
     * 根据作品id获取作者的其他作品信息
     * 功能点：作品详情时获取作者其他作品信息，
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
     * 功能点：阅读小说界面获取作品信息
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
     * 功能点：作者端顶部作品数量，
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
     * 功能点：作者端工作台作品信息，
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
     * 功能点：添加作品功能验证作品是否存在
     * @param worksInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isworkname")
    public int isworkname(@RequestBody WorksInfo worksInfo) {
        String works_name = worksInfo.getWork_name();
        int num = worksInfoService.isworkname(works_name);
        return  num;
    }

    /**
     * 添加作品
     * 功能点：添加作品功能添加作品
     * @param worksInfo
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/addworkInfo")
    public int addworkInfo(@RequestBody WorksInfo worksInfo,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));


        int insertResult = worksInfoService.addworkInfo(worksInfo,user_id);
        return insertResult;
    }


    /**
     * 修改作品信息，
     * 功能点：下架作品中修改作品设置信息
     * @param worksInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWorkSerialStateByid")
    public int updateWorkSerialStateByid(@RequestBody WorksInfo worksInfo) {
        int updateResult = worksInfoService.updateWorkInfoByworkid(worksInfo);
        return  updateResult;
    }

    /**
     * 修改作品信息，
     * 功能点：下架作品中修改作品设置信息
     * @param worksInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateworkInfoByWork_id")
    public void updateworkInfoByWork_id(@RequestBody WorksInfo worksInfo) {
         worksInfoService.updateworkInfoByWork_id(worksInfo);
    }

}
