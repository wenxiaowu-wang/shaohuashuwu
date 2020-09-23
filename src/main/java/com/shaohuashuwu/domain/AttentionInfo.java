package com.shaohuashuwu.domain;


public class AttentionInfo {

    private Integer reader_id;      //读者ID
    private Integer author_id;      //作者ID

    public AttentionInfo() {
    }

    public Integer getReader_id() {
        return reader_id;
    }

    public void setReader_id(Integer reader_id) {
        this.reader_id = reader_id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "AttentionInfo{" +
                "reader_id=" + reader_id +
                ", author_id=" + author_id +
                '}';
    }
}
