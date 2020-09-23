package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.NoticeInfoDao;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("noticeInfoService")
public class NoticeInfoServiceImpl implements NoticeInfoService {

    @Autowired
    public NoticeInfoDao noticeInfoDao;

    //获取该用户所有的通知信息
    @Override
    public List<NoticeInfo> getAllNoticeInfo(int user_id) {
        return noticeInfoDao.selectAllNoticeInfoByUserId(user_id);
    }

    //该用户全部消息置为已读
    @Override
    public boolean updateAllNotice(int user_id) {
        boolean updateResult = false;
        if (noticeInfoDao.updateAllNoticeTipByUserId(user_id)!=(0)){
            updateResult = true;
        }
        return updateResult;
    }

    //删除该用户的一条消息
    @Override
    public boolean deleteOneNotice(int notice_id) {
        boolean deleteResult = false;
        if (noticeInfoDao.deleteNoticeInfoByNoticeId(notice_id)!=(0)){
            deleteResult = true;
        }
        return deleteResult;
    }

    //删除该用户收到的所有对应消息
    @Override
    public boolean deleteAllNotice(int user_id) {
        boolean deleteResult = false;
        if (noticeInfoDao.deleteNoticeInfoByUserId(user_id)!=(0)){
            deleteResult = true;
        }
        return deleteResult;
    }
}
