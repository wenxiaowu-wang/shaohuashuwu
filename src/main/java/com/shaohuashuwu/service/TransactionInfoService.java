package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.TransactionInfo;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface TransactionInfoService {

    //添加一条充值记录
    public boolean addTopUpsInfo(TransactionInfo topUpsInfo);

    //打赏作品
    public boolean tipWork(TransactionInfo tipInfo);

    //投票作品
    public boolean voteWork(TransactionInfo voteInfo);

    //获取该用户所有消费记录
    public List<TransactionInfo> getAllConsumptionTransactionInfo(int user_id);

    //获取该用户所有金币收入数量
    public int getAllIncomeGoldMount(int user_id);

    //获取该用户所有收入记录
    public List<TransactionInfo> getAllIncomeTransactionInfo(int user_id);

    //获取该用户所有提现记录
    public List<TransactionInfo> getTransactionOfWithdraw(int user_id);

    //提现金币（等待第三方转账成功，保存提现记录）
    public boolean withdrawMoney(TransactionInfo withdrawInfo);

    //获取该作品的被订阅的所有记录
    public List<TransactionInfo> getSubscribeInfo(int work_id);
}
