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


}
