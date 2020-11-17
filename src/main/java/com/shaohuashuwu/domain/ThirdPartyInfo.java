package com.shaohuashuwu.domain;

//用于提现记录的第三方记录
public class ThirdPartyInfo {

    private Integer transaction_id;        //交易ID（提现ID）
    private String third_number;        //第三方ID

    public ThirdPartyInfo() {
    }

    public Integer getWithdraw_id() {
        return transaction_id;
    }

    public void setWithdraw_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getThird_number() {
        return third_number;
    }

    public void setThird_number(String third_number) {
        this.third_number = third_number;
    }

    @Override
    public String toString() {
        return "ThirdPartyInfo{" +
                "withdraw_id=" + transaction_id +
                ", third_number='" + third_number + '\'' +
                '}';
    }
}
