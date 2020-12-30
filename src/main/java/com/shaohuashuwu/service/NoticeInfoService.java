package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.NoticeInfo;
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

    //添加一条未读消息
    public boolean addOneNewNotice(NoticeInfo noticeInfo);

    //添加或更新作品更新消息
    public boolean addOrUpdateWorkUpdateNotice(NoticeInfo noticeInfo);


}
