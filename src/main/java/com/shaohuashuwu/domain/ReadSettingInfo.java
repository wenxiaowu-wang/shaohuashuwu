package com.shaohuashuwu.domain;

public class ReadSettingInfo {

    private Integer setting_id;             //设置信息ID
    private Integer user_id;                //用户ID
    private Integer setting_theme;          //设置主题
    private Integer setting_font_type;      //设置字体类型
    private Integer setting_font_size;      //设置字体大小

    public ReadSettingInfo() {
    }


    public Integer getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(Integer setting_id) {
        this.setting_id = setting_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSetting_theme() {
        return setting_theme;
    }

    public void setSetting_theme(Integer setting_theme) {
        this.setting_theme = setting_theme;
    }

    public Integer getSetting_font_type() {
        return setting_font_type;
    }

    public void setSetting_font_type(Integer setting_font_type) {
        this.setting_font_type = setting_font_type;
    }

    public Integer getSetting_font_size() {
        return setting_font_size;
    }

    public void setSetting_font_size(Integer setting_font_size) {
        this.setting_font_size = setting_font_size;
    }

    @Override
    public String toString() {
        return "ReadSettingInfo{" +
                "setting_id=" + setting_id +
                ", user_id=" + user_id +
                ", setting_theme=" + setting_theme +
                ", setting_font_type=" + setting_font_type +
                ", setting_font_size=" + setting_font_size +
                '}';
    }
}
