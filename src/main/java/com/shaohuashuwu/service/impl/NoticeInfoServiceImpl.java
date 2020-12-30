package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.NoticeInfoDao;
import com.shaohuashuwu.dao.NoticeStateInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.NoticeInfo;
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

    @Autowired
    public WorksInfoDao worksInfoDao;

    @Autowired
    public NoticeStateInfoDao noticeStateInfoDao;

    //获取该用户所有的通知信息
    @Override
    public List<NoticeInfoVo> getAllNoticeInfo(int user_id) {
        List<NoticeInfoVo> getResult = new ArrayList<NoticeInfoVo>();
        List<NoticeInfo> noticeInfoList = noticeInfoDao.selectAllNoticeInfoByUserId(user_id);


        for (NoticeInfo noticeInfo : noticeInfoList) {
            //编制信息值对象
            NoticeInfoVo noticeInfoVo = new NoticeInfoVo();
            StringBuilder user_name = new StringBuilder("");
            switch (noticeInfo.getNotice_type()) {
                case 1:
                case 3: {
                    user_name.append(userInfoDao.selectUserNameById(noticeInfo.getSend_by()));
                    break;
                }
                case 2: {
                    user_name.append("《").append(worksInfoDao.selectWorkNameByWorkId(noticeInfo.getSend_by())).append("》");
                    break;
                }
            }

            noticeInfoVo.setNotice_id(noticeInfo.getNotice_id());
            noticeInfoVo.setSend_by(noticeInfo.getSend_by());
            noticeInfoVo.setNotice_type(noticeInfo.getNotice_type());
            noticeInfoVo.setNotice_content(noticeInfo.getNotice_content());
            noticeInfoVo.setNotice_title(noticeInfo.getNotice_title());
            //TimeStamp转化为String类型(通过substirng去掉毫秒值)
            noticeInfoVo.setSend_time(noticeInfo.getSend_time().toString().substring(0, noticeInfo.getSend_time().toString().indexOf(".")));
            noticeInfoVo.setNotice_tip(noticeInfo.getNotice_tip());
            noticeInfoVo.setSend_by_name(user_name.toString());
            getResult.add(noticeInfoVo);    //装配关注信息值对象
        }

        return getResult;
    }

    //添加一条未读消息以及和该消息关联的消息状态信息
    @Override
    public boolean addOneNewNotice(NoticeInfo noticeInfo) {
        boolean addResult = false;
        if (noticeInfoDao.insertOneNoticeInfo(noticeInfo) != 0){
            //添加未读消息到notice_info成功
            //这里怎么处理事务的回滚？待解答
            NoticeInfo noticeInfo1 = noticeInfoDao.selectRecentTimeNoticeInfoBySendByToAndType(noticeInfo.getSend_by(),noticeInfo.getSend_to(),noticeInfo.getNotice_type());
            if (noticeInfo1 != null){
                if (noticeStateInfoDao.insertOrdinaryNoticeStateInfo(noticeInfo1.getNotice_id(), noticeInfo1.getSend_by(), noticeInfo1.getSend_to()) != 0){
                    //插入消息状态信息表中成功
                    addResult = true;
                }
            }
        }
        return addResult;
    }

    //添加或更新作品更新消息
    @Override
    public boolean addOrUpdateWorkUpdateNotice(NoticeInfo noticeInfo) {
        boolean theResult = false;
        int getNoticeId = noticeInfoDao.selectWorkUpdateNoticeIdBySendBy(noticeInfo.getSend_by());
        if (getNoticeId != -1){
            //表示获取到消息ID，即表中已有该作品的更新通知记录(若表中没有该作品的更新通知记录，接收值为-1)
            noticeInfo.setNotice_id(getNoticeId);   //补充传入参数的消息ID,因为若没有主键或者主索引，无法使用更新或插入函数。
        }
        if (noticeInfoDao.insertOrUpdateOneNoticeInfo(noticeInfo) != 0){
            //表示添加或更新成功
            theResult = true;
        }
        return theResult;
    }
}
