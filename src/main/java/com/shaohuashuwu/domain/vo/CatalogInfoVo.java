package com.shaohuashuwu.domain.vo;

import java.io.Serializable;

public class CatalogInfoVo implements Serializable {

    private Integer chapter_id;                 //章节id
    private String chapter_title;               //章节名称
    private Integer chapter_charge;             //是否收费
    private Integer subscribe;                  //是否订阅

    public CatalogInfoVo() {
    }

    public CatalogInfoVo(Integer chapter_id, String chapter_title, Integer chapter_charge, Integer subscribe) {
        this.chapter_id = chapter_id;
        this.chapter_title = chapter_title;
        this.chapter_charge = chapter_charge;
        this.subscribe = subscribe;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public Integer getChapter_charge() {
        return chapter_charge;
    }

    public void setChapter_charge(Integer chapter_charge) {
        this.chapter_charge = chapter_charge;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "CatalogInfoVo{" +
                "chapter_id=" + chapter_id +
                ", chapter_title='" + chapter_title + '\'' +
                ", chapter_charge=" + chapter_charge +
                ", subscribe=" + subscribe +
                '}';
    }
}
