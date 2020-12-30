package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface AttentionInfoDao {

    //添加一条关注信息、
    @Insert("insert into attention_info(reader_id,author_id) values(#{reader_id},#{author_id})")
    public int insertAttentionInfo(AttentionInfo attentionInfo);

    //根据读者ID和作者ID查询关注信息
    @Select("select * from attention_info where reader_id = #{reader_id} and author_id = #{author_id}")
    @Results(id = "attention",value = {
            @Result(id = true,column = "reader_id",property = "reader_id"),
            @Result(id = true,column = "author_id",property = "author_id")
    })
    public AttentionInfo selectAttentionInfo(AttentionInfo attentionInfo);

    //获取该用户关注的所有作者信息
    @Select("SELECT user_info.* FROM user_info,attention_info WHERE user_info.user_id = attention_info.author_id AND attention_info.reader_id = #{user_id} ")
    @Results(id = "userInfo",value = {
            @Result(id = true,column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "head_portrait",property = "head_portrait"),
            @Result(column = "gender",property = "gender"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "area",property = "area"),
            @Result(column = "phone_number",property = "phone_number"),
            @Result(column = "password",property = "password"),
            @Result(column = "double_password",property = "double_password"),
            @Result(column = "gold_bean_num",property = "gold_bean_num"),
            @Result(column = "gold_coin_num",property = "gold_coin_num"),
            @Result(column = "ticket_num",property = "ticket_num")
    })
    public List<UserInfo> selectAttentionUserInfoByUserId(int user_id);

    //获取该用户粉丝信息
    @Select("SELECT user_info.* FROM user_info,attention_info WHERE user_info.user_id = attention_info.reader_id AND attention_info.author_id = #{user_id} ")
    @ResultMap("userInfo")
    public List<UserInfo> selectWereAttentionUserInfoByAuthorId(int user_id);

    //获取与该用户互相关注的所有用户信息
    @Select("SELECT DISTINCT user_info.* FROM user_info,attention_info attention_one,attention_info attention_two WHERE user_info.user_id = attention_one.reader_id AND user_info.user_id = attention_two.author_id AND attention_one.author_id = #{user_id} ")
    @ResultMap("userInfo")
    public List<UserInfo> selectEachAttentionUserInfoByUserId(int user_id);

    //删除一条关注信息
    @Delete("delete from attention_info where reader_id = #{reader_id} and author_id = #{author_id}")
    public int deleteAttentionInfo(AttentionInfo attentionInfo);

}
