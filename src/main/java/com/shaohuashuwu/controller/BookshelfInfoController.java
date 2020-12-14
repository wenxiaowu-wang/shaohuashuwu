package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.BookshelfInfoService;
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
@RequestMapping(path = "/bookshelfInfoController")
public class BookshelfInfoController {

    @Autowired
    public BookshelfInfoService bookshelfInfoService;

    @RequestMapping(path = "/getReaderLikeDistributionByWorkId/{work_id}")
    @ResponseBody
    public Map<String, List<Map<String,Object>>> getReaderLikeDistributionByWorkId(@PathVariable(value = "work_id")int work_id){
        List<String> type = new ArrayList<String>();
        type.add("玄幻");
        type.add("奇幻");
        type.add("武侠");
        type.add("仙侠");
        type.add("都市");
        type.add("历史");
        type.add("军事");
        type.add("悬疑");
        type.add("科幻");
        type.add("游戏");
        type.add("体育");
        type.add("现实");
        type.add("轻小说");
        return bookshelfInfoService.getReaderLikeDistributionByWorkIdAndGender(type,work_id);
    }

}
