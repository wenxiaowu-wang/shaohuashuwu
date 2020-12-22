package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.WorksInfo;

import java.io.Serializable;

public class RankingInfoVo extends WorksInfo implements Serializable {

    private Integer sumnum;                 //数量

    public RankingInfoVo() {

    }

    public RankingInfoVo(Integer work_id, String work_cover, String work_name, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word, Integer sumnum) {
        super(work_id, work_cover, work_name, work_main_label, work_vice_label, work_serial_state, work_introduct, work_other_word);
        this.sumnum = sumnum;
    }

    public Integer getSumnum() {
        return sumnum;
    }

    public void setSumnum(Integer sumnum) {
        this.sumnum = sumnum;
    }

    @Override
    public String toString() {
        return "RankingInfoVo{" +
                super.toString()+
                "sumnum=" + sumnum +
                '}';
    }
}
