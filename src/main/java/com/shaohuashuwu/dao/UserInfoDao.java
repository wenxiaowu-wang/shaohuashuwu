package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface UserInfoDao {

    //依据用户名称获取用户登录信息
    //功能点：用户界面上的登录的用户信息
    @Select("select user_name,user_id from user_info where user_id = #{user_id}")
    public UserInfo selectUserLogiInfoByuser_id(int user_id);

    //获取网站注册人数
    //功能点：用户界面上的获取的用户数量
    @Select("SELECT COUNT(*) FROM user_info ")
    public int selectUserNum();

    //根据作品id获取用户信息
    @Select(" Select u1.user_id user_id,u1.user_name user_name,u1.head_portrait head_portrait " +
            " from user_info u1 " +
            " where u1.user_id = " +
            "   (select w1.user_id " +
            "    from works_info w1 " +
            "    where w1.work_id = #{work_id})")
    public UserInfo selectUserInfoByWork_id(int work_id);

    //依据章节id查询作者信息
    //功能点：阅读小说界面获取作者信息
    @Select("SELECT u.user_id,u.user_name FROM user_info u,chapter_post_info c WHERE u.user_id = c.user_id AND c.chapter_id = #{chapter_id}")
    public UserInfo selectauthorInfoByChapter_id(int chapter_id);

}
