package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ReadSettingInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadSettingInfoDao {

    //依据用户id，判断设置是否存在
    //功能点：用阅读小说界面设置获取
    @Select("select count(*) from read_setting_info where user_id = #{user_id}" )
    public int selectReadSettinginfoNumByuser_id(int user_id);


    //跟据用户id保存设置信息
    //功能点：用阅读小说界面设置获取
    @Insert("insert into read_setting_info(user_id,setting_theme,setting_font_type,setting_font_size) " +
            "values(#{user_id},#{setting_theme},#{setting_font_type},#{setting_font_size})")
    public int insertreadSettingInfo(ReadSettingInfo readSettingInfo);


    //根据用户id查询设置信息
    //功能点：用阅读小说界面设置获取
    @Select("select * from read_setting_info where user_id = #{user_id}" )
    public ReadSettingInfo selectReadSettinginfoByuser_id(int user_id);

    //跟据用户id修改设置信息
    //功能点：阅读小说界面修改设置，
    @Update(" update read_setting_info set setting_theme = #{setting_theme},setting_font_type = #{setting_font_type},setting_font_size = #{setting_font_size} where user_id = #{user_id} ")
    public int updateReadSettingInfoByid(ReadSettingInfo readSettingInfo);


}
