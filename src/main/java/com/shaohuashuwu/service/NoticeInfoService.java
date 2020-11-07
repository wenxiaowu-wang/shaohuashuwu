package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.NoticeInfoVo;

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
    public List<NoticeInfoVo> getAllNoticeInfo(int user_id);

    //该用户全部消息置为已读(未使用)
    public boolean updateAllNotice(int user_id);

    //该用户对应类型消息提醒全部置为已读
    public boolean updateAllNoticeByIdAndType(int user_id,int notice_type);

    //删除该用户的一条消息
    public boolean deleteOneNotice(int notice_id);

    //删除该用户对应类型的所有消息提醒
    public boolean deleteAllNoticeByIdAndType(int user_id,int notice_type);

    //删除该用户收到的所有对应消息(未使用)
    public boolean deleteAllNotice(int user_id);

}
