package com.shaohuashuwu.domain.vo;

/**
 * 包:com.shaohuashuwu.domain.vo
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public class AttentionInfoVo {

    private int user_id;            //用户ID
    private String user_name;       //用户名
    private String head_portrait;   //用户头像
    //在加上用户的几个作品最多三个
    private String works_name; //作品名

    public AttentionInfoVo() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getWorks_name() {
        return works_name;
    }

    public void setWorks_name(String works_name) {
        this.works_name = works_name;
    }

    @Override
    public String toString() {
        return "AttentionInfoVo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", head_portrait='" + head_portrait + '\'' +
                ", works_name='" + works_name + '\'' +
                '}';
    }
}
