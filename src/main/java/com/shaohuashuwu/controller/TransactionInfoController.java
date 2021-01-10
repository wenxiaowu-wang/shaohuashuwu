package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.vo.TransactionInfoVo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Timestamp;
import java.util.*;


/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/transactionInfoController")
@SessionAttributes(value = {"user_name","user_id","work_id","work_name","chapter_id","chapter_title","goldCoin_total_income","goldCoin_already_withdraw"},types = {String.class,Integer.class})
public class TransactionInfoController {

    @Autowired
    private TransactionInfoService transactionInfoService;


    /*
     * 郝振威
     * */

    //多个订阅单个章节H(回滚)
    @ResponseBody
    @RequestMapping(value = "/subscribeChapterGUN/{userId}/{chapterId}/{work_id}")
    public Boolean subscribeChapterGUN(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "chapterId") List<Integer> chapterId,@PathVariable(value = "work_id") Integer work_id)  {
        boolean subsResult = true ;

        for (int id : chapterId) {
            subsResult = transactionInfoService.subscribeAChapterGUN(userId, -10, id, 10,work_id);
        }
        return subsResult;
    }

    /**
     * 阿斌
     */
    /**
     * 获取session中的作品名字和章节名字
     * @param modelMap
     * @return 获取到的作品名以及章节名
     */
    @ResponseBody
    @RequestMapping(path = "/getWorkNameAndChapterNameFromSession")
    public HashMap<String,Object> getWorkNameAndChapterNameFromSession(ModelMap modelMap){
        String work_name = (String)modelMap.get("work_name");
        String chapter_title = (String)modelMap.get("chapter_title");
        HashMap<String,Object> map = new HashMap();
        map.put("work_name",work_name);
        map.put("chapter_title",chapter_title);
        return map;
    }

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
        return "topUpsDialog.html";
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

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("user_id",user_id);
        map.put("user_name",user_name);
        return map;
    }

    @ResponseBody
    @RequestMapping(path = "/postIntoSession/{work_id}/{chapter_id}")
    public String postIntoSession(Model model,@PathVariable(value = "work_id")Integer work_id,@PathVariable(value = "chapter_id")Integer chapter_id){
        //跳转时模拟已经登录，将用户的账号以及用户名信息放进session域中
        //通过此代码块从session中获取
        model.addAttribute("work_id",work_id);
        model.addAttribute("chapter_id",chapter_id);
        return "work_id and chapter_id success";
    }

    /**
     * 充值金豆
     * @param modelMap  用户信息缓存
     * @param method    充值方式
     * @param money     充值金额（元）
     * @return          充值结果，Boolean
     */
    //充值
    @ResponseBody
    @RequestMapping(path = "/topUpsGoldBean/{method}/{money}")
    public boolean topUpsGoldBean(ModelMap modelMap,@PathVariable(value = "method") Integer  method,@PathVariable(value = "money") Integer money){
        //通过此代码块从session中获取
        Integer id = (Integer)modelMap.get("user_id");
        TransactionInfo transactionInfo = new TransactionInfo();

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
        return transactionInfoService.topUpsGoldBean(transactionInfo);
    }

    /**
     * 打赏作品
     * @param beanNum   打赏金豆数量
     * @return  打赏结果 Boolean
     */
    //打赏作品
    @ResponseBody
    @RequestMapping(path = "/tipsWork/{beanNum}")
    public boolean tipsWork(ModelMap modelMap,@PathVariable(value = "beanNum") Integer beanNum){
        boolean tipsResult = false;

        Integer userId = (Integer)modelMap.get("user_id");
        Integer chapterId = (Integer)modelMap.get("chapter_id");
        Integer work_id = (Integer)modelMap.get("work_id");

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
     * @param modelMap session域中的一块域的变量名
     * @param voteNum   投票数量
     * @return  投票结果 Boolean
     */
    @ResponseBody
    @RequestMapping(path = "/voteWork/{voteNum}")
    public boolean voteWork(ModelMap modelMap,@PathVariable(value = "voteNum")Integer voteNum){
        boolean voteResult = false;

        Integer userId = (Integer)modelMap.get("user_id");
        Integer chapterId = (Integer)modelMap.get("chapter_id");
        Integer work_id = (Integer)modelMap.get("work_id");

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

    //提现金币
    @RequestMapping(path = "/withdrawGoldCoin/{quantity}/{mode}/{third_party_number}")
    @ResponseBody
    public TransactionInfo withdrawGoldCoin(Model model,ModelMap modelMap,@PathVariable(value = "quantity")Integer quantity,
                                            @PathVariable(value = "mode")Integer mode,@PathVariable(value = "third_party_number")String third_party_number){
        Integer author_id = (Integer)modelMap.get("user_id");
        Integer already_withdraw = (Integer)modelMap.get("goldCoin_already_withdraw");
        int num = already_withdraw + quantity*10;
        model.addAttribute("goldCoin_already_withdraw",num);
        TransactionInfo transactionInfo = new TransactionInfo();
        //调用transactionService的添加一条记录（其中包含对该用户的金币数减少）
        //封装数据，调用transactionInfoService
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        //交易记录流水ID不用设置，在数据库中自增补齐
        transactionInfo.setConsumer_id(0);//代表韶华书屋平台
        transactionInfo.setRecipent_id(author_id);//接受者ID（作者ID）
        transactionInfo.setTransaction_type(4);//4表示交易类型是提现
        transactionInfo.setTransaction_mode(mode);//交易方式(除了0和1外，其它的不进行解析)
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(quantity);//交易数量
        transactionInfo.setTransaction_unit("元人民币");
        //返回插入的交易记录
        TransactionInfo transactionInfo1 = new TransactionInfo();
        transactionInfo1 = transactionInfoService.withdrawMoney(transactionInfo,third_party_number);
        return transactionInfo1;
    }

    /**
     * 获取该用户所有的消费记录（除去提现记录的记录）
     * @param modelMap session中的域变量 可用来获取该用户ID
     * @return
     */
    @RequestMapping(path = "/getAllConsumptionTransactionInfo")
    @ResponseBody
    public List<TransactionInfoVo> getAllConsumerRecords(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
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

    /**
     * 根据用户（作者ID）获取对应收入的交易记录信息（值对象集合）
     * @param modelMap 用于从session中获取用户（作者）ID
     * @return 对应收入记录值对象
     */
    @RequestMapping(path = "/getAllIncomeTransactionInfo")
    @ResponseBody
    public List<TransactionInfoVo> getAllIncomeTransactionInfo(ModelMap modelMap){
        Integer author_id = (Integer)modelMap.get("user_id");
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        getResult = transactionInfoService.getAllIncomeTransactionInfo(author_id);
        return getResult;
    }

    //获取该用户所有提现记录
    @RequestMapping(path = "/getAllWithdrawInfo")
    @ResponseBody
    public List<TransactionInfoVo> getAllWithdrawInfo(ModelMap modelMap){
        Integer author_id = (Integer)modelMap.get("user_id");
        return transactionInfoService.getTransactionOfWithdraw(author_id);
    }

    @RequestMapping(path = "/toRemunerationInterface")
    public String toRemunerationInterface(){
        return "remunerationInterface.html";
    }

    @RequestMapping(path = "/toPersonalAccount")
    public String toPersonalAccount(){
        return "personalAccountInterface.html";
    }

    @RequestMapping(path = "/toWorkDataStatisticsInterface")
    public String toWorkDataStatisticsInterface(){
        return "workDataStatisticsInterface.html";
    }

    //获取作品的订阅记录统计分布信息
    @RequestMapping(path = "/getSubscriptionStatisticsData/{work_id}")
    @ResponseBody
    public Map<String,List<Map<String,Object>>> getSubscriptionStatisticsData(@PathVariable(value = "work_id") int work_id){
        return transactionInfoService.getSubscriptionStatisticsData(work_id);
    }

    //获取作品的其它订阅统计数据getOtherSubscriptionStatisticsData
    @RequestMapping(path = "/getOtherSubscriptionStatisticsData/{work_id}")
    @ResponseBody
    public Map<String,Object> getOtherSubscriptionStatisticsData(@PathVariable(value = "work_id") int work_id){
        return transactionInfoService.getOtherSubscriptionStatisticsData(work_id);
    }

}
