package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/11/7
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/reportInfoController")
@SessionAttributes(value = {"user_name","user_id","work_id","work_name","chapter_id","chapter_title"},types = {String.class,Integer.class})
public class ReportInfoController {

    @Autowired
    public ReportInfoService reportInfoService;

    @RequestMapping(path = "/reportDetectionChapter/{checkedList}")
    @ResponseBody
    public int reportDetectionChapter(ModelMap modelMap,@PathVariable(value = "checkedList")List<Integer> checkedList){
        Integer user_id = (Integer)modelMap.get("user_id");
        Integer chapter_id = (Integer)modelMap.get("chapter_id");
        Integer work_id = (Integer)modelMap.get("work_id");

        int reportResult = 0;
        System.out.println("调用处理举报信息模块,checkedList内容如下：");
        for (int i = 0;i<checkedList.size();i++){
            System.out.println(checkedList.get(i));
        }
        return reportResult;
    }

    @RequestMapping(path = "/toReportingWorks")
    public String toReportingWorks(){
        return "reportingWorksInterface.html";
    }

}
