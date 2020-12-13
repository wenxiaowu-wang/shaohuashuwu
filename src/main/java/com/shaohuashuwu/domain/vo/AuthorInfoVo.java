package com.shaohuashuwu.domain.vo;

import com.shaohuashuwu.domain.UserInfo;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.math.BigDecimal;

public class AuthorInfoVo  extends UserInfo  implements Serializable {


    private BigDecimal attention_num;  //关注数量
    private Long allwork_word_num; //作品总字数


    public AuthorInfoVo(Integer user_id, String user_name, String head_portrait, Integer gold_coin_num, BigDecimal attention_num, Long allwork_word_num) {
        super(user_id, user_name, head_portrait, gold_coin_num);
        this.attention_num = attention_num;
        this.allwork_word_num = allwork_word_num;
    }

    public BigDecimal getAttention_num() {
        return attention_num;
    }

    public void setAttention_num(BigDecimal attention_num) {
        this.attention_num = attention_num;
    }

    public Long getAllwork_word_num() {
        return allwork_word_num;
    }

    public void setAllwork_word_num(Long allwork_word_num) {
        this.allwork_word_num = allwork_word_num;
    }

    //    public Integer getAttention_num() {
//        return attention_num;
//    }
//
//    public void setAttention_num(Integer attention_num) {
//        this.attention_num = attention_num;
//    }
//
//    public Integer getAllwork_word_num() {
//        return allwork_word_num;
//    }
//
//    public void setAllwork_word_num(Integer allwork_word_num) {
//        this.allwork_word_num = allwork_word_num;
//    }

    @Override
    public String toString() {
        return "AuthorInfoVo{" +
                super.toString()+
                "allwork_word_num=" + allwork_word_num +
                "attention_num=" + attention_num +
                '}';
    }
}
