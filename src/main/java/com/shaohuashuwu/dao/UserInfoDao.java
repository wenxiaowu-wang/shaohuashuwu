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


    /*
    * 郝振威
    * */
//根据用户手机号查询密码*********************
    @Select("select password from user_info where phone_number = #{phone_number}")
    public String selectUserPasswordByPhoneNumber(String phone_number);



    //注册账号密码22222222222222222
    @Insert("insert into user_info(phone_number,password,user_name,head_portrait,gender,area,birthday,gold_bean_num,gold_coin_num,ticket_num)values" +
            "(#{phone_number},#{password},#{user_name},#{head_portrait},#{gender},#{area},#{birthday},#{gold_bean_num},#{gold_coin_num},#{ticket_num})")
    public void insertUserInfoByAccount(UserInfo userInfo);

    //更新个人资料
    @Update("update user_info set user_name = #{user_name},gender = #{gender},birthday = #{birthday},area = #{area} where user_id = #{user_id}")
    public int updatePersonalDataByID(UserInfo userInfo);

    //根据手机号 获取用户ID 用户昵称 头像 性别 地区 生日
    @Select("select * from user_info where phone_number = #{phone_number}")
    public UserInfo selectUserInfoByPhoneNumber(String phone_number);

    //更改密码
    @Update("update user_info set password = #{password} where phone_number = #{phone_number}")
    public int updateUserPwd(UserInfo userInfo);


    //依据手机号判断账号是否存在
    @Select("select count(*) from user_info where phone_number = #{phone_number}")
    public int isUserInfohaveByphone_number(String phone_number);

    //查询数据库用户注册的总条数
    @Select("select count(*) from user_info")
    public int selectUserInfoAllCount(String phone_number);


    //查询用户昵称是否存在
    @Select("select count(*) from user_info where user_name = #{param1} and user_id != #{param2}")
    public Boolean isUserNameHaveByUsername(String user_name,int user_id);

    //根据章节ID获取作者的id
    @Select("select user_id from chapter_post_info where chapter_id = #{chapter_id}")
    public int selectAuthorIDByChapterID(int chapter_id);



    /**
     * 阿斌
     */
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

    //根据用户ID获取用户名
    @Select("select distinct user_name from user_info where user_id = #{id}")
    public String selectUserNameById(int id);

    //根据用户ID获取二级密码
    @Select("select double_password from user_info where user_id = #{user_id}")
    public String selectDoublePasswordById(int user_id);
}
