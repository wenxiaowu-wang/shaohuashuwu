package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ReportInfo;
import org.apache.ibatis.annotations.Insert;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ReportInfoDao {

    //插入一条举报信息
    @Insert("insert into report_info(report_id,user_id,chapter_id,report_reason,report_time,report_remarks)" +
            "values(#{report_id},#{user_id},#{chapter_id},#{report_reason},#{report_time},#{report_remarks})")
    public int insertReportInfo(ReportInfo reportInfo);

}
