package com.shaohuashuwu.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransactionInfo implements Serializable {

    private Integer transaction_id;         //交易ID
    private Integer consumer_id;            //消费者
    private Integer recipient_id;            //接受者(当交易类型为2订阅时，接受者为作品ID)
    private Integer transaction_type;       //交易类型
    private Integer transaction_mode;       //交易方式
    private Timestamp transaction_time; //交易时间
    private Integer transaction_quantity;   //交易数量
    private String transaction_unit;    //交易单位


    public TransactionInfo() {
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

    public Integer getRecipent_id() {
        return recipient_id;
    }

    public void setRecipent_id(Integer recipent_id) {
        this.recipient_id = recipent_id;
    }

    public Integer getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Integer transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Integer getTransaction_mode() {
        return transaction_mode;
    }

    public void setTransaction_mode(Integer transaction_mode) {
        this.transaction_mode = transaction_mode;
    }

    public Timestamp getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(Timestamp transaction_time) {
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
    @Override
    public String toString() {
        return "TransactionInfo{" +
                "transaction_id=" + transaction_id +
                ", consumer_id=" + consumer_id +
                ", recipent_id=" + recipient_id +
                ", transaction_type=" + transaction_type +
                ", transaction_mode=" + transaction_mode +
                ", transaction_time=" + transaction_time +
                ", transaction_quantity=" + transaction_quantity +
                ", transaction_unit='" + transaction_unit + '\'' +
                '}';
    }
}
