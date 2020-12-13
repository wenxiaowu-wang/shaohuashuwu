package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserandWorksInfoVo extends WorksInfo  implements Serializable {


    //用户属性
//    private Integer user_id;                //用户ID
    private String user_name;           //用户昵称
    private String head_portrait;         //用户头像
    private Integer attentionnum;       //关注数量



    public UserandWorksInfoVo(Integer work_id, String work_cover, String work_name, Integer user_id, String work_main_label, String work_vice_label, Integer work_serial_state, String work_introduct, String work_other_word, Integer work_word_num, Integer work_tip_num, Integer work_subscribe_num, Integer work_vote_num, String work_create_time, String user_name, String head_portrait , Integer attentionnum) {
        super( work_id,  work_cover,  work_name,  user_id,  work_main_label,  work_vice_label,  work_serial_state,  work_introduct,  work_other_word,  work_word_num,  work_tip_num,  work_subscribe_num,  work_vote_num,  work_create_time);
        this.user_name = user_name;
        this.head_portrait = head_portrait;
        this.attentionnum = attentionnum;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public Integer getAttentionnum() {
        return attentionnum;
    }

    public void setAttentionnum(Integer attentionnum) {
        this.attentionnum = attentionnum;
    }

    @Override
    public String toString() {
        return "UserandWorksInfo{" +
                super.toString()+
                "user_name='" + user_name + '\'' +
                ", head_portrait='" + head_portrait + '\'' +
                ", attentionnum=" + attentionnum +
                '}';
    }



    //    //作品属性
//    private Integer work_id;                //作品id
//    private String work_cover;              //作品封面
//    private String work_name;               //作品名称
////    private Integer user_id;                //作者id
//    private String work_main_label;         //作品类型
//    private String work_vice_label;         //作品子类型
//    private Integer work_serial_state;      //作品状态
//    private String work_introduct;          //作品介绍
//    private String work_other_word;         //给读者的话
//    private Integer work_word_num;          //作品字数
//    private Integer work_tip_num;           //打赏数
//    private Integer work_subscribe_num;     //订阅数
//    private Integer work_vote_num;          //推荐票数
//    private String work_create_time;        //创建时间



}
