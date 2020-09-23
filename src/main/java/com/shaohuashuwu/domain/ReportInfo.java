package com.shaohuashuwu.domain;

import java.sql.Timestamp;

public class ReportInfo {

    private Integer report_id;          //举报id
    private Integer user_id;            //用户id
    private Integer chapter_id;         //章节id
    private Integer report_reason;      //举报原因
    private Timestamp report_time;      //举报时间
    private String report_remarks;      //举报备注

    public ReportInfo() {
    }

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Integer getReport_reason() {
        return report_reason;
    }

    public void setReport_reason(Integer report_reason) {
        this.report_reason = report_reason;
    }

    public Timestamp getReport_time() {
        return report_time;
    }

    public void setReport_time(Timestamp report_time) {
        this.report_time = report_time;
    }

    public String getReport_remarks() {
        return report_remarks;
    }

    public void setReport_remarks(String report_remarks) {
        this.report_remarks = report_remarks;
    }

    @Override
    public String toString() {
        return "Reportinfo{" +
                "report_id=" + report_id +
                ", user_id=" + user_id +
                ", chapter_id=" + chapter_id +
                ", report_reason=" + report_reason +
                ", report_time=" + report_time +
                ", report_remarks='" + report_remarks + '\'' +
                '}';
    }
}
