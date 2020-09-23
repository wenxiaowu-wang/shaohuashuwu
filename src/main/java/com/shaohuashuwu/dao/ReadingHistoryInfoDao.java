package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ReadingHistoryInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
}
