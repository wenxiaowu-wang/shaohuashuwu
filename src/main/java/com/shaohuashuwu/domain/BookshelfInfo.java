package com.shaohuashuwu.domain;

import java.sql.Timestamp;

public class BookshelfInfo {

    private Integer user_id;                //读者（用户）ID
    private Integer work_id;                //作品ID
    private Timestamp collection_time;  //收藏时间

    public BookshelfInfo() {
    }

    public BookshelfInfo(int user_id, int work_id, Timestamp collection_time) {
        this.collection_time=collection_time;
        this.user_id=user_id;
        this.work_id=work_id;
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

    public Timestamp getCollection_time() {
        return collection_time;
    }

    public void setCollection_time(Timestamp collection_time) {
        this.collection_time = collection_time;
    }

    @Override
    public String toString() {
        return "BookshelfInfo{" +
                "user_id=" + user_id +
                ", work_id=" + work_id +
                ", collection_time=" + collection_time +
                '}';
    }
}
