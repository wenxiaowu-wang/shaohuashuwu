package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.ReadSettingInfo;

public interface ReadSettingInfoService {

    //根据用户id查询设置信息
    //功能点：用阅读小说界面设置获取，
    public ReadSettingInfo getReadSettinginfo(int user_id);

    //跟据用户id修改设置信息
    //功能点：阅读小说界面修改设置，
    public int updateReadSettingInfoByid(ReadSettingInfo readSettingInfo);


}
