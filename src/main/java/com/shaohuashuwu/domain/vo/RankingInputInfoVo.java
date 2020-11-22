package com.shaohuashuwu.domain.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class RankingInputInfoVo implements Serializable {

    private String work_main_label;         //作品类型
    private Integer transaction_type;       //交易类型
    private String transaction_time;     //交易时间

    public RankingInputInfoVo() {
    }

    public RankingInputInfoVo(String work_main_label, Integer transaction_type, String transaction_time) {
        this.work_main_label = work_main_label;
        this.transaction_type = transaction_type;
        this.transaction_time = transaction_time;
    }

    public String getWork_main_label() {
        return work_main_label;
    }

    public void setWork_main_label(String work_main_label) {
        this.work_main_label = work_main_label;
    }

    public Integer getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Integer transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    @Override
    public String toString() {
        return "RankingInputInfoVo{" +
                "work_main_label='" + work_main_label + '\'' +
                ", transaction_type=" + transaction_type +
                ", transaction_time=" + transaction_time +
                '}';
    }
}
