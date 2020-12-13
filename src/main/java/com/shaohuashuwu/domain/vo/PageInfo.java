package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.WorksInfo;

import java.io.Serializable;

public class PageInfo extends WorksInfo  implements Serializable {

    private Integer works_page;
    private Integer works_page_num;

    public PageInfo() {

    }

    public PageInfo(Integer works_page, Integer works_page_num) {
        this.works_page = works_page;
        this.works_page_num = works_page_num;
    }

    public PageInfo(String work_main_label, String work_vice_label, Integer work_serial_state, Integer works_page, Integer works_page_num) {
        super(work_main_label, work_vice_label, work_serial_state);
        this.works_page = works_page;
        this.works_page_num = works_page_num;
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
        return "PageInfo{" +
                "works_page=" + works_page +
                ", works_page_num=" + works_page_num +
                '}';
    }
}
