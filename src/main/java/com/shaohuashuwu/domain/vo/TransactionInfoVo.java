package com.shaohuashuwu.domain.vo;

import java.sql.Timestamp;

/**
 * 包:com.shaohuashuwu.domain.vo
 * 作者:王洪斌
 * 日期:2020/11/3
 * 项目:shaohuashuwu
 * 描述: 用于方便读取交易记录的展示
 */
public class TransactionInfoVo {
    private Integer transaction_id;         //交易ID
    private Integer consumer_id;            //消费者
    private Integer recipient_id;            //接受者(当交易类型为2订阅时，接受者为作品ID)
    private String recipient_name;          //接受者名字
    private String transaction_type;       //交易类型(0充值、1打赏、2订阅、3投票)
    private String transaction_mode;       //交易方式
    private String transaction_time; //交易时间
    private Integer transaction_quantity;   //交易数量
    private String transaction_unit;    //交易单位

    public TransactionInfoVo() {

    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public Integer getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(Integer recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_mode() {
        return transaction_mode;
    }

    public void setTransaction_mode(String transaction_mode) {
        this.transaction_mode = transaction_mode;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public Integer getTransaction_quantity() {
        return transaction_quantity;
    }

    public void setTransaction_quantity(Integer transaction_quantity) {
        this.transaction_quantity = transaction_quantity;
    }

    public String getTransaction_unit() {
        return transaction_unit;
    }

    public void setTransaction_unit(String transaction_unit) {
        this.transaction_unit = transaction_unit;
    }
}
