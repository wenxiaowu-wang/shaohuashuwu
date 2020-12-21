package com.shaohuashuwu.domain.vo;

import java.sql.Timestamp;

public class BookshelfInfoVo {


    private String work_name;                   //作品名称
    private String user_name;                   //作者的用户名
    private String work_main_label;             //作品类型
    private Integer work_serial_state;          //作品状态
    private String chapter_title;               //章节名称
    private Timestamp chapter_time;             //创建时间


    public BookshelfInfoVo() {

    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWork_main_label() {
        return work_main_label;
    }

    public void setWork_main_label(String work_main_label) {
        this.work_main_label = work_main_label;
    }

    public Integer getWork_serial_state() {
        return work_serial_state;
    }

    public void setWork_serial_state(Integer work_serial_state) {
        this.work_serial_state = work_serial_state;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public Timestamp getChapter_time() {
        return chapter_time;
    }

    public void setChapter_time(Timestamp chapter_time) {
        this.chapter_time = chapter_time;
    }



    @Override
    public String toString() {
        return "BookshelfInfoVo{" +
                "work_name='" + work_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", work_main_label='" + work_main_label + '\'' +
                ", work_serial_state=" + work_serial_state +
                ", chapter_title='" + chapter_title + '\'' +
                ", chapter_time=" + chapter_time +
                '}';
    }
}
