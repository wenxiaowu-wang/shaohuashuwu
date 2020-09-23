package com.shaohuashuwu.domain;

import java.sql.Timestamp;

public class PublishmentInfo {

    private Integer punishment_id;              //处罚信息ID
    private Integer punishment_result;          //处罚原因
    private Integer report_id;                  //举报人ID
    private String admin_id;                //管理员ID
    private Timestamp punishment_time;      //处罚时间

    public PublishmentInfo() {
    }

    public Integer getPunishment_id() {
        return punishment_id;
    }

    public void setPunishment_id(Integer punishment_id) {
        this.punishment_id = punishment_id;
    }

    public Integer getPunishment_result() {
        return punishment_result;
    }

    public void setPunishment_result(Integer punishment_result) {
        this.punishment_result = punishment_result;
    }

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public Timestamp getPunishment_time() {
        return punishment_time;
    }

    public void setPunishment_time(Timestamp punishment_time) {
        this.punishment_time = punishment_time;
    }

    @Override
    public String toString() {
        return "PublishmentInfo{" +
                "punishment_id=" + punishment_id +
                ", punishment_result=" + punishment_result +
                ", report_id=" + report_id +
                ", admin_id='" + admin_id + '\'' +
                ", punishment_time=" + punishment_time +
                '}';
    }
}
