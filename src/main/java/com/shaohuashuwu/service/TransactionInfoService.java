package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.vo.TransactionInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface TransactionInfoService {

    //充值金豆（记录充值信息并添加对应用户金豆数量）
    public boolean topUpsGoldBean(TransactionInfo topUpsInfo);

    //打赏作品
    public boolean tipWork(TransactionInfo tipInfo,int work_id);

    //投票作品
    public boolean voteWork(TransactionInfo voteInfo,int work_id);

    //获取该用户所有消费记录
    public List<TransactionInfoVo> getAllConsumptionTransactionInfo(int user_id);

    //获取该用户所有金币收入数量
    public int getAllIncomeGoldMount(int user_id);

    //获取该用户所有收入记录
    public List<TransactionInfoVo> getAllIncomeTransactionInfo(int user_id);

    //获取该用户所有提现记录
    public List<TransactionInfoVo> getTransactionOfWithdraw(int user_id);

    //提现金币（等待第三方转账成功，保存提现记录）
    public TransactionInfo withdrawMoney(TransactionInfo withdrawInfo,String third_party_number);

    //获取该作品的被订阅的所有记录
    public List<TransactionInfo> getSubscribeInfo(int work_id);

    //统计近一个月的某作品的订阅总量分布
    public Map<String,List<Map<String,Object>>> getSubscriptionStatisticsData(int work_id);

    //获取该作品订阅的其它统计数据
    public Map<String,Object> getOtherSubscriptionStatisticsData(int work_id);
}
