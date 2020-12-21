package com.shaohuashuwu.domain.vo;


import java.sql.Timestamp;

public class CommentInfoParentVo {


    private Integer user_id;            //用户ID
    private String user_name;       //用户名
    private String head_portrait;   //用户头像
    private String comment_time;     //评论时间
    private String comment_content;     //评论内容
    private Integer comment_id;            //评论ID

    public  CommentInfoParentVo(){

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

    @Override
    public String toString() {
        return "CommentInfoParentVo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", head_portrait='" + head_portrait + '\'' +
                ", comment_time='" + comment_time + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_id=" + comment_id +
                '}';
    }
}
