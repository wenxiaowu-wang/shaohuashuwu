package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;

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

    @Autowired
    private TransactionInfoService transactionInfoService;

    //添加交易信息（充值）
    @RequestMapping(path = "/addTopUpsInfo")
    public String addTopUpsInfo(TransactionInfo transactionInfo){
        String addResult = "loginFaile";
        if (transactionInfoService.addTopUpsInfo(transactionInfo)){
            addResult = "loginSuccess";
        }
        return addResult;
    }
    //跳转到打赏页面（跳转）
    @RequestMapping(path = "/topUpsInterface")
    public String topUpsInterface(){
        System.out.println("将跳转到打赏页面");
        return "topUpsInterface.html";
//        return "loginSuccess.jsp";
    }

    //充值
    @ResponseBody
    @RequestMapping(path = "/topUpsGoldBean/{name}/{method}/{money}")
    public boolean topUpsGoldBean(@PathVariable(value = "name") String name,@PathVariable(value = "method") String  method,@PathVariable(value = "money") String money){
        boolean topUpsResult = false;
        TransactionInfo transactionInfo = new TransactionInfo();
        int topUpsMethod = Integer.parseInt(method);//字符串转化为int
        int topUpsMoney = Integer.parseInt(money);
        Date date = new Date();
        System.out.println("date is "+date.toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("timestamp is "+timestamp.toString());
        transactionInfo.setTransaction_id(10);
        transactionInfo.setConsumer_id(1);
        transactionInfo.setRecipent_id(0);//0表示接受者为韶华书屋平台
        transactionInfo.setTransaction_type(0);//0表示交易类型是充值
        transactionInfo.setTransaction_mode(topUpsMethod);//交易方式
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(topUpsMoney);//交易数量
        transactionInfo.setTransaction_unit("元人民币");
        System.out.println("充值信息为："+transactionInfo.toString());
        return topUpsResult;
    }

}
