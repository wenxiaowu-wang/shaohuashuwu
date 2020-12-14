package com.shaohuashuwu.domain.vo;

import java.sql.Timestamp;

/**
 * 包:com.shaohuashuwu.domain.vo
 * 作者:王洪斌
 * 日期:2020/11/1
 * 项目:shaohuashuwu
 * 描述:
 */
public class NoticeInfoVo {
    //消息信息值对象，包含内容如下
    private Integer notice_id;              //通知信息ID
    private Integer send_by;                //发送者ID（如果类型为更新提醒，此为作品ID；若类型为系统消息，此为0）
    private Integer notice_type;            //通知类型(1系统消息，2更新提醒，3私信)
    private String notice_content;      //通知内容
    private String notice_title;        //通知标题
    private String send_time;        //通知时间(将其由TimeStamp转为String类型，方便展示)
    private Integer notice_tip;             //提示通知（1通知、未读，0不通知、已读）
    private String send_by_name;        //通知发送人名字（更新提醒为作品名）

    public NoticeInfoVo() {

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

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public Integer getNotice_tip() {
        return notice_tip;
    }

    public void setNotice_tip(Integer notice_tip) {
        this.notice_tip = notice_tip;
    }

    public String getSend_by_name() {
        return send_by_name;
    }

    public void setSend_by_name(String send_by_name) {
        this.send_by_name = send_by_name;
    }

    @Override
    public String toString() {
        return "NoticeInfoVo{" +
                "notice_id=" + notice_id +
                ", send_by=" + send_by +
                ", notice_type=" + notice_type +
                ", notice_content='" + notice_content + '\'' +
                ", notice_title='" + notice_title + '\'' +
                ", send_time='" + send_time + '\'' +
                ", notice_tip=" + notice_tip +
                ", send_by_name='" + send_by_name + '\'' +
                '}';
    }
}
