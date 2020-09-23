package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.WorksInfo;
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

}
