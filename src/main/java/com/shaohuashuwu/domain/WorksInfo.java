package com.shaohuashuwu.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

/**
 * 作品信息类
 */
public class WorksInfo implements Serializable {

    private Integer work_id;                //作品id
    private String work_cover;              //作品封面
    private String work_name;               //作品名称
    private Integer user_id;                //作者id
    private String work_main_label;         //作品类型
    private String work_vice_label;         //作品子类型
    private Integer work_serial_state;      //作品状态
    private String work_introduct;          //作品介绍
    private String work_other_word;         //给读者的话
    private Integer work_word_num;          //作品字数
    private Integer work_tip_num;           //打赏数
    private Integer work_subscribe_num;     //订阅数
    private Integer work_vote_num;          //推荐票数
    private String work_create_time;        //创建时间

    public WorksInfo(Integer work_id) {
        this.work_id = work_id;
    }

    /**
     * WorkWholeInfoVo用到信息
     * */
    public WorksInfo(Integer work_id, String work_cover, String work_name, Integer user_id, String work_main_label, Integer work_serial_state, String work_introduct, Integer work_vote_num) {
        this.work_id = work_id;
        this.work_cover = work_cover;
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_serial_state = work_serial_state;
        this.work_introduct = work_introduct;
        this.work_vote_num = work_vote_num;
    }

    public WorksInfo(Integer work_id, String work_cover, String work_name, Integer user_id, String work_main_label, String work_introduct) {
        this.work_id = work_id;
        this.work_cover = work_cover;
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_introduct = work_introduct;
    }

    public WorksInfo(Integer work_id, String work_cover, String work_name, Integer user_id, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word, Integer work_word_num, Integer work_tip_num, Integer work_subscribe_num, Integer work_vote_num, String work_create_time) {
        this.work_id = work_id;
        this.work_cover = work_cover;
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
        this.work_introduct = work_introduct;
        this.work_other_word = work_other_word;
        this.work_word_num = work_word_num;
        this.work_tip_num = work_tip_num;
        this.work_subscribe_num = work_subscribe_num;
        this.work_vote_num = work_vote_num;
        this.work_create_time = work_create_time;
    }


    public WorksInfo(String work_main_label, String work_vice_label, Integer work_serial_state) {
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
    }

    public WorksInfo(String work_name, String work_main_label, String work_vice_label, Integer work_serial_state) {
        this.work_name = work_name;
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
    }

    public WorksInfo(String work_name, Integer user_id, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word, Integer work_word_num, Integer work_tip_num, Integer work_subscribe_num, Integer work_vote_num) {
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
        this.work_introduct = work_introduct;
        this.work_other_word = work_other_word;
        this.work_word_num = work_word_num;
        this.work_tip_num = work_tip_num;
        this.work_subscribe_num = work_subscribe_num;
        this.work_vote_num = work_vote_num;
    }

    public WorksInfo(String work_name, Integer user_id, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word, Integer work_word_num, Integer work_tip_num, Integer work_subscribe_num, Integer work_vote_num, String work_create_time) {
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
        this.work_introduct = work_introduct;
        this.work_other_word = work_other_word;
        this.work_word_num = work_word_num;
        this.work_tip_num = work_tip_num;
        this.work_subscribe_num = work_subscribe_num;
        this.work_vote_num = work_vote_num;
        this.work_create_time = work_create_time;
    }

    public WorksInfo(String work_cover, String work_name, Integer user_id, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word) {
        this.work_cover = work_cover;
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
        this.work_introduct = work_introduct;
        this.work_other_word = work_other_word;
    }

    /**
     * 添加作品信息
     * @param work_name
     * @param user_id
     * @param work_main_label
     * @param work_vice_label
     * @param work_serial_state
     * @param work_introduct
     * @param work_other_word
     */
    public WorksInfo(String work_name, Integer user_id, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word) {
        this.work_name = work_name;
        this.user_id = user_id;
        this.work_main_label = work_main_label;
        this.work_vice_label = work_vice_label;
        this.work_serial_state = work_serial_state;
        this.work_introduct = work_introduct;
        this.work_other_word = work_other_word;
    }

