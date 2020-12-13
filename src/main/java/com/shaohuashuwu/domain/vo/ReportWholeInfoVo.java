package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.ReportInfo;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReportWholeInfoVo extends ReportInfo implements Serializable {


    private String work_name;               //作品名称
    private String user_name;               //用户昵称
    private String chapter_title;           //章节名称
    private String chapter_content;             //章节内容

    public ReportWholeInfoVo(Integer report_id, Integer user_id, Integer chapter_id, Integer report_reason, Timestamp report_time, String report_remarks, int handle_state, String work_name, String user_name,  String chapter_title) {
        super(report_id, user_id, chapter_id, report_reason, report_time, report_remarks, handle_state);

        this.work_name = work_name;
        this.user_name = user_name;
        this.chapter_title = chapter_title;
    }

    public ReportWholeInfoVo(Integer report_id, Timestamp report_time, String work_name, String user_name, String chapter_title) {
        super(report_id, report_time);
        this.work_name = work_name;
        this.user_name = user_name;
        this.chapter_title = chapter_title;
    }

    public ReportWholeInfoVo(Integer report_id, Integer chapter_id, Integer report_reason, Timestamp report_time, String report_remarks, String work_name, String user_name, String chapter_title, String chapter_content) {
        super(report_id, chapter_id, report_reason, report_time, report_remarks);
        this.work_name = work_name;
        this.user_name = user_name;
        this.chapter_title = chapter_title;
        this.chapter_content = chapter_content;
    }

    public ReportWholeInfoVo(Integer report_id, Integer user_id, Integer chapter_id, Integer report_reason, Timestamp report_time, String report_remarks, int handle_state, String work_name, String user_name, String chapter_title, String chapter_content) {
        super(report_id, user_id, chapter_id, report_reason, report_time, report_remarks, handle_state);
        this.work_name = work_name;
        this.user_name = user_name;
        this.chapter_title = chapter_title;
        this.chapter_content = chapter_content;
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

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public String getChapter_content() {
        return chapter_content;
    }

    public void setChapter_content(String chapter_content) {
        this.chapter_content = chapter_content;
    }

    @Override
    public String toString() {
        return "ReportWholeInfoVo{" +
                super.toString()+
                ", work_name='" + work_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", chapter_title='" + chapter_title + '\'' +
                '}';
    }
}
