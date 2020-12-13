package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.vo.AdminSelectInfoVo;
import com.shaohuashuwu.domain.vo.ReportWholeInfoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportWholeInfoVoDao {

    //查询所有举报信息
    //功能点：获取举报信息列表
    @Select(
            "SELECT r1.report_id report_id,r1.report_time report_time, " +
            " (SELECT w1.work_name FROM works_info w1 WHERE w1.work_id = (SELECT cp1.work_id FROM chapter_post_info cp1 WHERE r1.chapter_id = cp1.chapter_id)) work_name, " +
            " (SELECT u1.user_name FROM user_info u1 WHERE r1.user_id = u1.user_id) user_nam, " +
            " (SELECT c1.chapter_title FROM chapter_info c1 WHERE r1.chapter_id = c1.chapter_id) chapter_title " +
            " FROM report_info r1 " +
            " WHERE r1.handle_state = 1 "
            )
    public List<ReportWholeInfoVo> selectReportWholeInfoVoList(AdminSelectInfoVo adminSelectInfoVo);


    //查询所有举报信息
    //功能点：获取举报信息数量
    @Select("SELECT count(*) FROM report_info WHERE handle_state = 1 " )
    public int selectReportWholeInfoVoTotal();

    //依据举报id获取举报信息
    //功能点：举报详情获取举报信息
    @Select("SELECT  r1.report_id report_id,r1.user_id user_id,r1.chapter_id chapter_id,r1.report_reason report_reason,r1.report_time report_time,r1.report_remarks report_remarks,r1.handle_state handle_state, " +
            " (SELECT w1.work_name FROM works_info w1 WHERE w1.work_id = (SELECT cp1.work_id FROM chapter_post_info cp1 WHERE r1.chapter_id = cp1.chapter_id)) work_name,  " +
            " (SELECT u1.user_name FROM user_info u1 WHERE r1.user_id = u1.user_id) user_nam, " +
            " (SELECT c1.chapter_title FROM chapter_info c1 WHERE r1.chapter_id = c1.chapter_id) chapter_title, " +
            " (SELECT c2.chapter_content FROM chapter_info c2 WHERE r1.chapter_id = c2.chapter_id) chapter_content " +
            " FROM report_info r1  " +
            " WHERE r1.report_id = #{report_id}" )
    public ReportWholeInfoVo selectreportWholeInfoVoByreport_id(int report_id);

    //查询处理结果信息
    //功能点：更改处理结果的举报信息列表
    @Select({
            "<script>",
            "SELECT r1.report_id report_id,r1.report_time report_time, " +
                    " (SELECT w1.work_name FROM works_info w1 WHERE w1.work_id = (SELECT cp1.work_id FROM chapter_post_info cp1 WHERE r1.chapter_id = cp1.chapter_id)) work_name, " +
                    " (SELECT u1.user_name FROM user_info u1 WHERE r1.user_id = u1.user_id) user_nam, " +
                    " (SELECT c1.chapter_title FROM chapter_info c1 WHERE r1.chapter_id = c1.chapter_id) chapter_title " +
                    " FROM report_info r1 " +
                    " WHERE r1.handle_state != 1 ",
                    " <if test='work_name != null and work_name != \" \" and work_name != \"\" '>",
                    " AND r1.chapter_id IN " +
                    "   (SELECT cp1.chapter_id FROM chapter_post_info cp1 WHERE cp1.work_id = " +
                            " (SELECT w2.work_id FROM works_info w2 WHERE w2.work_name = #{work_name}))",
                    " </if>",
                    " <if test='report_time != null and report_time != \" \" and report_time != \"\" '>",
                    " AND r1.report_time LIKE '%${report_time}%' ",
                    " </if>",
            " </script>"
    })
    public List<ReportWholeInfoVo> selecthandleResultList(AdminSelectInfoVo adminSelectInfoVo);


    //查询所有处理结果数量
    //功能点：更改处理结果的举报信息数量
    @Select({
            "<script>",
            "SELECT count(*) FROM report_info r1 WHERE r1.handle_state != 1 ",
            " <if test='work_name != null and work_name != \" \" and work_name != \"\" '>",
            " AND r1.chapter_id IN " +
                    " (SELECT cp1.chapter_id FROM chapter_post_info cp1 WHERE cp1.work_id = " +
                    " (SELECT w2.work_id FROM works_info w2 WHERE w2.work_name = #{work_name}))",
            " </if>",
            " <if test='report_time != null and report_time != \" \" and report_time != \"\" '>",
            " AND r1.report_time LIKE '%${report_time}%' ",
            " </if>",
            " </script>"
        })
    public int selecthandleResultListTotal(AdminSelectInfoVo adminSelectInfoVo);
}
