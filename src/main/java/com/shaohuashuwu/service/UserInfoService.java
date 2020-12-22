package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.UserInfo;

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

}
