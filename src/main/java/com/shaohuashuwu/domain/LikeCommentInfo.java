package com.shaohuashuwu.domain;

public class LikeCommentInfo {

     private Integer user_id;               //用户ID
     private Integer comment_id;            //评论ID

    public LikeCommentInfo() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "LikeCommentInfo{" +
                "user_id=" + user_id +
                ", comment_id=" + comment_id +
                '}';
    }
}
