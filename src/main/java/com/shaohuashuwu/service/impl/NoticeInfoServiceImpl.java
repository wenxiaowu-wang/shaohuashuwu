package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.NoticeInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.domain.vo.NoticeInfoVo;
import com.shaohuashuwu.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    public UserInfoDao userInfoDao;

    //获取该用户所有的通知信息
    @Override
    public List<NoticeInfoVo> getAllNoticeInfo(int user_id) {
        List<NoticeInfoVo> getResult = new ArrayList<NoticeInfoVo>();
        List<NoticeInfo> noticeInfoList = noticeInfoDao.selectAllNoticeInfoByUserId(user_id);
        //编制信息值对象
        String user_name = "";
        if (noticeInfoList.size()!=0){
            for (int i=0;i<noticeInfoList.size();i++){
                user_name = userInfoDao.selectUserNameById(noticeInfoList.get(i).getSend_by());
                NoticeInfoVo noticeInfoVo = new NoticeInfoVo();
                noticeInfoVo.setNotice_id(noticeInfoList.get(i).getNotice_id());
                noticeInfoVo.setSend_by(noticeInfoList.get(i).getSend_by());
                noticeInfoVo.setNotice_type(noticeInfoList.get(i).getNotice_type());
                noticeInfoVo.setNotice_content(noticeInfoList.get(i).getNotice_content());
                noticeInfoVo.setNotice_title(noticeInfoList.get(i).getNotice_title());
                //TimeStamp转化为String类型
                noticeInfoVo.setSend_time(noticeInfoList.get(i).getSend_time().toString());
                noticeInfoVo.setNotice_tip(noticeInfoList.get(i).getNotice_tip());
                noticeInfoVo.setSend_by_name(user_name);
                getResult.add(noticeInfoVo);    //装配关注信息值对象
            }
        }
        return getResult;
    }

    //该用户全部消息置为已读(未使用)
    @Override
    public boolean updateAllNotice(int user_id) {
        boolean updateResult = false;
        if (noticeInfoDao.updateAllNoticeTipByUserId(user_id)!=(0)){
            updateResult = true;
        }
        return updateResult;
    }

    //该用户对应类型消息提醒全部置为已读
    @Override
    public boolean updateAllNoticeByIdAndType(int user_id, int notice_type) {
        boolean updateResult = false;
        if (noticeInfoDao.updateAllNoticeTipByIdAndType(user_id,notice_type)!=(0)){
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

    //删除该用户对应类型的所有消息提醒
    @Override
    public boolean deleteAllNoticeByIdAndType(int user_id, int notice_type) {
        boolean deleteResult = false;
        if (noticeInfoDao.deleteAllNoticeInfoByIdAndType(user_id,notice_type)!=(0)){
            deleteResult = true;
        }
        return deleteResult;
    }

    //删除该用户收到的所有对应消息(未使用)
    @Override
    public boolean deleteAllNotice(int user_id) {
        boolean deleteResult = false;
        if (noticeInfoDao.deleteNoticeInfoByUserId(user_id)!=(0)){
            deleteResult = true;
        }
        return deleteResult;
    }
}
