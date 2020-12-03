package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
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

    private WorkWholeInfoVo workWholeInfoVo;
    private List<WorkWholeInfoVo> workWholeInfoVoList;

//    private WorksInfo worksInfo;


    /**
     * 获取主页不同分类的作品信息
     * @return 分类数量
     */
    @ResponseBody
    @RequestMapping("/getdifferentStateWork")
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork){

        return workWholeInfoVoService.getdifferentStateWork(differentStateWork);
    }


    /**
     * 通过搜索信息获取作品全部信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWorkWholeInfoBySelectinput")
    public List<WorkWholeInfoVo> getWorkWholeInfoBySelectinput(@RequestBody WorksInfo worksInfomation, HttpServletRequest request, HttpServletResponse response){
        //获取搜索内容
        HttpSession session = request.getSession();
        Object selectinputObj = session.getAttribute("selectinput");
        String selectinput =String.valueOf(selectinputObj);

        System.out.println("获取得到信息"+worksInfomation);
        //设置作品信息

        worksInfomation.setWork_name(selectinput);
        System.out.println("需要搜索信息"+worksInfomation);
        workWholeInfoVoList = workWholeInfoVoService.getWorkWholeInfoBySelectinput(worksInfomation);

        return workWholeInfoVoList;
    }


}
