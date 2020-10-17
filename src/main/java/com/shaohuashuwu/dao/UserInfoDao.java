package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface UserInfoDao {

    //根据用户ID更新该用户金豆数
    @Update("update user_info set gold_bean_num = (gold_bean_num)+(#{param2}) where user_id = #{param1}")
    public int updateGoldBeanNumByUserId(int user_id,int updateNum);

    //根据用户id更新该用户金币数量
    @Update("update user_info set gold_coin_num = (gold_coin_num)+(#{param2}) where user_id = #{param1}")
    public int updateGoldCoinNumByUserId(int user_id,int updateNum);//根据用户id更新该用户金币数量

    //根据用户id更新该用户推荐票数量
    @Update("update user_info set ticket_num = (ticket_num)+(#{param2}) where user_id = #{param1}")
    public int updateTicketNumByUserId(int user_id,int updateNum);

    //查询该用户当前所有信息
    @Select("select * from user_info where user_id = #{user_id}")
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
    public UserInfo selectUserInfoByUserId(int user_id);

    //更新该用户二级密码
    @Update("update user_info set double_password = #{param2} where user_id = #{param1}")
    public int updateDoublePasswordByUserId(int user_id,String double_password);

    //根据作品ID获取阅读历史信息表以及书架信息表中对应的读者ID，根据读者ID获取所有该用户对应的用户信息
    //错误示范：select * from user_info where user_id = (select user_id from reading_history_info where work_id = #{work_id} UNION select user_id from bookshelf_info where work_id = #{work_id})
    @Select("select DISTINCT user_info.* from user_info,reading_history_info,bookshelf_info where reading_history_info.work_id = #{work_id} or bookshelf_info.work_id = #{work_id}")
    @ResultMap("userInfo")
    public List<UserInfo> selectReaderInfo(int work_id);

}
