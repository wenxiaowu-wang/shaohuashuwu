package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ReadingHistoryInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ReadingHistoryInfoDao {

    //根据用户ID获取对应阅读历史信息
    @Select("select * from reading_history_info where user_id = #{user_id}")
    @Results(id = "readingHistoryInfo",value = {
            @Result(id = true,column = "user_id",property = "user_id"),
            @Result(column = "work_id",property = "work_id"),
            @Result(column = "reading_time",property = "reading_time")
    })
    public List<ReadingHistoryInfo> selectReadingHistoryInfoByUserId(int user_id);

    //根据作品ID和读者性别获取对应读者年龄段分布
    @Select("SELECT COUNT(DISTINCT reading_history_info.user_id) AS reader_num,ELT(INTERVAL(TIMESTAMPDIFF(YEAR,birthday,NOW()),0,10,18,25,35,200),'10岁以下','10~18岁','18~25岁','25~35岁','35岁及以上') AS age_type FROM user_info,reading_history_info WHERE user_info.user_id = reading_history_info.user_id AND gender LIKE #{param2} AND work_id = #{param1} GROUP BY age_type ORDER BY age_type ASC")
    public List<Map<String,Object>> selectReaderAgeDistributionByWorkIdAndGender(int work_id,String gender);

    //根据作品ID和读者性别获取对应读者阅读时间段
    @Select("SELECT DATE_FORMAT(reading_time,'%H')date_hour,COUNT(DISTINCT reading_history_info.user_id)reader_num FROM reading_history_info,user_info WHERE reading_history_info.user_id = user_info.user_id AND work_id = #{param1} AND gender LIKE #{param2} GROUP BY date_hour ORDER BY date_hour ASC")
    public List<Map<String,Object>> selectReadingTimeDistributionByWorkIdAndGender(int work_id,String gender);
}
