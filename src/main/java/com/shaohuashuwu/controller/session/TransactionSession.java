package com.shaohuashuwu.controller.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.net.URLDecoder;
import java.util.HashMap;

/**
 * 包:com.shaohuashuwu.controller.session
 * 作者:王洪斌
 * 日期:2020/10/14
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/transactionSession")
@SessionAttributes(value = {"goldCoin_total_income","goldCoin_already_withdraw"},types = {Integer.class})
public class TransactionSession {

    /**
     * 向session中存入Transaction中的 所有金币收入以及已提现的金币数
     */
    @ResponseBody
    @RequestMapping(path = "/saveTransactionGoldCoin/{goldCoin_total_income}/{goldCoin_already_withdraw}")
    public String save(Model model, @PathVariable(value = "goldCoin_total_income")Integer goldCoin_total_income, @PathVariable(value = "goldCoin_already_withdraw")Integer goldCoin_already_withdraw){
        //模拟登录的的时候进行的一些操作，包括根据ID获取用户信息，
        // 把部分信息放进session域中。

        System.out.println("向session域中存入transaction的金币数量数据");
        model.addAttribute("goldCoin_total_income",goldCoin_total_income);
        model.addAttribute("goldCoin_already_withdraw",goldCoin_already_withdraw);
        return "TransactionGoldCoin's Loading is complete";
    }

    /**
     * 从session中获取work的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/getTransactionGoldCoin")
    public HashMap<String,Object> get(ModelMap modelMap){
        Integer goldCoin_total_income = (Integer)modelMap.get("goldCoin_total_income");
        Integer goldCoin_already_withdraw = (Integer)modelMap.get("goldCoin_already_withdraw");

        HashMap<String,Object> map = new HashMap();
        map.put("goldCoin_total_income",goldCoin_total_income);
        map.put("goldCoin_already_withdraw",goldCoin_already_withdraw);
        return map;
    }

    /**
     * 清除session中的值
     * 注意：全部删除，并不能指定删除
     */
    @RequestMapping(path = "/deleteWork")
    @ResponseBody
    public String delete(SessionStatus status){
        status.setComplete();
        return "delete add session success";
    }
}
