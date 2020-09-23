package com.shaohuashuwu.domain;

import java.io.Serializable;
import java.sql.Blob;

public class WorkslabelInfo implements Serializable {
    private String label_name;          //标签名称
    private Integer work_id;            //作品id

    public WorkslabelInfo() {
    }

    public String getLabelname() {
        return label_name;
    }

    public void setLabelname(String labelname) {
        this.label_name = labelname;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    @Override
    public String toString() {
        return "Workslabelinfo{" +
                "labelname='" + label_name + '\'' +
                ", work_id=" + work_id +
                '}';
    }
}
