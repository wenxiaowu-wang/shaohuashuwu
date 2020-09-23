package com.shaohuashuwu.domain;

import java.sql.Timestamp;

public class ReadingHistoryInfo {


    private Integer user_id;            //用户id
    private Integer work_id;            //作品id
    private Timestamp reading_time;      //阅读时间

    public ReadingHistoryInfo() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public Timestamp getReading_time() {
        return reading_time;
    }

    public void setReading_time(Timestamp reding_time) {
        this.reading_time = reding_time;
    }

    @Override
    public String toString() {
        return "Readinghistoryinfo{" +
                "user_id=" + user_id +
                ", work_id=" + work_id +
                ", reding_time=" + reading_time +
                '}';
    }
}
