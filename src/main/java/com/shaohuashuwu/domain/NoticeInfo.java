package com.shaohuashuwu.domain;

import java.sql.Timestamp;

public class NoticeInfo {

    private Integer notice_id;              //通知信息ID
    private Integer send_by;                //发送者ID（如果类型为更新提醒，此为作品ID；若类型为系统消息，此为0）
    private Integer send_to;                //接收者ID（0表示全体用户）
    private Integer notice_type;            //通知类型(1系统消息，2更新提醒，3私信)
    private String notice_content;      //通知内容
    private String notice_title;        //通知标题
    private Timestamp send_time;        //通知时间
    private Integer notice_tip;             //提示通知（1通知、未读，0不通知、已读）

    public NoticeInfo() {
    }

    public Integer getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public Integer getSend_by() {
        return send_by;
    }

    public void setSend_by(Integer send_by) {
        this.send_by = send_by;
    }

    public Integer getSend_to() {
        return send_to;
    }

    public void setSend_to(Integer send_to) {
        this.send_to = send_to;
    }

    public Integer getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(Integer notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public Timestamp getSend_time() {
        return send_time;
    }

    public void setSend_time(Timestamp send_time) {
        this.send_time = send_time;
    }

    public Integer getNotice_tip() {
        return notice_tip;
    }

    public void setNotice_tip(Integer notice_tip) {
        this.notice_tip = notice_tip;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "notice_id=" + notice_id +
                ", send_by=" + send_by +
                ", send_to=" + send_to +
                ", notice_type=" + notice_type +
                ", notice_content='" + notice_content + '\'' +
                ", notice_title='" + notice_title + '\'' +
                ", send_time=" + send_time +
                ", notice_tip=" + notice_tip +
                '}';
    }
}
