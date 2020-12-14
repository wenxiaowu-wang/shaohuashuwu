package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.ReadingHistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/11/7
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/readingHistoryInfoController")
public class ReadingHistoryInfoController {

    @Autowired
    public ReadingHistoryInfoService readingHistoryInfoService;

    @RequestMapping(path = "/getReaderAgeDistributionByWorkId/{work_id}")
    @ResponseBody
    public Map<String, List<Map<String,Object>>> getReaderAgeDistributionByWorkId(@PathVariable(value = "work_id")int work_id){
        List<String> type = new ArrayList<String>();
        type.add("10岁以下");
        type.add("10~18岁");
        type.add("18~25岁");
        type.add("25~35岁");
        type.add("35岁及以上");
        return readingHistoryInfoService.getReaderAgeDistributionByWorkId(type,work_id);
    }

    @RequestMapping(path = "/getReadingTimeDistributionByWorkId/{work_id}")
    @ResponseBody
    public Map<String, List<Map<String,Object>>> getReadingTimeDistributionByWorkId(@PathVariable(value = "work_id")int work_id){
        List<String> type = new ArrayList<String>();
        for (int i =0;i<24;i++){
            if (i<10){
                type.add("0"+i);
            }else{
                type.add(i+"");
            }
        }
        return readingHistoryInfoService.getReadingTimeDistributionByWorkId(type,work_id);
    }

}
