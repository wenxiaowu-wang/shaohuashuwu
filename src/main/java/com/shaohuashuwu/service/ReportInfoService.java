package com.shaohuashuwu.service;


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

}
