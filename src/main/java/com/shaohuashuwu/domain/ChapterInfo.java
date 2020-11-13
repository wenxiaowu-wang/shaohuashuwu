package com.shaohuashuwu.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChapterInfo implements Serializable {

    private Integer chapter_id;                 //章节id
    private Integer chapter_pid;                //章节父标签
    private String chapter_title;               //章节名称
    private String chapter_time;                //创建时间
    private Integer chapter_word_num;           //章节字数
    private String chapter_content;             //章节内容
    private String chapter_other_word;          //给读者的话
    private Integer chapter_state;              //章节状态
    private Integer chapter_charge;             //是否收费

    public ChapterInfo() {
    }

    public ChapterInfo(Integer chapter_id, Integer chapter_pid, String chapter_title, String chapter_time, Integer chapter_word_num, String chapter_content, String chapter_other_word, Integer chapter_state, Integer chapter_charge) {
        this.chapter_id = chapter_id;
        this.chapter_pid = chapter_pid;
        this.chapter_title = chapter_title;
        this.chapter_time = chapter_time;
        this.chapter_word_num = chapter_word_num;
        this.chapter_content = chapter_content;
        this.chapter_other_word = chapter_other_word;
        this.chapter_state = chapter_state;
        this.chapter_charge = chapter_charge;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Integer getChapter_pid() {
        return chapter_pid;
    }

    public void setChapter_pid(Integer chapter_pid) {
        this.chapter_pid = chapter_pid;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public String getChapter_time() {
        return chapter_time;
    }

    public void setChapter_time(String chapter_time) {
        this.chapter_time = chapter_time;
    }

    public String getChapter_other_word() {
        return chapter_other_word;
    }

    public void setChapter_other_word(String chapter_other_word) {
        this.chapter_other_word = chapter_other_word;
    }

    public Integer getChapter_state() {
        return chapter_state;
    }

    public void setChapter_state(Integer chapter_state) {
        this.chapter_state = chapter_state;
    }

    public Integer getChapter_charge() {
        return chapter_charge;
    }

    public void setChapter_charge(Integer chapter_charge) {
        this.chapter_charge = chapter_charge;
    }

    public Integer getChapter_word_num() {
        return chapter_word_num;
    }

    public void setChapter_word_num(Integer chapter_word_num) {
        this.chapter_word_num = chapter_word_num;
    }

    public String getChapter_content() {
        return chapter_content;
    }

    public void setChapter_content(String chapter_content) {
        this.chapter_content = chapter_content;
    }

    @Override
    public String toString() {
        return "Chapterinfo{" +
                "chapter_id=" + chapter_id +
                ", chapter_pid=" + chapter_pid +
                ", chapter_title='" + chapter_title + '\'' +
                ", chapter_time=" + chapter_time +
                ", chapter_word_num=" + chapter_word_num +
                ", chapter_content='" + chapter_content + '\'' +
                ", chapter_other_word='" + chapter_other_word + '\'' +
                ", chapter_state=" + chapter_state +
                ", chapter_charge=" + chapter_charge +
                '}';
    }
}
