package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.vo.TransactionInfoVo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/transactionInfoController")
@SessionAttributes(value = {"user_name","user_id"},types = {String.class,Integer.class})
public class TransactionInfoController {

    @Autowired
    private TransactionInfoService transactionInfoService;

    //添加交易信息（充值）[单纯保存信息，未进行更改用户金豆数量等操作]
    @RequestMapping(path = "/addTopUpsInfo")
    public String addTopUpsInfo(TransactionInfo transactionInfo){
        String addResult = "loginFaile";
        if (transactionInfoService.topUpsGoldBean(transactionInfo)){
            addResult = "loginSuccess";
        }
        return addResult;
    }
    //跳转到打赏页面（跳转）
    @RequestMapping(path = "/topUpsInterface")
    public String topUpsInterface(){
        System.out.println("将跳转到购买金豆页面");
        //跳转时模拟已经登录，将用户的账号以及用户名信息放进session域中
//        model.addAttribute("user_id",11);
//        model.addAttribute("user_name","我吃唐家土豆");

        return "topUpsInterface.html";
//        return "loginSuccess.jsp";
    }

    //跳转到打赏页面（跳转）
    @RequestMapping(path = "/rewardInterface")
    public String rewardInterface(){
        System.out.println("将跳转到打赏作品页面");

        return "rewardInterface.html";
    }

    //跳转到打赏页面（跳转）
    @RequestMapping(path = "/voteInterface")
    public String voteInterface(){
        System.out.println("将跳转到投推荐票页面");
        return "voteInterface.html";
    }


    /**
     * 获取用户名
     * @param modelMap：用来取出登录时存到session域中的用户信息值
     * @return          HashMap 存储的user_id 以及 user_name
     */
    @ResponseBody
    @RequestMapping(path = "/get")
    public HashMap<String,Object> get(ModelMap modelMap){
        //跳转时模拟已经登录，将用户的账号以及用户名信息放进session域中
        //通过此代码块从session中获取
        Integer user_id = (Integer)modelMap.get("user_id");
        String user_name = (String)modelMap.get("user_name");
        System.out.println("user_id = "+user_id+",user_name = "+user_name);

        HashMap<String,Object> map = new HashMap();
        map.put("user_id",user_id);
        map.put("user_name",user_name);
        return map;
    }

    /**
     * 充值金豆
     * @param id        用户ID
     * @param method    充值方式
     * @param money     充值金额（元）
     * @return          充值结果，Boolean
     */
    //充值
    @ResponseBody
    @RequestMapping(path = "/topUpsGoldBean/{id}/{method}/{money}")
    public boolean topUpsGoldBean(@PathVariable(value = "id") Integer id,@PathVariable(value = "method") Integer  method,@PathVariable(value = "money") Integer money){
        boolean topUpsResult = false;
        ////        name = URLDecoder.decode(name,"UTF-8");
//        id = URLDecoder.decode(id);
//        System.out.println("name is "+id);
        TransactionInfo transactionInfo = new TransactionInfo();
//        int topUpsMethod = Integer.parseInt(method);//字符串转化为int
//        int topUpsId = Integer.parseInt(id);//字符串转化为int
//        int topUpsMoney = Integer.parseInt(money);
        Date date = new Date();
        System.out.println("date is "+date.toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("timestamp is "+timestamp.toString());
        //交易记录流水ID不用设置，在数据库中自增补齐
        transactionInfo.setConsumer_id(id);//消费者ID
        transactionInfo.setRecipent_id(0);//0表示接受者为韶华书屋平台
        transactionInfo.setTransaction_type(0);//0表示交易类型是充值
        transactionInfo.setTransaction_mode(method);//交易方式
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(money);//交易数量
        transactionInfo.setTransaction_unit("元人民币");
        System.out.println("充值信息为："+transactionInfo.toString());
        //金豆充值操作service
        topUpsResult = transactionInfoService.topUpsGoldBean(transactionInfo);
        return topUpsResult;
    }

    /**
     * 打赏作品
     * @param userId    用户ID
     * @param chapterId    章节ID
     * @param beanNum   打赏金豆数量
     * @return  打赏结果 Boolean
     */
    //打赏作品
    @ResponseBody
    @RequestMapping(path = "/tipsWork/{userId}/{chapterId}/{work_id}/{beanNum}")
    public boolean tipsWork(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "chapterId") Integer chapterId,@PathVariable(value = "work_id")Integer work_id,@PathVariable(value = "beanNum") Integer beanNum){
        boolean tipsResult = false;

        TransactionInfo transactionInfo = new TransactionInfo();
        Date date = new Date();
        System.out.println("date is "+date.toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("timestamp is "+timestamp.toString());
        //交易记录流水ID不用设置，在数据库中自增补齐
        transactionInfo.setConsumer_id(userId);//消费者ID
        transactionInfo.setRecipent_id(chapterId);//接受者ID（作品ID）(更改为章节ID)
        transactionInfo.setTransaction_type(1);//1表示交易类型是打赏
        transactionInfo.setTransaction_mode(11);//交易方式(除了0和1外，其它的不进行解析)
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(beanNum);//交易数量
        transactionInfo.setTransaction_unit("个金豆");
        System.out.println("打赏信息为："+transactionInfo.toString());
        //打赏作品操作service
        tipsResult = transactionInfoService.tipWork(transactionInfo,work_id);
        return tipsResult;
    }

    /**
     * 投推荐票
     * @param userId    用户ID
     * @param chapterId 章节ID
     * @param voteNum   投票数量
     * @return  投票结果 Boolean
     */
    @ResponseBody
    @RequestMapping(path = "/voteWork/{userId}/{chapterId}/{work_id}/{voteNum}")
    public boolean voteWork(@PathVariable(value = "userId")Integer userId,@PathVariable(value = "chapterId")Integer chapterId,@PathVariable(value = "work_id")Integer work_id,@PathVariable(value = "voteNum")Integer voteNum){
        boolean voteResult = false;
        TransactionInfo transactionInfo = new TransactionInfo();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        //交易记录流水ID不用设置，在数据库中自增补齐
        transactionInfo.setConsumer_id(userId);//消费者ID
        transactionInfo.setRecipent_id(chapterId);//接受者ID（作品ID）(更改为章节ID)
        transactionInfo.setTransaction_type(3);//3表示交易类型是投推荐票
        transactionInfo.setTransaction_mode(11);//交易方式(除了0和1外，其它的不进行解析)
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(voteNum);//交易数量
        transactionInfo.setTransaction_unit("张推荐票");
        System.out.println("投票信息为："+transactionInfo.toString());

        voteResult = transactionInfoService.voteWork(transactionInfo,work_id);
        return voteResult;
    }

    /**
     * 获取该用户所有的消费记录（除去提现记录的记录）
     * @param user_id
     * @return
     */
    @RequestMapping(path = "/getAllConsumptionTransactionInfo/{user_id}")
    @ResponseBody
    public List<TransactionInfoVo> getAllConsumerRecords(@PathVariable(value = "user_id")Integer user_id){
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        List<TransactionInfoVo> getService = transactionInfoService.getAllConsumptionTransactionInfo(user_id);
        for(int i=0;i<getService.size();i++){
            switch(getService.get(i).getTransaction_type()){
                case "充值":
                case "打赏":
                case "订阅":
                case "投票":{
                    getResult.add(getService.get(i));
                    break;
                }
                default:break;
            }
        }
        return getResult;
    }

    @RequestMapping(path = "/toPersonalAccount")
    public String toPersonalAccount(){
        return "personalAccountInterface.html";
    }

}
