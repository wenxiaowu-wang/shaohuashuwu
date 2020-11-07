package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class ReportInfoController {

    @Autowired
    public ReportInfoService reportInfoService;

    @RequestMapping(path = "/reportDetectionChapter/{userId}/{chapterId}/{checkedList}")
    @ResponseBody
    public int reportDetectionChapter(@PathVariable(value = "userId")Integer userId, @PathVariable(value = "chapterId")Integer chapterId, @PathVariable(value = "checkedList")List<Integer> checkedList){
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
