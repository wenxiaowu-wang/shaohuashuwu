package com.shaohuashuwu.domain;

//用于章节定位
public class ChapterPostInfo {

    private Integer user_id;        //用户ID
    private Integer work_id;        //作品ID
    private Integer chapter_id;     //章节ID

    public ChapterPostInfo() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    @Override
    public String toString() {
        return "ChapterPostInfo{" +
                "user_id=" + user_id +
                ", work_id=" + work_id +
                ", chapter_id=" + chapter_id +
                '}';
    }
}
