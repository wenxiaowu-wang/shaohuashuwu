package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
