package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;
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

    //依据用户名称查询用户信息
    @Select("select * from user_info where user_name = #{user_name}")
    public UserInfo selectuserInfoByusername(String user_name);

}
