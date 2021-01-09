package com.shaohuashuwu.domain.vo;

import java.io.Serializable;

public class CommentInfoChildVo  implements Serializable {

    private Integer user_id;            //用户ID
    private String user_name;       //用户名
    private String head_portrait;   //用户头像
    private String comment_time;     //评论时间
    private String comment_content;     //评论内容
    private Integer comment_id;          //评论ID
    private Integer comment_pid;       //上一条评论id
    private Integer comment_aid;          //总父亲评论ID
    private String parent_name;       //父评论用户名



    public  CommentInfoChildVo(){

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getComment_pid() {
        return comment_pid;
    }

    public void setComment_pid(Integer comment_pid) {
        this.comment_pid = comment_pid;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public Integer getComment_aid() {
        return comment_aid;
    }

    public void setComment_aid(Integer comment_aid) {
        this.comment_aid = comment_aid;
    }

    @Override
    public String toString() {
        return "CommentInfoChildVo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", head_portrait='" + head_portrait + '\'' +
                ", comment_time='" + comment_time + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_id=" + comment_id +
                ", comment_pid=" + comment_pid +
                ", comment_aid=" + comment_aid +
                ", parent_name='" + parent_name + '\'' +
                '}';
    }
}
