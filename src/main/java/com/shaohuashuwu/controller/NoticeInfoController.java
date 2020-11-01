package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.vo.NoticeInfoVo;
import com.shaohuashuwu.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/11/1
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/noticeInfoController")
public class NoticeInfoController {

    @Autowired
    public NoticeInfoService noticeInfoService;

    //获取该用户所有的通知信息
    @RequestMapping(path = "/getAllNoticeInfo/{user_id}")
    @ResponseBody
    public List<NoticeInfoVo> getAllNoticeInfo(@PathVariable(value = "user_id") Integer user_id){
        return noticeInfoService.getAllNoticeInfo(user_id);
    }

    //跳转到消息中心页面
    @RequestMapping(path = "/toMessageCenterInterface")
    public String toMessageCenterInterface(){
        return "messageCenterInterface.html";
    }
}
