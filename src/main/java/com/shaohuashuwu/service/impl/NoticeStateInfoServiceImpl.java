package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.NoticeStateInfoDao;
import com.shaohuashuwu.service.NoticeStateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/12/10
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("noticeStateInfoService")
public class NoticeStateInfoServiceImpl implements NoticeStateInfoService {

    @Autowired
    public NoticeStateInfoDao noticeStateInfoDao;

    //插入或更新某作品的相关提信息状态
    @Override
    public boolean addOrUpdateNoticeStateInfoByWorkId(int work_id) {
        boolean theResult = false;
        if (noticeStateInfoDao.insertOrUpdateNoticeStateInfoByWorkId(work_id)!=(-1)){
            /*因为是用的insert into ... on duplicate key update ,
            有可能出现存在而且不用修改的情况，或存在需要修改，修改后不影响记录排序的情况.
            所以在这里用 -1 来判断（一般插入数据不会出错，网络延迟等其它情况除外）
            */
           theResult = true;
        }
        return theResult;
    }

    //根据消息ID和接受者ID将消息置为已读状态
    @Override
    public boolean updateReadStateByNoticeIdAndViewerId(int notice_id, int viewer_id) {
        boolean theResult = false;
        if (noticeStateInfoDao.updateReadStateByNoticeIdAndViewerId(notice_id,viewer_id,1)!=(-1)){
            //为没有修改留个余地（没有修改表示已经值为空）
            theResult = true;
        }
        return theResult;
    }

    //根据消息ID和接受者ID将消息置为删除状态
    @Override
    public boolean updateDeleteStateByNoticeIdAndViewerId(int notice_id, int viewer_id) {
        boolean theResult = false;
        if (noticeStateInfoDao.updateDeleteStateByNoticeIdAndViewerId(notice_id,viewer_id,1)!=(-1)){
            //为没有修改留个余地（没有修改表示已经值为空）
            theResult = true;
        }
        return theResult;
    }

    //该用户对应类型消息提醒全部置为已读 【已读某类型所有】
    @Override
    public boolean updateAllReadStateByViewerIdAndNoticeType(int viewer_id, int notice_type) {
        boolean theResult = false;
        if (noticeStateInfoDao.updateAllReadStateByViewerIdAndNoticeType(viewer_id,notice_type,1)!=(-1)){
            //为没有修改留个余地（没有修改表示已经值为空）
            theResult = true;
        }
        return theResult;
    }

    //该用户对应类型消息提醒全部置为已删除 【删除某类型所有】
    @Override
    public boolean updateAllDeleteStateByViewerIdAndNoticeType(int viewer_id, int notice_type) {
        boolean theResult = false;
        if (noticeStateInfoDao.updateAllDeleteStateByViewerIdAndNoticeType(viewer_id,notice_type,1)!=(-1)){
            //为没有修改留个余地（没有修改表示已经值为空）
            theResult = true;
        }
        return theResult;
    }
}
