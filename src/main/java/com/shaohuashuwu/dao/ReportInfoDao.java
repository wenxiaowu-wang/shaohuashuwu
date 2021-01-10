package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ReportInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface ReportInfoDao {


    //修改举报信息状态
    //功能点：处理作品信息修改处理信息，
    @Update("UPDATE report_info SET handle_state = #{handle_state},report_time = #{report_time} WHERE report_id = #{report_id} ")
    public int updateReportInfoByReport_id(ReportInfo reportInfo);


    //通过举报id获取章节id
    //功能点：处理作品信息修改处理信息，
    @Select("select chapter_id from report_info where report_id = #{report_id}")
    public int selectChapter_idByReport_id(Integer report_id);

    /**
     * 阿斌
     */
    //插入一条举报信息
    @Insert("insert into report_info(report_id,user_id,chapter_id,report_reason,report_time,report_remarks,handle_state)" +
            "values(#{report_id},#{user_id},#{chapter_id},#{report_reason},#{report_time},#{report_remarks},#{handle_state})")
    public int insertReportInfo(ReportInfo reportInfo);

}
