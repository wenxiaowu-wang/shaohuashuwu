package com.shaohuashuwu.service;


import com.shaohuashuwu.domain.ReportInfo;
import org.springframework.stereotype.Service;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ReportInfoService {

    //修改处理信息
    //功能点：处理作品信息修改处理信息，
    public int updateReportInfoByReport_id(Integer report_id,Integer handle_state);

    /**
     * 阿斌
     */

    //保存一条举报信息
    public boolean addReportInfo(ReportInfo reportInfo);

    //举报章节信息
    public int reportChapter(int user_id,int chapter_id,int work_id,int reason);

}
