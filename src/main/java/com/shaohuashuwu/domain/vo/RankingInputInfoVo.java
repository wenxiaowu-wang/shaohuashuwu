package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.WorksInfo;

import java.io.Serializable;
import java.sql.Timestamp;

public class RankingInputInfoVo implements Serializable {

    private String work_main_label;       //作品类型
    private Integer getneednum;          //需要的数量
    private Integer transaction_type;    //排行类型
    private String transaction_time;     //交易时间
    private Integer time_type;            //时间类型

    public RankingInputInfoVo() {
    }

    public RankingInputInfoVo(String work_main_label, Integer getneednum, Integer transaction_type, String transaction_time, Integer time_type) {
        this.work_main_label = work_main_label;
        this.getneednum = getneednum;
        this.transaction_type = transaction_type;
        this.transaction_time = transaction_time;
        this.time_type = time_type;
    }

    public String getWork_main_label() {
        return work_main_label;
    }

    public void setWork_main_label(String work_main_label) {
        this.work_main_label = work_main_label;
    }

    public Integer getGetneednum() {
        return getneednum;
    }

    public void setGetneednum(Integer getneednum) {
        this.getneednum = getneednum;
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

    public Integer getTime_type() {
        return time_type;
    }

    public void setTime_type(Integer time_type) {
        this.time_type = time_type;
    }

    @Override
    public String toString() {
        return "RankingInputInfoVo{" +
                "work_main_label='" + work_main_label + '\'' +
                ", getneednum=" + getneednum +
                ", transaction_type=" + transaction_type +
                ", transaction_time='" + transaction_time + '\'' +
                ", time_type=" + time_type +
                '}';
    }
}
