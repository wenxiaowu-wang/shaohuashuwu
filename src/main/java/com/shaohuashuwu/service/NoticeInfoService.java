package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.NoticeInfo;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface NoticeInfoService {

    //获取该用户所有的通知信息
    public List<NoticeInfo> getAllNoticeInfo(int user_id);

    //该用户全部消息置为已读
    public boolean updateAllNotice(int user_id);

    //删除该用户的一条消息
    public boolean deleteOneNotice(int notice_id);

    //删除该用户收到的所有对应消息
    public boolean deleteAllNotice(int user_id);

}
