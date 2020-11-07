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

    //删除所有对应类型的通知消息
    @RequestMapping(path = "/deleteAllNoticeInfoByIdAndType/{user_id}/{notice_type}")
    @ResponseBody
    public boolean deleteAllNoticeInfoByIdAndType(@PathVariable(value = "user_id")Integer user_id,@PathVariable(value = "notice_type")Integer notice_id){
        return noticeInfoService.deleteAllNoticeByIdAndType(user_id,notice_id);
    }

    //删除对应的一条通知消息
    @RequestMapping(path = "/deleteOneNoticeInfo/{notice_id}")
    @ResponseBody
    public boolean deleteOneNoticeInfo(@PathVariable(value = "notice_id")Integer notice_id){
        return noticeInfoService.deleteOneNotice(notice_id);
    }

    //将对应类型的消息置为已读
    @RequestMapping(path = "/updateAllNoticeInfoByIdAndType/{user_id}/{notice_type}")
    @ResponseBody
    public boolean updateAllNoticeInfoByIdAndType(@PathVariable(value = "user_id")Integer user_id,@PathVariable(value = "notice_type")Integer notice_id){
        return noticeInfoService.updateAllNoticeByIdAndType(user_id,notice_id);
    }

    //跳转到消息中心页面
    @RequestMapping(path = "/toMessageCenterInterface")
    public String toMessageCenterInterface(){
        return "messageCenterInterface.html";
    }


}
