package com.shaohuashuwu.domain;

import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.sql.Timestamp;


public class UserInfo implements Serializable {

    //属性
    private Integer user_id;                //用户ID
    private String user_name;           //用户昵称
    private String head_portrait;         //用户头像
    private String gender;              //用户性别
    private Timestamp birthday;         //用户生日
    private String area;                //用户所在地
    private String phone_number;        //用户手机号
    private String password;            //用户密码
    private String double_password;     //二级密码
    private Integer gold_bean_num;          //金豆数量
    private Integer gold_coin_num;          //金豆数量
    private Integer ticket_num;             //推荐票数量

    //成员变量
    @Autowired
    private AttentionInfoVo attentionInfoVo;    //关注信息值对象(成员变量)

    public UserInfo() {
        //无参构造方法
        System.out.println("userinfo无参构造方法");
    }

    public UserInfo(Integer user_id, String user_name, String head_portrait) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.head_portrait = head_portrait;
    }


    public UserInfo(String user_name, String gender, String area, String phone_number, String password, Integer gold_bean_num, Integer gold_coin_num, Integer ticket_num) {
        this.user_name = user_name;
        this.gender = gender;
        this.area = area;
        this.phone_number = phone_number;
        this.password = password;
        this.gold_bean_num = gold_bean_num;
        this.gold_coin_num = gold_coin_num;
        this.ticket_num = ticket_num;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDouble_password() {
        return double_password;
    }

    public void setDouble_password(String double_password) {
        this.double_password = double_password;
    }

    public Integer getGold_bean_num() {
        return gold_bean_num;
    }

    public void setGold_bean_num(Integer gold_bean_num) {
        this.gold_bean_num = gold_bean_num;
    }

    public Integer getGold_coin_num() {
        return gold_coin_num;
    }

    public void setGold_coin_num(Integer gold_coin_num) {
        this.gold_coin_num = gold_coin_num;
    }

    public Integer getTicket_num() {
        return ticket_num;
    }

    public void setTicket_num(Integer ticket_num) {
        this.ticket_num = ticket_num;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", head_portrait=" + head_portrait +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", area='" + area + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", password='" + password + '\'' +
                ", double_password='" + double_password + '\'' +
                ", gold_bean_num=" + gold_bean_num +
                ", gold_coin_num=" + gold_coin_num +
                ", ticket_num=" + ticket_num +
                '}';
    }

    /**
     * 获取对应的关注信息值对象
     * @return
     */

    public AttentionInfoVo toAttentionInfoVo(){

        attentionInfoVo.setUser_id(this.user_id);
        attentionInfoVo.setUser_name(this.user_name);
        attentionInfoVo.setHead_portrait(this.head_portrait);
        return attentionInfoVo;
    }
}
