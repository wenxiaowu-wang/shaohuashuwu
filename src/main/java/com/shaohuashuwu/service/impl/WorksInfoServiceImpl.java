package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("worksInfoService")
public class WorksInfoServiceImpl implements WorksInfoService {

    @Autowired
    public WorksInfoDao worksInfoDao;

    //根据作者ID获取所有对应的作品信息
    @Override
    public List<WorksInfo> getAllWorkInfoOfAuthorId(int user_id) {
        return worksInfoDao.selectAllByUserId(user_id);
    }

    //根据用户ID获取用户加入书架的作品的作品名字
    @Override
    public List<WorksInfo> getBookshelfWorkNameByWorkID(int user_id) {
        return worksInfoDao.selectBookshelfWorkNameByWorkID(user_id);
    }

    //根据用户ID获取用户阅读历史的作品的作品名字
    @Override
    public List<WorksInfo> getReadingHistoryWorkNameByWorkID(int user_id) {
        return worksInfoDao.selectReadingHistoryWorkNameByWorkID(user_id);
    }

    @Override
    public List<WorksInfo> getWorkIdNameByUserId(int user_id) {
        return worksInfoDao.selectWorkIdNameByUserId(user_id);
    }

}
