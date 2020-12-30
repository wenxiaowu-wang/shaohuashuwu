package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.domain.vo.NoticeInfoVo;
import com.shaohuashuwu.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Timestamp;
import java.util.Date;
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
@SessionAttributes(value = {"user_name","user_id","head_portrait"},types = {String.class,Integer.class})
public class NoticeInfoController {

    @Autowired
    public NoticeInfoService noticeInfoService;

    //获取该用户所有的通知信息
    @RequestMapping(path = "/getAllNoticeInfo")
    @ResponseBody
    public List<NoticeInfoVo> getAllNoticeInfo(ModelMap modelMap){
        Integer user_id = (Integer) modelMap.get("user_id");
        return noticeInfoService.getAllNoticeInfo(user_id);
    }
/*
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
*/
    //发送一条信息（私信）
    @RequestMapping(path = "/addOnePrivateLetter/{send_to}/{title}/{content}")
    @ResponseBody
    public boolean addOnePrivateLetter(ModelMap modelMap,@PathVariable(value = "send_to")Integer send_to,@PathVariable(value = "title")String title,@PathVariable(value = "content")String content){
        Integer user_id = (Integer)modelMap.get("user_id");
        NoticeInfo noticeInfo = new NoticeInfo();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        noticeInfo.setSend_by(user_id);
        noticeInfo.setSend_to(send_to);
        noticeInfo.setNotice_type(3);//设置该消息类型为私信
        noticeInfo.setNotice_content(content);
        noticeInfo.setNotice_title(title);
        noticeInfo.setSend_time(timestamp);
        noticeInfo.setNotice_tip(1);//设置该消息未读
        return noticeInfoService.addOneNewNotice(noticeInfo);
    }

    //跳转到消息中心页面
    @RequestMapping(path = "/toMessageCenterInterface")
    public String toMessageCenterInterface(){
        return "messageCenterInterface.html";
    }


}
