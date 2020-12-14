package com.shaohuashuwu.domain;


public class NoticeStateInfo {
    private Integer notice_state_id;    //消息状态ID，数据库中为自增ID
    private Integer notice_id;          //消息ID
    private Integer viewer_id;          //阅读者ID（user_id）
    private Integer read_state;         //是否已读（0未读，1已读）
    private Integer delete_state;       //是否已删除（0未删除，1已删除）

    public NoticeStateInfo() {
    }

    public Integer getNotice_state_id() {
        return notice_state_id;
    }

    public void setNotice_state_id(Integer notice_state_id) {
        this.notice_state_id = notice_state_id;
    }

    public Integer getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public Integer getViewer_id() {
        return viewer_id;
    }

    public void setViewer_id(Integer viewer_id) {
        this.viewer_id = viewer_id;
    }

    public Integer getRead_state() {
        return read_state;
    }

    public void setRead_state(Integer read_state) {
        this.read_state = read_state;
    }

    public Integer getDelete_state() {
        return delete_state;
    }

    public void setDelete_state(Integer delete_state) {
        this.delete_state = delete_state;
    }

    @Override
    public String toString() {
        return "NoticeStateInfo{" +
                "notice_state_id=" + notice_state_id +
                ", notice_id=" + notice_id +
                ", viewer_id=" + viewer_id +
                ", read_state=" + read_state +
                ", delete_state=" + delete_state +
                '}';
    }
}
