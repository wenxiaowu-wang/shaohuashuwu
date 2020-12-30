package com.shaohuashuwu.controller;

import com.shaohuashuwu.service.NoticeStateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/11/1
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/noticeStateInfoController")
@SessionAttributes(value = {"user_name","user_id","head_portrait"},types = {String.class,Integer.class})
public class NoticeStateInfoController {

    @Autowired
    public NoticeStateInfoService noticeStateInfoService;

    //将对应的一条通知消息标记为已读 【增加 viewer_id】
    @RequestMapping(path = "/updateOneReadState/{notice_id}")
    @ResponseBody
    public boolean updateOneReadState(ModelMap modelMap,@PathVariable(value = "notice_id")Integer notice_id){
        Integer viewer_id = (Integer)modelMap.get("user_id");
        return noticeStateInfoService.updateReadStateByNoticeIdAndViewerId(notice_id,viewer_id);
    }

    //将对应的一条通知消息 标记为已删除 【增加 viewer_id】
    @RequestMapping(path = "/updateOneDeleteState/{notice_id}")
    @ResponseBody
    public boolean updateOneDeleteState(ModelMap modelMap,@PathVariable(value = "notice_id")Integer notice_id){
        Integer viewer_id = (Integer)modelMap.get("user_id");
        return noticeStateInfoService.updateDeleteStateByNoticeIdAndViewerId(notice_id,viewer_id);
    }

    //将对应类型的消息置为已读 【删除该用户ID user_id】
    @RequestMapping(path = "/updateAllReadStateOfNoticeType/{notice_type}")
    @ResponseBody
    public boolean updateAllReadStateOfNoticeType(ModelMap modelMap,@PathVariable(value = "notice_type")Integer notice_id){
        Integer viewer_id = (Integer)modelMap.get("user_id");
        return noticeStateInfoService.updateAllReadStateByViewerIdAndNoticeType(viewer_id,notice_id);
    }

    //将对应类型的消息置为已删除 【删除该用户ID user_id】
    @RequestMapping(path = "/updateAllDeleteStateOfNoticeType/{notice_type}")
    @ResponseBody
    public boolean updateAllDeleteStateOfNoticeType(ModelMap modelMap,@PathVariable(value = "notice_type")Integer notice_type){
        Integer viewer_id = (Integer)modelMap.get("user_id");
        return noticeStateInfoService.updateAllDeleteStateByViewerIdAndNoticeType(viewer_id,notice_type);
    }

}
