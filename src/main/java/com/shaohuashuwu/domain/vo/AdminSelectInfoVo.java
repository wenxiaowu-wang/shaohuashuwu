package com.shaohuashuwu.domain.vo;

import java.io.Serializable;

public class AdminSelectInfoVo  implements Serializable {

    private String work_name;
    private String report_time;
    private Integer works_page;
    private Integer works_page_num;

    public AdminSelectInfoVo() {
    }

    public AdminSelectInfoVo(String work_name, String report_time, Integer works_page, Integer works_page_num) {
        this.work_name = work_name;
        this.report_time = report_time;
        this.works_page = works_page;
        this.works_page_num = works_page_num;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public Integer getWorks_page() {
        return works_page;
    }

    public void setWorks_page(Integer works_page) {
        this.works_page = works_page;
    }

    public Integer getWorks_page_num() {
        return works_page_num;
    }

    public void setWorks_page_num(Integer works_page_num) {
        this.works_page_num = works_page_num;
    }

    @Override
    public String toString() {
        return "AdminSelectInfoVo{" +
                "work_name='" + work_name + '\'' +
                ", report_time='" + report_time + '\'' +
                ", works_page=" + works_page +
                ", works_page_num=" + works_page_num +
                '}';
    }
}
