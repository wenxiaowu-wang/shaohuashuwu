package com.shaohuashuwu.test;

import com.shaohuashuwu.controller.AttentionInfoController;
import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.AdminInfo;
import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.domain.vo.NoticeInfoVo;
import com.shaohuashuwu.domain.vo.TransactionInfoVo;
import com.shaohuashuwu.service.*;
import com.shaohuashuwu.utils.DateCalculation;
import com.shaohuashuwu.utils.StatisticalHelp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestBackEnd {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Autowired
    private AttentionInfoDao attentionInfoDao;
    /**
     * 测试推送 3
     * 在王洪斌的分支里，为所欲为，改名字
     */
    @Test
    public void testFindAll(){
        List list = accountService.findAll();
        System.out.println(list);
    }

    @Autowired
    private AdminInfoService adminInfoService;

    @Test
    public void deleteTest(){
        if (adminInfoService.deleteAdminInfo("334455")){
            System.out.println("test over");
        }
    }

    @Test
    public void deleteTest1(){
        if (adminInfoService.deleteAdminInfo("334455")){
            System.out.println("test over");
        }
    }

    @Test
    public void updateBeanNumByIdAndNumTest(){
        if (userInfoDao.updateGoldBeanNumByUserId(11,1000)!=0){
            System.out.println("充值金豆测试成功");
        }
    }

    @Test
    public void selectAdminInfoByAdminIdTest(){
        AdminInfo adminInfo = adminInfoDao.selectAdminInfoByAdminId("abin");
        System.out.println(adminInfo.toString());
    }

    /**
     * 测试根据读者ID获取关注者信息
     * 测试接口：AttentionInfoDao.selectAttentionUserInfoByUserId
     */
    @Test
    public void selectAttentionInfoByReaderId(){
        System.out.println("测试根据读者ID获取关注者信息");
        List<UserInfo> selectResult = attentionInfoDao.selectAttentionUserInfoByUserId(11);
        for (int i=0;i<selectResult.size();i++){
            System.out.println(selectResult.get(i).getUser_name());
        }
    }
    /*
    @Autowired
    public AttentionInfoController attentionInfoController;

    @Test
    public void testAttentionInfoControllerGetAttentionInfo(){
        List<AttentionInfoVo> attentionInfoVos = attentionInfoController.getAttentionAuthorInfo(11);
        System.out.println("attentionInfoVos = ");
        for (int i=0;i<attentionInfoVos.size();i++) {
            System.out.println(attentionInfoVos.get(i).getUser_name());
        }
    }
    */
    @Autowired
    public AttentionInfoService attentionInfoService;

    @Test
    public void testAttentionInfoServiceGetAttentionInfo(){
        List<AttentionInfoVo> attentionInfoVos = attentionInfoService.getAttentionAuthorInfo(11);
        System.out.println("attentionInfoVos = ");
        for (int i=0;i<attentionInfoVos.size();i++) {
            System.out.println(attentionInfoVos.get(i).getUser_name());
        }
    }

    @Autowired
    public NoticeInfoService noticeInfoService;

    @Test
    public void testGetAllNoticeInfo(){
        System.out.println("测试获取所有通知信息");
        List<NoticeInfoVo> noticeInfoVoList = noticeInfoService.getAllNoticeInfo(11);
        for (int i=0;i<noticeInfoVoList.size();i++){
            System.out.println(noticeInfoVoList.get(i).toString());
        }
    }

    @Autowired
    public TransactionInfoService transactionInfoService;

    @Test
    public void testGetAllIncomeTransaction(){
        List<TransactionInfoVo> transactionInfoVos = new ArrayList<>();
        transactionInfoVos = transactionInfoService.getAllIncomeTransactionInfo(11);
        for (int i=0;i<transactionInfoVos.size();i++){
            System.out.println(transactionInfoVos.get(i).toString());
        }
        System.out.println("test``````success");
    }

    @Test
    public void testGetAllWithdarw(){
        List<TransactionInfoVo> transactionInfoVos = transactionInfoService.getTransactionOfWithdraw(11);
        for (int i=0;i<transactionInfoVos.size();i++){
            System.out.println(transactionInfoVos.get(i).toString());
        }
    }

    @Autowired
    public TransactionInfoDao transactionInfoDao;

    @Test
    public void testSometimeOfTransactionInfo(){
        String time1 = "2020-10-17 13:50:42";
        String time2 = "2020-11-16 13:50:42";
        List<TransactionInfo> transactionInfos = new ArrayList<TransactionInfo>();
        transactionInfos = transactionInfoDao.selectSomeTimeOfTransactionInfo(time1,time2);
        for (int i=0;i<transactionInfos.size();i++){
            System.out.println(transactionInfos.get(i).toString());
        }
    }

    @Test
    public void testSubscriptionStatisticsData(){
        String gender = "男";
        String start_time = "2020-10-17";
        String end_time = "2020-11-23";
        int work_id = 76;
        List<Map<String,Object>> selectResult = transactionInfoDao.selectSubscriptionStatisticsData(work_id,start_time,end_time,gender);
        System.out.println("获取统计结果的列表条数为："+selectResult.size());
        for (int i=0;i<selectResult.size();i++){
            System.out.println("key[],value[]:"+selectResult.get(i).toString());
        }
    }

    @Test
    public void testResultMapData(){
        List<Map<String,Object>> selectResult = transactionInfoDao.selectTestResultMapData("%");
        System.out.println("获取统计结果的列表条数为："+selectResult.size());
        for (int i=0;i<selectResult.size();i++){
            System.out.println("key[],value[]:"+selectResult.get(i).toString());
        }
    }

    @Test
    public void testServiceOfSubscriptionStatisticsData(){
        //将timestamp类型格式化为String
        Date date = new Date();
        System.out.println("date is "+date.toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date_now = sdf.format(timestamp);
        System.out.println("格式化日期后："+date_now); //2009-07-16
        System.out.println("计算后理应日期减一，输出："+date_now);

    }

    @Autowired
    public WorksInfoDao worksInfoDao;

    @Test
    public void testDataCalculationUtil(){
        //初始化返回值
        Map<String,List<Map<String,Object>>> theResult_all = new HashMap<String,List<Map<String,Object>>>();
        List<Map<String,Object>> theResult_nan = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> theResult_nv = new ArrayList<Map<String,Object>>();

        //初始化日期计算工具类
        String dateFormat = "yyyy-MM-dd";
        DateCalculation dateCalculation = new DateCalculation(dateFormat);

        //计算当前时间 以及 前30天的日期
        int dayNum = -30;
        String day = dateCalculation.getCertainTime(dateCalculation.getRightNow(),dayNum);
        System.out.println(dateCalculation.getRightNow()+" 的前"+dayNum*(-1)+"天为："+day);

        //获取作品发布时间
        Timestamp timestamp = worksInfoDao.selectWorkCreateTimeByWorkId(76);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String workCreateTime = sdf.format(timestamp);
        System.out.println("转化后的作品发布时间："+workCreateTime);

        //比较作品发布时间是否早于一月前时间，根据比较结果进行赋值
        String startTime = dateCalculation.compareTime(workCreateTime,day) ? workCreateTime:day;

        //设置日期计算类的开始以及结束时间
        dateCalculation.setEndDate(dateCalculation.stringToDate(dateCalculation.getRightNow()));
        dateCalculation.setStartDate(dateCalculation.stringToDate(startTime));
        System.out.println("开始时间与结束时间相差天数："+dateCalculation.getDateDifferenceDayNum());

        //获取开始时间到结束时间的所有日期（精确到天）
        List<String> days = dateCalculation.getAllDayBetweenTwo();

        //获取某一时间段的订阅记录统计分布结果（男，女）
        List<Map<String,Object>> getDao_nan = transactionInfoDao.selectSubscriptionStatisticsData(76,startTime,dateCalculation.getRightNow(),"男");
        List<Map<String,Object>> getDao_nv = transactionInfoDao.selectSubscriptionStatisticsData(76,startTime,dateCalculation.getRightNow(),"女");

        /*System.out.println("获取到的订阅统计数据为：");
        for (Map<String,Object> mapData : getDao_nan){
            System.out.println(mapData);
            System.out.println(mapData.get("date_day")+"的数据类型为："+mapData.get("date_day").getClass().getName().toString());
            System.out.println(mapData.get("subscription_quantity")+"的数据类型为："+mapData.get("subscription_quantity").getClass().getName().toString());

            BigDecimal num = (BigDecimal) mapData.get("subscription_quantity");
            int readerNum = num.intValue();
            System.out.println("阅读人数"+readerNum);

        }*/

        //使用自定义的统计帮助工具类的装配方法，将每一天的数据都装起来(男，女)
        StatisticalHelp statisticalHelp = new StatisticalHelp();
        theResult_nan = statisticalHelp.assemblySubscriptionStatistics(days,getDao_nan);
        theResult_nv = statisticalHelp.assemblySubscriptionStatistics(days,getDao_nv);

        //装配最终返回结果
        theResult_all.put("男",theResult_nan);
        theResult_all.put("女",theResult_nv);

        //检测装配的结果
        System.out.println("检测装配的结果：");
        for (Map<String,Object> map:theResult_all.get("男")){
            System.out.println(map.toString());
        }
        System.out.println("女性读者订阅统计分布：");
        for (Map<String,Object> map:theResult_all.get("女")){
            System.out.println(map.toString());
        }

    }




    
}
