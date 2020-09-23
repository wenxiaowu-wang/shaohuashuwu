package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.TransactionInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/transactionInfoController")
public class TransactionInfoController {


    //添加交易信息（充值）
    @RequestMapping(path = "/addTopUpsInfo")
    public boolean addTopUpsInfo(TransactionInfo transactionInfo){
        boolean addResult = false;


        return addResult;
    }

}
