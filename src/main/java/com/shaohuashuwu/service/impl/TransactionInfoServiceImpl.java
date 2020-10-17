package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.TransactionInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
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

    @Autowired
    public UserInfoDao userInfoDao;

    @Autowired
    public WorksInfoDao worksInfoDao;

    //充值金豆（记录充值信息并添加对应用户金豆数量）
    @Override
    public boolean topUpsGoldBean(TransactionInfo topUpsInfo) {
        boolean addResult = false;

        if (transactionInfoDao.insertTransactionInfo(topUpsInfo) != 0){
            System.out.println("log:充值预备 +"+topUpsInfo.getTransaction_quantity() * 100+"金豆");

            if (userInfoDao.updateGoldBeanNumByUserId(topUpsInfo.getConsumer_id(),topUpsInfo.getTransaction_quantity() * 100) !=0){
                addResult = true;
                System.out.println("log:充值成功 +"+topUpsInfo.getTransaction_quantity() * 100+"金豆");
            }else{
                System.out.println("log:充值失败 ");

            }


        }
        return addResult;
    }

    /**
     * 已完成
     * @param tipInfo
     * @return
     */
    //打赏作品
    @Override
    public boolean tipWork(TransactionInfo tipInfo) {
        boolean tipResult = false;
        //根据被打赏作品ID获取对应作品作者ID
        int author_id = worksInfoDao.selectAuthorIdByWorkId(tipInfo.getRecipent_id());
        if (/*扣除消费用户金豆数，增加目标用户的金币数*/
                userInfoDao.updateGoldBeanNumByUserId(tipInfo.getConsumer_id(),tipInfo.getTransaction_quantity() * (-1)) != 0
                && userInfoDao.updateGoldCoinNumByUserId(author_id,tipInfo.getTransaction_quantity()/10) != 0
                && transactionInfoDao.insertTransactionInfo(tipInfo)!=(0)){
            tipResult = true;
        }
        System.out.println("log: 打赏成功 ["+tipInfo.getConsumer_id()+"]消费了 ["+tipInfo.getTransaction_quantity()+"]金豆|***|["+author_id+"]被打赏了["+tipInfo.getTransaction_quantity()/10+"]金币");
        return tipResult;
    }

    /**
     * 待完成(暂未减少使用推荐票的用户的推荐票数)
     * @param voteInfo
     * @return
     */
    //投票作品
    @Override
    public boolean voteWork(TransactionInfo voteInfo) {
        boolean voteResult = false;
        if (/*根据消费者ID扣除对应用户推荐票数，根据作品ID增加目标图书的推荐票数*/
                userInfoDao.updateTicketNumByUserId(voteInfo.getConsumer_id(),voteInfo.getTransaction_quantity() * (-1)) != 0
                && worksInfoDao.updateWorkVoteNumByWorkId(voteInfo.getRecipent_id(),voteInfo.getTransaction_quantity()) != 0
                && transactionInfoDao.insertTransactionInfo(voteInfo)!=(0)){
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
