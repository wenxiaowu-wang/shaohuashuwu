package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.vo.TransactionInfoVo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    public ChapterPostInfoDao chapterPostInfoDao;

    @Autowired
    public ChapterInfoDao chapterInfoDao;

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
    public boolean tipWork(TransactionInfo tipInfo,int work_id) {
        boolean tipResult = false;
        //根据被打赏作品ID获取对应作品作者ID
        int author_id = worksInfoDao.selectAuthorIdByWorkId(work_id);
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
    public boolean voteWork(TransactionInfo voteInfo,int work_id) {
        boolean voteResult = false;
        if (/*根据消费者ID扣除对应用户推荐票数，根据作品ID增加目标图书的推荐票数*/
                userInfoDao.updateTicketNumByUserId(work_id,voteInfo.getTransaction_quantity() * (-1)) != 0
                && worksInfoDao.updateWorkVoteNumByWorkId(work_id,voteInfo.getTransaction_quantity()) != 0
                && transactionInfoDao.insertTransactionInfo(voteInfo)!=(0)){
            voteResult = true;
        }
        return voteResult;
    }

    /**
     * 获取该用户所有的消费记录（所有的记录）
     * @param user_id 消费者ID
     * @return 交易记录值对象纪集合
     */
    //获取该用户所有消费记录
    @Override
    public List<TransactionInfoVo> getAllConsumptionTransactionInfo(int user_id) {
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        List<TransactionInfo> getDao = new ArrayList<TransactionInfo>();
        getDao = transactionInfoDao.selectConsumptionInfoByUserId(user_id);
        if (getDao.size() != 0){
            for (int i=0;i<getDao.size();i++){
                String name = "";
                switch (getDao.get(i).getTransaction_type()){
                    case 0:
                    case 4:{
                        //接收人名字获取：充值接收人为韶华书屋平台，打赏、订阅、投票的接收人为作者以及作品名字，提现的接收人为用户
                        name = userInfoDao.selectUserNameById(getDao.get(i).getRecipent_id());
                        break;
                    }
                    case 1:
                    case 2:
                    case 3:{
                        //获取接受者ID对应的作品名和章节标题，将其进行字符串拼接
                        name = chapterPostInfoDao.selectWorkNameByChapterId(getDao.get(i).getRecipent_id());
                        name += chapterInfoDao.selectChapterTitleByChapterId(getDao.get(i).getRecipent_id());
                        break;
                    }
                    default:name = "未知";break;
                }
                TransactionInfoVo transactionInfoVo = new TransactionInfoVo();
                transactionInfoVo.setTransaction_id(getDao.get(i).getTransaction_id());
                transactionInfoVo.setConsumer_id(getDao.get(i).getConsumer_id());
                transactionInfoVo.setRecipient_id(getDao.get(i).getRecipent_id());
                transactionInfoVo.setRecipient_name(name);
                transactionInfoVo.setTransaction_type(getDao.get(i).analysisType());
                transactionInfoVo.setTransaction_mode(getDao.get(i).analysisMode());
                transactionInfoVo.setTransaction_time(getDao.get(i).analysisTime());
                transactionInfoVo.setTransaction_quantity(getDao.get(i).getTransaction_quantity());
                transactionInfoVo.setTransaction_unit(getDao.get(i).getTransaction_unit());
                getResult.add(transactionInfoVo);
            }
        }
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
    public List<TransactionInfoVo> getAllIncomeTransactionInfo(int user_id) {
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        List<TransactionInfo> getDao = transactionInfoDao.selectIncomeInfoByUserId(user_id);
        //遍历从dao层获取到的数据，处理后封装到transactionInfoVo对象中
        if (getDao.size() != 0){
            for (int i=0;i<getDao.size();i++){
                String work_name = "待赋值";
                String chapter_name = "待赋值";
                String consumer_name = "待赋值";
                int type = getDao.get(i).getTransaction_type();
                //如果是0(充值)，暂不考虑，先行跳过
                if ( type == 0){
                    continue;
                }
                switch (getDao.get(i).getTransaction_type()){
                    case 0:
                    case 4:{
                        consumer_name = "韶华书屋平台";
                        break;
                    }
                    case 1:
                    case 2:
                    case 3:{
                        //获取接受者ID对应的作品名和章节标题，将其进行字符串拼接(打赏、订阅、投票接受者都是对应章节ID)
                        work_name = chapterPostInfoDao.selectWorkNameByChapterId(getDao.get(i).getRecipent_id());
                        chapter_name = chapterInfoDao.selectChapterTitleByChapterId(getDao.get(i).getRecipent_id());
                        consumer_name = userInfoDao.selectUserNameById(getDao.get(i).getConsumer_id());
                        break;
                    }
                    default:work_name = "未知"; chapter_name = "未知";break;
                }
                TransactionInfoVo transactionInfoVo = new TransactionInfoVo();
                transactionInfoVo.setTransaction_id(getDao.get(i).getTransaction_id());
                transactionInfoVo.setConsumer_id(getDao.get(i).getConsumer_id());
                transactionInfoVo.setConsumer_name(consumer_name);
                transactionInfoVo.setRecipient_id(getDao.get(i).getRecipent_id());//还是对应的章节ID（打赏、订阅、投票）
                transactionInfoVo.setRecipient_name(work_name);
                transactionInfoVo.setRecipient_name_other(chapter_name);
                transactionInfoVo.setTransaction_type(getDao.get(i).analysisType());
                transactionInfoVo.setTransaction_mode(getDao.get(i).analysisMode());
                transactionInfoVo.setTransaction_time(getDao.get(i).analysisTime());
                transactionInfoVo.setTransaction_quantity(getDao.get(i).getTransaction_quantity());
                transactionInfoVo.setTransaction_unit(getDao.get(i).getTransaction_unit());
                getResult.add(transactionInfoVo);
            }
        }
        return getResult;
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
