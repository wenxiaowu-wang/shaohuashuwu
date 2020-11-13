package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.ReadSettingInfo;
import com.shaohuashuwu.domain.WorksInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadSettingInfoDao {

    //依据用户id查询是否有数量
    @Select("select count(*) from read_setting_info where user_id = #{user_id}" )
    public int selectIsuserid(int user_id);

    //根据用户id查询设置信息
    @Select("select * from read_setting_info where user_id = #{user_id}" )
    public ReadSettingInfo selectReadSettinginfo(int user_id);

    //跟据用户id保存设置信息
    @Insert("insert into read_setting_info(user_id,setting_theme,setting_font_type,setting_font_size) " +
            "values(#{user_id},#{setting_theme},#{setting_font_type},#{setting_font_size})")
    public int insertchapter_info(ReadSettingInfo readSettingInfo);

    //跟据用户id修改设置信息
    @Update(" update read_setting_info set setting_theme = #{setting_theme},setting_font_type = #{setting_font_type},setting_font_size = #{setting_font_size} where user_id = #{user_id} ")
    public int updateReadSettingInfoByid(ReadSettingInfo readSettingInfo);



}