    public WorksInfo(String work_main_label) {
        this.work_main_label = work_main_label;
    }

    public WorksInfo(Integer work_id, String work_name, Integer user_id) {
        this.work_id = work_id;
        this.work_name = work_name;
        this.user_id = user_id;
    }

    /*
        根据作品id修改作品状态
         */
    public WorksInfo(Integer work_id, Integer work_serial_state) {
        this.work_id = work_id;
        this.work_serial_state = work_serial_state;
    }

    public WorksInfo() {
    }

    public String getWork_create_time() {
        return work_create_time;
    }

    public void setWork_create_time(String work_create_time) {
        this.work_create_time = work_create_time;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public String getWork_cover() {
        return work_cover;
    }

    public void setWork_cover(String work_cover) {
        this.work_cover = work_cover;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getWork_main_label() {
        return work_main_label;
    }

    public void setWork_main_label(String work_main_label) {
        this.work_main_label = work_main_label;
    }

    public String getWork_vice_label() {
        return work_vice_label;
    }

    public void setWork_vice_label(String work_vice_label) {
        this.work_vice_label = work_vice_label;
    }

    public Integer getWork_serial_state() {
        return work_serial_state;
    }

    public void setWork_serial_state(Integer work_serial_state) {
        this.work_serial_state = work_serial_state;
    }

    public String getWork_introduct() {
        return work_introduct;
    }

    public void setWork_introduct(String work_introduct) {
        this.work_introduct = work_introduct;
    }

    public String getWork_other_word() {
        return work_other_word;
    }

    public void setWork_other_word(String work_other_word) {
        this.work_other_word = work_other_word;
    }

    public Integer getWork_word_num() {
        return work_word_num;
    }

    public void setWork_word_num(Integer work_word_num) {
        this.work_word_num = work_word_num;
    }

    public Integer getWork_tip_num() {
        return work_tip_num;
    }

    public void setWork_tip_num(Integer work_tip_num) {
        this.work_tip_num = work_tip_num;
    }

    public Integer getWork_subscribe_num() {
        return work_subscribe_num;
    }

    public void setWork_subscribe_num(Integer work_subscribe_num) {
        this.work_subscribe_num = work_subscribe_num;
    }

    public Integer getWork_vote_num() {
        return work_vote_num;
    }

    public void setWork_vote_num(Integer work_vote_num) {
        this.work_vote_num = work_vote_num;
    }

//    @Override
//    public String toString() {
//        return "WorksInfo{" +
//                "work_id=" + work_id +
//                ", work_cover=" + work_cover +
//                ", work_name='" + work_name + '\'' +
//                ", user_id=" + user_id +
//                ", work_main_label='" + work_main_label + '\'' +
//                ", work_vice_label='" + work_vice_label + '\'' +
//                ", work_serial_state=" + work_serial_state +
//                ", work_introduct='" + work_introduct + '\'' +
//                ", work_other_word='" + work_other_word + '\'' +
//                ", work_word_num=" + work_word_num +
//                ", work_tip_num=" + work_tip_num +
//                ", work_subscribe_num=" + work_subscribe_num +
//                ", work_vote_num=" + work_vote_num +
//                ", work_create_time=" + work_create_time +
//                '}';
//    }

    @Override
    public String toString() {
        return "WorksInfo{" +
                "work_id=" + work_id +
                ", work_cover=" + work_cover +
                ", work_name='" + work_name + '\'' +
                ", user_id=" + user_id +
                ", work_main_label='" + work_main_label + '\'' +
                ", work_vice_label='" + work_vice_label + '\'' +
                ", work_serial_state=" + work_serial_state +
                ", work_introduct='" + work_introduct + '\'' +
                ", work_other_word='" + work_other_word + '\'' +
                ", work_word_num=" + work_word_num +
                ", work_tip_num=" + work_tip_num +
                ", work_subscribe_num=" + work_subscribe_num +
                ", work_vote_num=" + work_vote_num +
                '}';
    }
}
