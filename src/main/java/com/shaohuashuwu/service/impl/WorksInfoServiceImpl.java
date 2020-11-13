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

}
