package com.shaohuashuwu.domain;

import java.sql.Timestamp;


public class CommentInfo {

    private Integer comment_id;             //评论ID
    private Integer comment_pid;            //评论父ID
    private Integer comment_aid;            //评论艾特ID
    private Integer user_id;                //用户ID
    private Timestamp comment_time;     //评论时间
    private String comment_content;     //评论内容
    private Integer comment_like_num;       //评论点赞数
    private Integer chapter_id;             //评论所在章节ID
    private Integer work_id;                //评论所在作品ID

    public CommentInfo() {
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

    public Integer getComment_aid() {
        return comment_aid;
    }

    public void setComment_aid(Integer comment_aid) {
        this.comment_aid = comment_aid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getComment_time() {
        return comment_time;
    }

    public void setComment_time(Timestamp comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Integer getComment_like_num() {
        return comment_like_num;
    }

    public void setComment_like_num(Integer comment_like_num) {
        this.comment_like_num = comment_like_num;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "comment_id=" + comment_id +
                ", comment_pid=" + comment_pid +
                ", comment_aid=" + comment_aid +
                ", user_id=" + user_id +
                ", comment_time=" + comment_time +
                ", comment_content='" + comment_content + '\'' +
                ", comment_like_num=" + comment_like_num +
                ", chapter_id=" + chapter_id +
                ", work_id=" + work_id +
                '}';
    }
}
