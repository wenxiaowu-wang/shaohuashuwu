package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterInfoDao;
import com.shaohuashuwu.dao.ReportInfoDao;
import com.shaohuashuwu.domain.ReportInfo;
import com.shaohuashuwu.service.ReportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Autowired
    public ChapterInfoDao chapterInfoDao;

    ReportInfo reportInfo;

    /**
     * 下架上架时的做欧品设置，和章节状态修改
     * 功能点：处理作品信息修改处理信息，
     * @param
     * @return
     */
    @Override
    public int updateReportInfoByReport_id(Integer report_id,Integer handle_state) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Timestamp timestamp=Timestamp.valueOf(date);
        //驳回申请
        if (handle_state == 2){
            reportInfo = new ReportInfo(report_id,timestamp,handle_state);
            return reportInfoDao.updateReportInfoByReport_id(reportInfo);
        }
        //同意申请处理,下架章节
        else if(handle_state == 3){
            reportInfo = new ReportInfo(report_id,timestamp,handle_state);
            int updateResult = reportInfoDao.updateReportInfoByReport_id(reportInfo);
            int chapter_id = reportInfoDao.selectChapter_idByReport_id(report_id);
            chapterInfoDao.updateChapter_stateByChapter_id(chapter_id);
            return updateResult;
        }
        //撤销吹结果，上架章节
        else if(handle_state == 4){
            reportInfo = new ReportInfo(report_id,timestamp,2);
            int updateResult = reportInfoDao.updateReportInfoByReport_id(reportInfo);
            int chapter_id = reportInfoDao.selectChapter_idByReport_id(report_id);
            chapterInfoDao.updateChapter_stateByChapter_id2(chapter_id);
            return updateResult;

        }
        return 0;
    }
}
