package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.ReadSettingInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ReadSettingInfoService {

    //根据用户id查询设置信息
    public ReadSettingInfo getReadSettinginfo(int user_id);

    //跟据用户id修改设置信息
    public int updateReadSettingInfoByid(ReadSettingInfo readSettingInfo);




















    /***********************以下未修改***************/





}
