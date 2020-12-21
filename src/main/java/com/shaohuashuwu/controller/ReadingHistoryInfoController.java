package com.shaohuashuwu.controller;


import com.shaohuashuwu.service.ReadingHistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(path = "/readingHistoryInfoController")
public class ReadingHistoryInfoController {


    @Autowired
    private ReadingHistoryInfoService readingHistoryInfoService;


    //加入阅读历史
    @RequestMapping("/addToReadingHistory/{user_id}/{work_id}")
    @ResponseBody
    public Boolean addToReadingHistory(@PathVariable(value = "user_id") int user_id, @PathVariable(value = "work_id") int work_id) {
        System.out.println("阅读历史Controller");
        System.out.println("用户ID：" + user_id + "、作品ID：" + work_id);
        boolean theResult = false;

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获得年月日
        Date currentTime = new Date();
        Timestamp timestamp = new Timestamp(currentTime.getTime());
//        String readTime = formatter.format(currentTime);

        System.out.println(timestamp);

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
}
