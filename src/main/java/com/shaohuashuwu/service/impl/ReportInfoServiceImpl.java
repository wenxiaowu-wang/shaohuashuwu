package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ReportInfoDao;
import com.shaohuashuwu.domain.ReportInfo;
import com.shaohuashuwu.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("reportInfoService")
public class ReportInfoServiceImpl implements ReportInfoService {

    @Autowired
    public ReportInfoDao reportInfoDao;

    //保存一条举报信息
    @Override
    public boolean addReportInfo(ReportInfo reportInfo) {
        boolean addResult = false;
        if (reportInfoDao.insertReportInfo(reportInfo)!=(0)){
            addResult = true;
        }
        return addResult;
    }

}
