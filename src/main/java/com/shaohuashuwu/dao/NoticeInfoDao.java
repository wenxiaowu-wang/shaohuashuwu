package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.NoticeInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
//    @Select("SELECT DISTINCT notice_info.notice_id AS notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_state_info.read_state AS notice_tip \n" +
//            "FROM notice_info,bookshelf_info,works_info,attention_info,notice_state_info \n" +
//            "WHERE notice_info.notice_id = notice_state_info.notice_id AND \n" +
//            "(send_to = viewer_id AND viewer_id = #{param1} AND delete_state = 0) OR \n" +
//            "(notice_type = 2 AND send_by = bookshelf_info.work_id AND bookshelf_info.user_id = #{param1} AND viewer_id = #{param1} AND delete_state = 0) OR\n" +
//            "(notice_type = 2 AND notice_type = 2 AND send_by = works_info.work_id AND works_info.user_id = attention_info.author_id AND attention_info.reader_id = #{param1} AND viewer_id = #{param1} AND delete_state = 0) ORDER BY read_state ASC,send_time DESC")

    //获取该用户所有的通知信息
    @Select("(SELECT DISTINCT notice_info.notice_id AS notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_state_info.read_state AS notice_tip \n" +
            "FROM notice_info,notice_state_info WHERE notice_info.notice_id = notice_state_info.notice_id AND viewer_id = send_to AND notice_type = 1 AND send_by = 0 AND delete_state = 0 AND send_to = #{param1})\n" +
            "UNION\n" +
            "(SELECT DISTINCT notice_info.notice_id AS notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_state_info.read_state AS notice_tip\n" +
            "FROM notice_info,notice_state_info WHERE notice_info.notice_id = notice_state_info.notice_id AND notice_info.notice_type = 2 AND notice_info.send_by IN(\n" +
            "SELECT DISTINCT works_info.work_id AS work_id FROM works_info,attention_info WHERE attention_info.author_id = works_info.user_id AND attention_info.reader_id = #{param1}\n" +
            "UNION\n" +
            "SELECT DISTINCT bookshelf_info.work_id AS work_id FROM bookshelf_info WHERE bookshelf_info.user_id = #{param1})AND delete_state = 0)\n" +
            "UNION\n" +
            "(SELECT DISTINCT notice_info.notice_id AS notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_state_info.read_state AS notice_tip\n" +
            "FROM notice_info,notice_state_info WHERE notice_info.notice_id = notice_state_info.notice_id AND viewer_id = notice_info.send_to AND notice_state_info.delete_state = 0 AND notice_info.notice_type = 3 AND notice_info.send_to = #{param1})")
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

    //当消息类型为更新提醒时，根据发送者ID（作品ID）获取消息ID
    @Select("SELECT IFNULL((SELECT notice_id FROM notice_info WHERE send_by = #{send_by} AND notice_type = 2),-1) AS notice_id")
    public int selectWorkUpdateNoticeIdBySendBy(int send_by);

    //获取该用户所有的通知信息值对象(猜测获取不成功，因为数据库不对应)

    //插入一条通知消息
    @Insert("insert into notice_info(notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_tip)" +
            "values(#{notice_id},#{send_by},#{send_to},#{notice_type},#{notice_content},#{notice_title},#{send_time},#{notice_tip})")
    public int insertOneNoticeInfo(NoticeInfo noticeInfo);

    @Insert("REPLACE INTO notice_info(notice_id,send_by,send_to,notice_type,notice_content,notice_title,send_time,notice_tip)" +
            "values(#{notice_id},#{send_by},#{send_to},#{notice_type},#{notice_content},#{notice_title},#{send_time},#{notice_tip})")
    public int insertOrUpdateOneNoticeInfo(NoticeInfo noticeInfo);

    //根据发送者、接受者、消息类型获取对应的最近的一条消息
    @Select("SELECT * FROM notice_info WHERE send_by = #{param1} AND send_to = #{param2} AND notice_type = #{param3} ORDER BY send_time DESC LIMIT 0,1")
    public NoticeInfo selectRecentTimeNoticeInfoBySendByToAndType(int send_by,int send_to,int notice_type);


}
