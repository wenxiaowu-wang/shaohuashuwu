package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.NoticeInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface NoticeInfoDao {

    //获取该用户所有的通知信息
    @Select("select * from notice_info where send_to = #{user_id}")
    @Results(id = "noticeInfo",value = {
            @Result(id = true,column = "notice_id",property = "notice_id"),
            @Result(column = "send_by",property = "send_by"),
            @Result(column = "send_to",property = "send_to"),
            @Result(column = "notice_type",property = "notice_type"),
            @Result(column = "notice_content",property = "notice_content"),
            @Result(column = "notice_title",property = "notice_title"),
            @Result(column = "send_time",property = "send_time"),
            @Result(column = "notice_tip",property = "notice_tip"),
    })
    public List<NoticeInfo> selectAllNoticeInfoByUserId(int user_id);

    //获取该用户所有的通知信息值对象(猜测获取不成功，因为数据库不对应)

    //更新所有该用户的通知提示(取消该用户所有通知的提示)
    @Update("update from notice_info set notice_tip = 0 where send_to = #{user_id}")
    public int updateAllNoticeTipByUserId(int user_id);

    //更新一条通知提示（取消提示）--未用到
    @Update("update from notice_info set notice_tip = 0 where notice_id = #{notice_id}")
    public int updateOneNoticeTipByNoticeId(int notice_id);

    //删除一条通知信息
    @Delete("delete from notice_info where notice_id = #{notice_id}")
    public int deleteNoticeInfoByNoticeId(int notice_id);

    //删除该用户所有通知
    @Delete("delete from notice_info where send_to = #{user_id}")
    public int deleteNoticeInfoByUserId(int user_id);

    //插入一条通知消息
    @Insert("insert into notice_info(notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_tip)" +
            "values(#{notice_id},#{send_by},#{send_to},#{notice_type},#{notice_content},#{notice_title},#{send_time},#{notice_tip})")
    public int insertOneNoticeInfo(NoticeInfo noticeInfo);



}
