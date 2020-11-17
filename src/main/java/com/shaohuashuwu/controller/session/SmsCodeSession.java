package com.shaohuashuwu.controller.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.Random;

/**
 * 包:com.shaohuashuwu.controller.session
 * 作者:王洪斌
 * 日期:2020/10/14
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/smsCodeSession")
@SessionAttributes(value = {"code","phone_number","code_time"},types = {String.class})
public class SmsCodeSession {

    /**
     * 向session中存入Transaction中的 所有金币收入以及已提现的金币数
     */
    @ResponseBody
    @RequestMapping(path = "/sendSms/{phone_number}")
    public String save(Model model, @PathVariable(value = "phone_number")String phone_number){
        //模拟登录的的时候进行的一些操作，包括根据ID获取用户信息，
        //1.生成一个6位验证码
        Random random = new Random();
        int one_code = random.nextInt(999999);
        if (one_code < 100000) {//位数处理
            one_code = one_code + 100000;
        }
        String code = Integer.toString(one_code);
        // 把部分信息放进session域中。
        String code_time = System.currentTimeMillis()+"";
        System.out.println("向session域中存入验证码以及手机号等数据");
        model.addAttribute("code",code);
        model.addAttribute("phone_number",phone_number);
        model.addAttribute("code_time",code_time);

        System.out.println("生成的code为："+code);
        return "Sms's Loading is complete";
    }

    /**
     * 从session中获取work的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/compareSms/{commit_code}/{commit_phone_number}")
    public Boolean compareSms(ModelMap modelMap,@PathVariable(value = "commit_code")String commit_code,@PathVariable(value = "commit_phone_number")String commit_phone_number){
        boolean compareResult = false;
        String code = (String)modelMap.get("code");
        String code_time = (String)modelMap.get("code_time");
        String phone_number = (String)modelMap.get("phone_number");

        long time = 0L;
        time += Long.parseLong(code_time.substring(0,7))*100000;
        time += Long.parseLong(code_time.substring(8,12));
        System.out.println(phone_number+"的 time 等于:"+time);

        long subTime = (System.currentTimeMillis() - time) / (1000 * 60);
        System.out.println("时间差为："+subTime);
        if (commit_code.equals(code) && commit_phone_number.equals(phone_number) && subTime < 5L){
            compareResult = true;
        }

        return compareResult;
    }

    /**
     * 清除session中的值
     * 注意：全部删除，并不能指定删除
     */
    @RequestMapping(path = "/deleteSmsCode")
    @ResponseBody
    public String delete(SessionStatus status){
        status.setComplete();
        return "delete add session success";
    }
}
