package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface UserInfoService {

    //获取网站注册人数
    public int getUserNum();

    //获取用户登录信息
    public UserInfo getUserLoginInfo(int user_id);

    //依据作品id获取用户信息
    public UserInfo getUserInfoByWork_id(int work_id);

    //依据章节id查询作品信息
    public UserInfo getauthorInfoBychapterid(int chapter_id);
















    /****************以下未修改**************/














    //依据用户名称查询用户id
    public int getuser_idByusername(String user_name);







}
