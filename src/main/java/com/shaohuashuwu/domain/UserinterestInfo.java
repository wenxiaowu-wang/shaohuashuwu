package com.shaohuashuwu.domain;

import java.io.Serializable;

public class UserinterestInfo implements Serializable {
    private Integer user_interest_id;       //用户感兴趣标签
    private Integer user_id;                //用户id
    private String label_name;              //标签名称
    private Integer select_num;             //搜索次数

    public UserinterestInfo() {
    }

    public UserinterestInfo(Integer user_interest_id, Integer user_id, String label_name, Integer select_num) {
        this.user_interest_id = user_interest_id;
        this.user_id = user_id;
        this.label_name = label_name;
        this.select_num = select_num;
    }

    public Integer getUser_interest_id() {
        return user_interest_id;
    }

    public void setUser_interest_id(Integer user_interest_id) {
        this.user_interest_id = user_interest_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public Integer getSelect_num() {
        return select_num;
    }

    public void setSelect_num(Integer select_num) {
        this.select_num = select_num;
    }

    @Override
    public String toString() {
        return "Userinterestinfo{" +
                "user_interest_id=" + user_interest_id +
                ", user_id=" + user_id +
                ", label_name='" + label_name + '\'' +
                ", select_num=" + select_num +
                '}';
    }
}
