package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.TransactionInfoDao;
import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */

@Service("transactionInfoService")
public class TransactionInfoServiceImpl implements TransactionInfoService {

    @Autowired
    public TransactionInfoDao transactionInfoDao;

    //添加一条充值记录
    @Override
    public boolean addTopUpsInfo(TransactionInfo topUpsInfo) {
        boolean addResult = false;
        if (transactionInfoDao.insertTransactionInfo(topUpsInfo) != 0){
            addResult = true;
        }
        return addResult;
    }

    /**
     * 待完成
     * @param tipInfo
     * @return
     */
    //打赏作品
    @Override
    public boolean tipWork(TransactionInfo tipInfo) {
        boolean tipResult = false;
        if (/*扣除对应用户金豆数，增加目标用户的金币数*/
                transactionInfoDao.insertTransactionInfo(tipInfo)!=(0)){
            tipResult = true;
        }
        return tipResult;
    }

    /**
     * 待完成
     * @param voteInfo
     * @return
     */
    //投票作品
    @Override
    public boolean voteWork(TransactionInfo voteInfo) {
        boolean voteResult = false;
        if (/*根据消费者ID扣除对应用户推荐票数，根据作品ID增加目标图书的推荐票数*/
                transactionInfoDao.insertTransactionInfo(voteInfo)!=(0)){
            voteResult = true;
        }
        return voteResult;
    }

    //获取该用户所有消费记录
    @Override
    public List<TransactionInfo> getAllConsumptionTransactionInfo(int user_id) {
        List<TransactionInfo> getResult = null;
        getResult = transactionInfoDao.selectConsumptionInfoByUserId(user_id);
        return getResult;
    }

    //获取该用户所有金币收入数量
    @Override
    public int getAllIncomeGoldMount(int user_id) {
        int goldNum = 0;
        goldNum = transactionInfoDao.selectAllIncomeGoldNum(user_id);
        return goldNum;
    }

    //获取该用户所有收入记录
    @Override
    public List<TransactionInfo> getAllIncomeTransactionInfo(int user_id) {
        return transactionInfoDao.selectIncomeInfoByUserId(user_id);
    }

    //获取该用户所有提现记录
    @Override
    public List<TransactionInfo> getTransactionOfWithdraw(int user_id) {
        return transactionInfoDao.selectWithdrawInfoByUserId(user_id);
    }

    //提现金币（等待第三方转账成功，保存提现记录）
    @Override
    public boolean withdrawMoney(TransactionInfo withdrawInfo) {
        boolean withdrawResult = false;
        if (/*等待第三方转账成功*/
        transactionInfoDao.insertTransactionInfo(withdrawInfo)!=(0)){
            withdrawResult = true;
        }
        return withdrawResult;
    }

    //获取该作品的被订阅的所有记录
    @Override
    public List<TransactionInfo> getSubscribeInfo(int work_id) {
        return transactionInfoDao.selectSubscribeInfoByWorkId(work_id);
    }
}
