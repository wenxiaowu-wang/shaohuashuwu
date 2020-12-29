package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.UserInfo;

import java.util.Map;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface UserInfoService {

    //获取网站注册人数
    //功能点：用户界面上的获取的用户数量
    public int getUserNum();

    //获取用户登录信息
    //功能点：用户界面上的登录的用户信息
    public UserInfo getUserLoginInfo(int user_id);

    //依据作品id获取用户信息
    //功能点：作品详情时获取作者信息，
    public UserInfo getUserInfoByWork_id(int work_id);

    //依据章节id查询作品信息
    //功能点：阅读小说界面获取作者信息
    public UserInfo getauthorInfoBychapterid(int chapter_id);

    /**
     *  郝振威
     */


    //根据手机号登录
    public int checkUserInfo(String phone_number,String password);

    //更改用户密码
    public Boolean updateUserPwd(String phone_number,String password);

    //注册保存信息
    public Boolean insertUserInfo2 (String phone_number, String password, String smsCode, String user_name, String head_portrait, String gender, String area, String birthday,int gold_bean_num,int gold_coin_num,int ticket_num);

    //依据手机号判断账号是否存在(注册查询)
    public int isUserInfohaveByphone_number(String phone_number);

    //根据手机号 获取用户ID 用户昵称 头像
    public Map<String,Object> getUserIdNameAndHeaderByPhone(String phone_number);

    //更新用户资料
    public Boolean updateUserData(int user_id, String user_name,  String gender,  String birthday,String area);

    //查询用户昵称是否存在
    public Boolean isUserNameHaveByUsername(String user_name,int user_id);

    //根据章节ID获取作者的id
    public int getAuthorIDByChapterID(int chapter_id);

    //获取该用户当前金豆数
    public int getGoldBeanNumOfUser(int user_id);

    public boolean updateUserGoldBean(int user_id, int updateNum);

}
