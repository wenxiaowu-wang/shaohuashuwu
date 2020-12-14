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
public interface NoticeStateInfoDao {

    //更新或插入某作品的消息状态信息（前提是已经将更新提醒存入notice_info表中）
    @Insert("INSERT INTO notice_state_info(notice_id,viewer_id,read_state,delete_state) SELECT * FROM(SELECT notice_id,user_id AS viewer_id,0 read_state,0 delete_state FROM notice_info,bookshelf_info WHERE notice_type = 2 AND send_by = work_id AND send_by = #{param1}\n" +
            "UNION\n" +
            "SELECT notice_id,reader_id AS viewer_id,0 read_state,0 delete_state FROM notice_info,works_info,attention_info WHERE notice_type = 2 AND send_by = works_info.work_id AND works_info.user_id = attention_info.author_id AND send_by = #{param1})derived_table \n" +
            "ON DUPLICATE KEY UPDATE read_state = VALUES(read_state),delete_state = VALUES(delete_state)")
    public int insertOrUpdateNoticeStateInfoByWorkId(int work_id);

    //插入非作品更新提醒的消息
    @Insert("INSERT INTO notice_state_info(notice_id,viewer_id) VALUES (#{param1},#{param2}),(#{param1},#{param3})")
    public int insertOrdinaryNoticeStateInfo(int notice_id,int send_by,int send_to);

    //根据消息ID和接收者ID（查看消息人ID）修改已读状态
    @Update("UPDATE notice_state_info SET read_state = #{param3} WHERE notice_id = #{param1} AND viewer_id = #{param2}")
    public int updateReadStateByNoticeIdAndViewerId(int notice_id,int viewer_id,int read_state);

    //根据消息ID和接收者ID（查看消息人ID）修改删除状态
    @Update("UPDATE notice_state_info SET delete_state = #{param3} WHERE notice_id = #{param1} AND viewer_id = #{param2}")
    public int updateDeleteStateByNoticeIdAndViewerId(int notice_id,int viewer_id,int delete_state);

    //根据查看者ID和消息类型 更新所有对应消息已读状态
    @Update("UPDATE notice_state_info,notice_info SET read_state = #{param3} WHERE notice_info.notice_id = notice_state_info.notice_id AND viewer_id = #{param1} AND notice_type = #{param2}")
    public int updateAllReadStateByViewerIdAndNoticeType(int viewer_id,int notice_type,int read_state);

    //根据查看者ID和消息类型 更新所有对应消息删除状态
    @Update("UPDATE notice_state_info,notice_info SET delete_state = #{param3} WHERE notice_info.notice_id = notice_state_info.notice_id AND viewer_id = #{param1} AND notice_type = #{param2}")
    public int updateAllDeleteStateByViewerIdAndNoticeType(int viewer_id,int notice_type,int delete_state);
}
