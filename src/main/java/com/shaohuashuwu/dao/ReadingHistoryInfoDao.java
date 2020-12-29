package com.shaohuashuwu.dao;


import com.shaohuashuwu.domain.ReadingHistoryInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface ReadingHistoryInfoDao {

    //根据用户ID获取对应阅读历史信息
    @Select("select * from reading_history_info where user_id = #{user_id}")
    @Results(id = "readingHistoryInfo",value = {
            @Result(id = true,column = "user_id",property = "user_id"),
            @Result(column = "work_id",property = "work_id"),
            @Result(column = "reading_time",property = "reading_time")
    })
    public List<ReadingHistoryInfo> selectReadingHistoryInfoByUserId(int user_id);

    //阅读浏览历史存入到阅读历史表
    @Insert("insert into reading_history_info(user_id,work_id,reading_time)values(#{user_id},#{work_id},#{reading_time})")
    public int insertReadingHistoryInfoByUserId(ReadingHistoryInfo readingHistoryInfo);

    //根据用户id判断以前是否看过这本小说（查看数据库该记录书否存在）
    @Select("select count(*) from reading_history_info where user_id = #{param1} AND work_id = #{param2}")
    public int isReadingHistoryInfoExistByUserId(int user_id,int work_id);

    //查询数据库某个用户阅读历史的总条数
    @Select("select count(*) from reading_history_info where user_id = #{user_id}")
    public int selectReadingHistoryInfoAllCountByUserId(int user_id);

    //如果数据库已存在某个小说 只需调用此更新时间操作就ok
    @Update("update reading_history_info set reading_time = #{param1} where user_id = #{param2} AND work_id = #{param3}")
    public int updatePersonalDataByID(Timestamp reading_time, int user_id, int work_id);

    //删除单个记录
    @Delete("DELETE FROM reading_history_info WHERE work_id = #{work_id}")
    public Boolean deleteHistoryWorkByWorkId(int work_id);

    //删除所有
    @Delete("DELETE FROM reading_history_info WHERE user_id = #{user_id}")
    public Boolean deleteHistoryWorkByUserId(int user_id);

    //根据user_id查询该用户 阅读历史  共有几本书
    @Select("select count(*) from reading_history_info where user_id = #{user_id}")
    public int selectReadingHistoryCountByUserId(int user_id);


}
