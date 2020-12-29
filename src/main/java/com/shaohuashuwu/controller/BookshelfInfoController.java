package com.shaohuashuwu.controller;


import com.shaohuashuwu.service.BookshelfInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/bookshelfInfoController")
public class BookshelfInfoController {

    @Autowired
    private BookshelfInfoService bookshelfInfoService;

    //加入书架
    @RequestMapping("/addToBookshelf/{user_id}/{work_id}")
    @ResponseBody
    public Boolean addToBookshelf(@PathVariable(value = "user_id") int user_id, @PathVariable(value = "work_id") int work_id) {
        System.out.println("书架Controller");
        System.out.println("用户ID：" + user_id + "、作品ID：" + work_id);
        boolean theResult = false;

        Date currentTime = new Date();
        Timestamp timestamp = new Timestamp(currentTime.getTime());

        System.out.println(timestamp);

        if (bookshelfInfoService.addBookshelfInfo(user_id, work_id, timestamp)) {
            System.out.println("Controller 加入书架成功！");
            theResult = true;
        } else {
            System.out.println("Controller 加入书架失败！");
        }
        return theResult;
    }

    //查询用户收藏到书架的数量
    @RequestMapping("/selectBookshelfInfoByUserId/{user_id}")
    @ResponseBody
    public int selectBookshelfInfoByUserId(@PathVariable(value = "user_id") int user_id) {

        System.out.println("查询书架Controller");
        System.out.println("用户ID：" + user_id);
        int theResult = -2;
        if (bookshelfInfoService.selectBookshelfInfoByUserId(user_id) >= -1) {

            theResult = bookshelfInfoService.selectBookshelfInfoByUserId(user_id);
        }
        return theResult;
    }

    //移出书架
    @RequestMapping("/deleteBookshelfWorkByWorkId/{work_id}/{user_id}")
    @ResponseBody
    public Boolean deleteBookshelfWorkByWorkId(@PathVariable(value = "work_id") List<Integer> work_id, @PathVariable(value = "user_id") int user_id) {

        boolean Result = false;
        for (int id : work_id) {
            Result = bookshelfInfoService.deleteBookshelfWorkByWorkId(id,user_id);
        }
        return Result;
    }

    //根据用户id删除收藏的书架内容
    @RequestMapping("/deleteBookshelfWorkByUserId/{user_id}")
    @ResponseBody
    public Boolean deleteBookshelfWorkByUserId(@PathVariable(value = "user_id") int user_id) {
        boolean theResult = false;
        if (bookshelfInfoService.deleteBookshelfWorkByUserId(user_id)) {
            theResult = true;
        }
        return theResult;
    }


    //跳转到书架界面
    @RequestMapping(path = "/bookshelfInterface")
    public String bookshelfInterface() {
        return "bookshelf.html";
    }

    //跳转到历史记录界面
    @RequestMapping(path = "/readingHistoryInterface")
    public String readingHistoryInterface() {
        return "readingHistory.html";
    }

}
