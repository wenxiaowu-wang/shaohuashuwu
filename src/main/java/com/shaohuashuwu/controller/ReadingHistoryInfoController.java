package com.shaohuashuwu.controller;


import com.shaohuashuwu.service.ReadingHistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/readingHistoryInfoController")
public class ReadingHistoryInfoController {


    @Autowired
    private ReadingHistoryInfoService readingHistoryInfoService;


    //加入阅读历史
    @RequestMapping("/addToReadingHistory/{work_id}")
    @ResponseBody
    public Boolean addToReadingHistory(@PathVariable(value = "work_id") int work_id, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("存储阅读历史---------------------------------------------------------------");

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        boolean theResult = false;

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获得年月日
        Date currentTime = new Date();
        Timestamp timestamp = new Timestamp(currentTime.getTime());
//        String readTime = formatter.format(currentTime);

        System.out.println(timestamp);
        System.out.println("存储阅读历史========================================"+work_id);

        if(readingHistoryInfoService.addReadingHistoryInfo(user_id,work_id,timestamp)){
            System.out.println("Controller 阅读历史成功！");
            theResult = true;
        }else {
            System.out.println("Controller 阅读历史失败！");
        }
        return theResult;
    }


    //历史记录单个删除
    @RequestMapping("/deleteHistoryWorkByWorkId/{work_id}")
    @ResponseBody
    public Boolean deleteHistoryWorkByWorkId(@PathVariable(value = "work_id") int work_id) {
        boolean theResult = false;

        System.out.println("历史记录单个删除Controller");
        if(readingHistoryInfoService.deleteHistoryWorkByWorkId(work_id)){
            theResult = true;
        }        return theResult;
    }

    //清空历史记录
    @RequestMapping("/deleteHistoryWorkByUserId/{user_id}")
    @ResponseBody
    public Boolean deleteHistoryWorkByUserId(@PathVariable(value = "user_id") int user_id) {
        boolean theResult = false;

        System.out.println("清空历史记录Controller");
        if(readingHistoryInfoService.deleteHistoryWorkByUserId(user_id)){
            theResult = true;
        }        return theResult;
    }

    //清空历史记录
    @RequestMapping("/getReadingHistoryCountByUserId/{user_id}")
    @ResponseBody
    public int getReadingHistoryCountByUserId(@PathVariable(value = "user_id") int user_id) {

        return readingHistoryInfoService.getReadingHistoryCountByUserId(user_id);

    }

    /**
     * 阿斌
     */
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
