package com.shaohuashuwu.domain.vo;

import java.io.Serializable;

public class RankingInfoVo implements Serializable {
    private Integer work_id;                //作品id
    private String work_main_label;         //作品类型
    private Integer work_serial_state;      //作品状态
    private Integer sumnum;                 //数量
    private String work_name;               //作品名称
    private String work_cover;              //作品封面

    public RankingInfoVo() {
    }

    public RankingInfoVo(Integer work_id, String work_main_label, Integer work_serial_state, Integer sumnum, String work_name, String work_cover) {
        this.work_id = work_id;
        this.work_main_label = work_main_label;
        this.work_serial_state = work_serial_state;
        this.sumnum = sumnum;
        this.work_name = work_name;
        this.work_cover = work_cover;
    }



    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
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

    public Integer getSumnum() {
        return sumnum;
    }

    public void setSumnum(Integer sumnum) {
        this.sumnum = sumnum;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getWork_cover() {
        return work_cover;
    }

    public void setWork_cover(String work_cover) {
        this.work_cover = work_cover;
    }

    @Override
    public String toString() {
        return "RankingInfoVo{" +
                "work_id=" + work_id +
                ", work_main_label='" + work_main_label + '\'' +
                ", work_serial_state=" + work_serial_state +
                ", sumnum=" + sumnum +
                ", work_name='" + work_name + '\'' +
                ", work_cover='" + work_cover + '\'' +
                '}';
    }
}
