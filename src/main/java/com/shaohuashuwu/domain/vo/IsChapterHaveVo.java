package com.shaohuashuwu.domain.vo;

public class IsChapterHaveVo {

    private Integer  chapter_id;
    private Integer user_id;

    public IsChapterHaveVo(Integer chapter_id, Integer user_id) {
        this.chapter_id = chapter_id;
        this.user_id = user_id;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "IsChapterHaveVo{" +
                "chapter_id=" + chapter_id +
                ", user_id=" + user_id +
                '}';
    }
}
