package com.shaohuashuwu.controller.session;


import com.shaohuashuwu.utils.CurrentTimesUtils;

import com.shaohuashuwu.utils.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 包:com.shaohuashuwu.controller.session
 * 作者:郝振威
 * 日期:2020/10/14
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/smsCodeSession")
@SessionAttributes(value = {"code","phone_number","code_time"},types = {String.class})
public class SmsCodeSession {

    @Autowired
    public CurrentTimesUtils times;


    /**
     *登录向session中存入code
     */
    @ResponseBody
    @RequestMapping(path = "/loginSendSms/{phone_number}")
    public String login(Model model, @PathVariable(value = "phone_number")String phone_number){
        //模拟登录的的时候进行的一些操作，包括根据ID获取用户信息，
        //1.生成一个6位验证码
        Random random = new Random();
        int one_code = random.nextInt(999999);
        if (one_code < 100000) {//位数处理
            one_code = one_code + 100000;
        }
        String code = String.valueOf(one_code);
        System.out.println("生成的code为："+code);

        String TemplateCode = "SMS_205459436";

//        SendSms loginSend = new SendSms();
//        loginSend.Send(phone_number,code,TemplateCode);

        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, 5);
        Date beforeD = beforeTime.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获得年月日时分秒


        //第一个发短信的时间  systemTime1
        Date currentTime = new Date();
        String systemDate1 = formatter.format(currentTime);
        String systemTime1 = systemDate1.substring(11,19);
        System.out.println("发短信的当前时间"+systemTime1);

        //五分钟后的时间  systemTime2
        String systemDate2 = formatter.format(beforeD);
        String systemTime2 = systemDate2.substring(11,19);
        System.out.println("五分钟后截取的时间---"+systemTime2);

        // 把部分信息放进session域中。
        System.out.println("向session域中存入验证码以及手机号、时间等数据");
        model.addAttribute("code",code);
        model.addAttribute("phone_number",phone_number);
        model.addAttribute("systemTime1",systemTime1);
        model.addAttribute("systemTime2",systemTime2);

        return "Sms's Loading is complete";
    }
    /**
     * 注册向session中存入code
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
        String code = String.valueOf(one_code);
        System.out.println("生成的code为："+code);

        String TemplateCode = "SMS_204290356";

        //SendSms registerSend = new SendSms();
        //registerSend.Send(phone_number,code,TemplateCode);

        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, 5);
        Date beforeD = beforeTime.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获得年月日时分秒


        //第一个发短信的时间  systemTime1
        Date currentTime = new Date();
        String systemDate1 = formatter.format(currentTime);
        String systemTime1 = systemDate1.substring(11,19);
        System.out.println("发短信的当前时间"+systemTime1);

        //五分钟后的时间  systemTime2
        String systemDate2 = formatter.format(beforeD);
        String systemTime2 = systemDate2.substring(11,19);
        System.out.println("五分钟后截取的时间---"+systemTime2);

        // 把部分信息放进session域中。
        System.out.println("向session域中存入验证码以及手机号、时间等数据");
        model.addAttribute("code",code);
        model.addAttribute("phone_number",phone_number);
        model.addAttribute("systemTime1",systemTime1);
        model.addAttribute("systemTime2",systemTime2);

        return "Sms's Loading is complete";
    }

    /**
     * 从session中获取code的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/compareSms/{commit_code}/{commit_phone_number}")
    public Boolean compareSms(ModelMap modelMap,@PathVariable(value = "commit_code")String commit_code,@PathVariable(value = "commit_phone_number")String commit_phone_number) throws ParseException {

        boolean compareResult = false;

        String format = "HH:mm:ss";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获得年月日时分秒

        //提交注册的时间
        Date currentTime = new Date();
        String systemDate3 = formatter.format(currentTime);
        String systemTime3 = systemDate3.substring(11,19);
        System.out.println("提交注册的时间"+systemTime3);

        String code = (String)modelMap.get("code");
        String systemTime1 = (String)modelMap.get("systemTime1");
        String systemTime2 = (String)modelMap.get("systemTime2");
        String phone_number = (String)modelMap.get("phone_number");

        System.out.println("Session里的code："+code);
        System.out.println("Session里的phone_number："+phone_number);

        Date nowTime = new SimpleDateFormat(format).parse(systemTime3);
        //输入验证码之后，点注册时间
        Date startTime = new SimpleDateFormat(format).parse(systemTime1);
        //发短信时间加五分钟
        Date endTime = new SimpleDateFormat(format).parse(systemTime2);


        if (commit_code.equals(code) && commit_phone_number.equals(phone_number) && times.isEffectiveDate(nowTime, startTime, endTime)){
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
        //status.setComplete();
        return "delete add session success";
    }
}
