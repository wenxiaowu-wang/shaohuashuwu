package com.shaohuashuwu.service;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/12/10
 * 项目:shaohuashuwu
 * 描述:
 */
public interface NoticeStateInfoService {

    //插入或更新某作品的相关提信息状态
    public boolean addOrUpdateNoticeStateInfoByWorkId(int work_id);

    //根据消息ID和接受者ID将消息置为已读状态 【已读一条】
    public boolean updateReadStateByNoticeIdAndViewerId(int notice_id,int viewer_id);

    //根据消息ID和接受者ID将消息置为删除状态 【删除一条】
    public boolean updateDeleteStateByNoticeIdAndViewerId(int notice_id,int viewer_id);

    //该用户对应类型消息提醒全部置为已读 【已读某类型所有】
    public boolean updateAllReadStateByViewerIdAndNoticeType(int viewer_id,int notice_type);

    //该用户对应类型消息提醒全部置为已删除 【删除某类型所有】
    public boolean updateAllDeleteStateByViewerIdAndNoticeType(int viewer_id,int notice_type);

}
