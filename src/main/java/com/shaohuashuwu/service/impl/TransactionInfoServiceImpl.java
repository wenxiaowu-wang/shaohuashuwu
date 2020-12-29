package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    public UserInfoDao userInfoDao;
    @Autowired
    public TransactionInfoDao transactionInfoDao;

    //单个订阅章节（事务回滚）
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public boolean subscribeAChapterGUN(int userId,int subBeanNum,int chapterId,int addBeanNum) {

        boolean Result = false;

        //通过章节ID查询到作者的ID
        int  authorID = userInfoDao.selectAuthorIDByChapterID(chapterId);

        //扣除用户金豆数
        boolean subUserBeanNum = false;
        if (userInfoDao.updateGoldBeanNumByUserId(userId, subBeanNum) != (0)) {
            subUserBeanNum = true;
        }

        //增加作者金豆数
        boolean addAuthorBeanNum = false;
        if (userInfoDao.updateGoldBeanNumByUserId(authorID, addBeanNum) != (0)) {
            addAuthorBeanNum = true;
        }

        //将订阅记录存到交易记录表
        TransactionInfo transactionInfo = new TransactionInfo();
        Date date = new Date();
        System.out.println("date is "+date.toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("timestamp is "+timestamp.toString());
        //交易记录流水ID不用设置，在数据库中自增补齐
        transactionInfo.setConsumer_id(userId);//消费者ID
        transactionInfo.setRecipent_id(chapterId);//接受者ID（作品ID）(更改为章节ID)
        transactionInfo.setTransaction_type(2);//1表示交易类型是打赏
        transactionInfo.setTransaction_mode(11);//交易方式(除了0和1外，其它的不进行解析)
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(addBeanNum);//交易数量
        transactionInfo.setTransaction_unit("个金豆");
        boolean saveResult = false;
        if(transactionInfoDao.insertTransactionInfo(transactionInfo)!=0){
            saveResult = true;
        }


        if(subUserBeanNum&&addAuthorBeanNum&&saveResult){
            Result = true;
        }

        return Result;
    }
}
