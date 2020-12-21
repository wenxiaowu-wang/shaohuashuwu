package com.shaohuashuwu.service;


import org.apache.ibatis.annotations.Select;

import java.util.Map;


public interface UserInfoService {

    //更新用户金豆数（加减都有可能）
    public boolean updateUserGoldBean(int user_id,int updateNum);

    //获取该用户当前金豆数
    public int getGoldBeanNumOfUser(int user_id);

    //获取该用户当前推荐票数
    public int getTicketNumOfUser(int user_id);

    //判断该用户是否已经是一位作者
    public boolean isAlreadyBecameAuthor(int user_id);

    //更新该用户(作者)的二级密码
    public boolean updateAuthorDoublePassword(int user_id,String double_password);

    public boolean isDoublePassword(int user_id,String pass);

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

}
