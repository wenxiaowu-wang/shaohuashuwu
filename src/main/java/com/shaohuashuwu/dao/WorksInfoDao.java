package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.WorksInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
//@Repository
public interface WorksInfoDao {

    //根据作者ID获取所有对应的作品信息
    @Select("select *from works_info where user_id = #{user_id}")
    @Results(id = "worksInfo",value = {
            @Result(id = true,column = "work_id",property = "work_id"),
            @Result(column = "work_cover",property = "work_cover"),
            @Result(column = "work_name",property = "work_name"),
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "work_main_label",property = "work_main_label"),
            @Result(column = "work_vice_label",property = "work_vice_label"),
            @Result(column = "work_serial_state",property = "work_serial_state"),
            @Result(column = "work_introduct",property = "work_introduct"),
            @Result(column = "work_other_word",property = "work_other_word"),
            @Result(column = "work_word_num",property = "work_word_num"),
            @Result(column = "work_tip_num",property = "work_tip_num"),
            @Result(column = "work_subscribe_num",property = "work_subscribe_num"),
            @Result(column = "work_vote_num",property = "work_vote_num")
    })
    public List<WorksInfo> selectAllByUserId(int user_id);

    //根据作者ID获取所有对应的作品ID和作品名字 待定


    //根据作品ID获取对应作者ID
    @Select("select user_id from works_info where work_id = #{work_id}")
    public int selectAuthorIdByWorkId(int work_id);

    //根据作品ID获取作品名字
    @Select("select work_name from works_info where work_id = #{work_id}")
    public String selectWorkNameByWorkId(int work_id);

    //根据作品ID更新投票数量
    @Update("update works_info set work_vote_num = work_vote_num + (#{param2}) where work_id = #{param1}")
    public int updateWorkVoteNumByWorkId(int work_id,int voteNum);


//    //根据用户id从关注表中获取作品信息
//    @Select("select w1.* from works_info w1 where w1.work_id in (select b1.work_id from )")
//    public String selectWorkInfoByWorkNameFromBookShelf(int work_id);

    //根据用户ID获取用户加入书架的作品的作品名字
    @Select("SELECT w1.* FROM bookshelf_info,works_info w1 WHERE bookshelf_info.work_id = w1.work_id AND bookshelf_info.user_id = #{user_id} ORDER BY collection_time DESC")
    @ResultMap("worksInfo")
    public List<WorksInfo> selectBookshelfWorkNameByWorkID(int user_id);


    //根据用户ID获取用户加入书架的作品的作品名字
    @Select("SELECT w1.* FROM reading_history_info,works_info w1 WHERE reading_history_info.work_id = w1.work_id AND reading_history_info.user_id = #{user_id} ORDER BY reading_time DESC")
    @ResultMap("worksInfo")
    public List<WorksInfo> selectReadingHistoryWorkNameByWorkID(int user_id);


    //根据用户的ID获取该用户写过的作品ID、作品名字
    @Select("select work_id,work_name from works_info where user_id = #{user_id}")
    public List<WorksInfo> selectWorkIdNameByUserId(int user_id);

}
