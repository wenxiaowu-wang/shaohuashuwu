package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.WorksInfo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 作品全部信息
 */
public class WorkWholeInfoVo extends WorksInfo implements Serializable {

    private String user_name;           //用户昵称
    private Integer chapter_id;                 //章节id
    private String chapter_title;               //章节名称
    private Timestamp chapter_time;                //创建时间


    /**
     * 设置主界面获取的信息*/
    public WorkWholeInfoVo(Integer work_id, String work_cover, String work_name, Integer user_id, String work_main_label, String work_introduct, String user_name) {
        super( work_id,  work_cover,  work_name,  user_id,  work_main_label,  work_introduct);
        this.user_name = user_name;
    }


    public WorkWholeInfoVo(String user_name, Integer chapter_id, String chapter_title, Timestamp chapter_time) {
        this.user_name = user_name;
        this.chapter_id = chapter_id;
        this.chapter_title = chapter_title;
        this.chapter_time = chapter_time;
    }

    public WorkWholeInfoVo(Integer work_id, String work_cover, String work_name, Integer user_id, String work_main_label, Integer work_serial_state, String work_introduct, Integer work_vote_num, String user_name, Integer chapter_id, String chapter_title, Timestamp chapter_time) {
        super(work_id, work_cover, work_name, user_id, work_main_label, work_serial_state, work_introduct, work_vote_num);
        this.user_name = user_name;
        this.chapter_id = chapter_id;
        this.chapter_title = chapter_title;
        this.chapter_time = chapter_time;
    }



    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public Timestamp getChapter_time() {
        return chapter_time;
    }

    public void setChapter_time(Timestamp chapter_time) {
        this.chapter_time = chapter_time;
    }


    @Override
    public String toString() {
        return "WorkWholeInfoVo{" +
                super.toString()+
                "user_name='" + user_name + '\'' +
                ", chapter_id=" + chapter_id +
                ", chapter_title='" + chapter_title + '\'' +
                ", chapter_time='" + chapter_time + '\'' +
                '}';
    }
}
