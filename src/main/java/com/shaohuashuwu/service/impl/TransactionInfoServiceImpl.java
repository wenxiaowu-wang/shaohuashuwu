package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.vo.TransactionInfoVo;
import com.shaohuashuwu.service.TransactionInfoService;
import com.shaohuashuwu.utils.DateCalculation;
import com.shaohuashuwu.utils.StatisticalHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private TransactionInfoDao transactionInfoDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private WorksInfoDao worksInfoDao;

    @Autowired
    private ChapterPostInfoDao chapterPostInfoDao;

    @Autowired
    private ChapterInfoDao chapterInfoDao;

    @Autowired
    private ThirdPartyInfoDao thirdPartyInfoDao;

    //单个订阅章节（事务回滚）
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public boolean subscribeAChapterGUN(int userId,int subBeanNum,int chapterId,int addBeanNum,int work_id) {

        boolean Result = false;

        //通过章节ID查询到作者的ID
        int  authorID = userInfoDao.selectAuthorIDByChapterID(chapterId);

        //扣除用户金豆数
        boolean subUserBeanNum = false;
        if (userInfoDao.updateGoldBeanNumByUserId(userId, subBeanNum) != (0)) {
            subUserBeanNum = true;
        }

        //增加作者金豆数
        boolean addAuthorBeanNum = false;
        if (userInfoDao.updateGoldBeanNumByUserId(authorID, addBeanNum) != (0)) {
            addAuthorBeanNum = true;
        }
        //增加作品表的订阅数
        boolean addWorkSubscribeNum = false;
        if(transactionInfoDao.updateWorkSubscribeNumByWorkId(1,work_id)){
            addWorkSubscribeNum = true;
        }

        //将订阅记录存到交易记录表
        TransactionInfo transactionInfo = new TransactionInfo();
        Date date = new Date();
        System.out.println("date is "+date.toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("timestamp is "+timestamp.toString());
        //交易记录流水ID不用设置，在数据库中自增补齐
        transactionInfo.setConsumer_id(userId);//消费者ID
        transactionInfo.setRecipent_id(chapterId);//接受者ID（作品ID）(更改为章节ID)
        transactionInfo.setTransaction_type(2);//1表示交易类型是打赏
        transactionInfo.setTransaction_mode(11);//交易方式(除了0和1外，其它的不进行解析)
        transactionInfo.setTransaction_time(timestamp);//交易时间
        transactionInfo.setTransaction_quantity(addBeanNum);//交易数量
        transactionInfo.setTransaction_unit("个金豆");
        boolean saveResult = false;
        if(transactionInfoDao.insertTransactionInfo(transactionInfo)!=0){
            saveResult = true;
        }


        if(subUserBeanNum&&addAuthorBeanNum&&saveResult&&addWorkSubscribeNum){
            Result = true;
        }

        return Result;
    }

    /**
     * 阿斌
     */
    //充值金豆（记录充值信息并添加对应用户金豆数量）
    @Override
    public boolean topUpsGoldBean(TransactionInfo topUpsInfo) {
        boolean addResult = false;

        if (transactionInfoDao.insertTransactionInfo(topUpsInfo) != 0){
            System.out.println("log:充值预备 +"+topUpsInfo.getTransaction_quantity() * 100+"金豆");

            if (userInfoDao.updateGoldBeanNumByUserId(topUpsInfo.getConsumer_id(),topUpsInfo.getTransaction_quantity() * 100) !=0){
                addResult = true;
                System.out.println("log:充值成功 +"+topUpsInfo.getTransaction_quantity() * 100+"金豆");
            }else{
                System.out.println("log:充值失败 ");

            }


        }
        return addResult;
    }

    /**
     * 已完成
     * @param tipInfo
     * @return
     */
    //打赏作品
    @Override
    public boolean tipWork(TransactionInfo tipInfo,int work_id) {
        boolean tipResult = false;
        //根据被打赏作品ID获取对应作品作者ID
        int author_id = worksInfoDao.selectAuthorIdByWorkId(work_id);
        if (/*扣除消费用户金豆数，增加目标用户的金币数*/
                userInfoDao.updateGoldBeanNumByUserId(tipInfo.getConsumer_id(),tipInfo.getTransaction_quantity() * (-1)) != 0
                        && userInfoDao.updateGoldCoinNumByUserId(author_id,tipInfo.getTransaction_quantity()/10) != 0
                        && worksInfoDao.updateWorkTipNumByWorkId(work_id,tipInfo.getTransaction_quantity()) != 0
                        && transactionInfoDao.insertTransactionInfo(tipInfo)!=(0)){
            tipResult = true;
        }
//        System.out.println("log: 打赏成功 ["+tipInfo.getConsumer_id()+"]消费了 ["+tipInfo.getTransaction_quantity()+"]金豆|***|["+author_id+"]被打赏了["+tipInfo.getTransaction_quantity()/10+"]金币");
        return tipResult;
    }

    /**
     * 投票作品
     * @param voteInfo
     * @return
     */
    //投票作品
    @Override
    public boolean voteWork(TransactionInfo voteInfo,int work_id) {
        boolean voteResult = false;
        if (/*根据消费者ID扣除对应用户推荐票数，根据作品ID增加目标图书的推荐票数*/
                userInfoDao.updateTicketNumByUserId(voteInfo.getConsumer_id(),voteInfo.getTransaction_quantity() * (-1)) != 0
                        && worksInfoDao.updateWorkVoteNumByWorkId(work_id,voteInfo.getTransaction_quantity()) != 0
                        && transactionInfoDao.insertTransactionInfo(voteInfo)!=(0)){
            voteResult = true;
        }
        return voteResult;
    }

    /**
     * 获取该用户所有的消费记录（所有的记录）
     * @param user_id 消费者ID
     * @return 交易记录值对象纪集合
     */
    //获取该用户所有消费记录
    @Override
    public List<TransactionInfoVo> getAllConsumptionTransactionInfo(int user_id) {
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        List<TransactionInfo> getDao = new ArrayList<TransactionInfo>();
        getDao = transactionInfoDao.selectConsumptionInfoByUserId(user_id);
        if (getDao.size() != 0){
            for (TransactionInfo transactionInfo : getDao) {
                StringBuilder name = new StringBuilder("");
                switch (transactionInfo.getTransaction_type()) {
                    case 0:
                    case 4: {
                        //接收人名字获取：充值接收人为韶华书屋平台，打赏、订阅、投票的接收人为作者以及作品名字，提现的接收人为用户
                        name.append(userInfoDao.selectUserNameById(transactionInfo.getRecipent_id()));
                        break;
                    }
                    case 1:
                    case 2:
                    case 3: {
                        //获取接受者ID对应的作品名和章节标题，将其进行字符串拼接
                        name.append("《").append(chapterPostInfoDao.selectWorkNameByChapterId(transactionInfo.getRecipent_id())).append("》");
                        name.append(chapterInfoDao.selectChapterTitleByChapterId(transactionInfo.getRecipent_id()));
                        break;
                    }
                    default:
                        name.append("未知");
                        break;
                }
                TransactionInfoVo transactionInfoVo = new TransactionInfoVo();
                transactionInfoVo.setTransaction_id(transactionInfo.getTransaction_id());
                transactionInfoVo.setConsumer_id(transactionInfo.getConsumer_id());
                transactionInfoVo.setRecipient_id(transactionInfo.getRecipent_id());
                transactionInfoVo.setRecipient_name(name.toString());
                transactionInfoVo.setTransaction_type(transactionInfo.analysisType());
                transactionInfoVo.setTransaction_mode(transactionInfo.analysisMode());
                transactionInfoVo.setTransaction_time(transactionInfo.analysisTime());
                transactionInfoVo.setTransaction_quantity(transactionInfo.getTransaction_quantity());
                transactionInfoVo.setTransaction_unit(transactionInfo.getTransaction_unit());
                getResult.add(transactionInfoVo);
            }
        }
        return getResult;
    }

    //获取该用户所有金币收入数量
    @Override
    public int getAllIncomeGoldMount(int user_id) {
        int goldNum = 0;
        goldNum = transactionInfoDao.selectAllIncomeGoldNum(user_id);
        return goldNum;
    }

    //获取该用户所有收入记录
    @Override
    public List<TransactionInfoVo> getAllIncomeTransactionInfo(int user_id) {
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        List<TransactionInfo> getDao = transactionInfoDao.selectIncomeInfoByUserId(user_id);
        //遍历从dao层获取到的数据，处理后封装到transactionInfoVo对象中
        if (getDao.size() != 0){
            for (TransactionInfo transactionInfo : getDao) {
                StringBuilder work_name = new StringBuilder("");
                StringBuilder chapter_name = new StringBuilder("");
                StringBuilder consumer_name = new StringBuilder("");
                int type = transactionInfo.getTransaction_type();
                //如果是0(充值)，暂不考虑，先行跳过
                if (type == 0) {
                    continue;
                }
                switch (transactionInfo.getTransaction_type()) {
                    case 0: {
                        consumer_name.append("韶华书屋平台");
                        break;
                    }
                    case 4: {
                        consumer_name.append("韶华书屋平台");
                        System.out.println("Service层有提现记录");
                        //此处的chapter_name代表的是第三方的账号
                        chapter_name.append(thirdPartyInfoDao.selectThirdNumberById(transactionInfo.getTransaction_id()));
                        break;
                    }
                    case 1:
                    case 2:
                    case 3: {
                        //获取接受者ID对应的作品名和章节标题，将其进行字符串拼接(打赏、订阅、投票接受者都是对应章节ID)
                        work_name.append("《").append(chapterPostInfoDao.selectWorkNameByChapterId(transactionInfo.getRecipent_id())).append("》");
                        chapter_name.append(chapterInfoDao.selectChapterTitleByChapterId(transactionInfo.getRecipent_id()));
                        consumer_name.append(userInfoDao.selectUserNameById(transactionInfo.getConsumer_id()));
                        break;
                    }
                    default:
                        work_name.append("未知");
                        chapter_name.append("未知");
                        break;
                }
                TransactionInfoVo transactionInfoVo = new TransactionInfoVo();
                transactionInfoVo.setTransaction_id(transactionInfo.getTransaction_id());
                transactionInfoVo.setConsumer_id(transactionInfo.getConsumer_id());
                transactionInfoVo.setConsumer_name(consumer_name.toString());
                transactionInfoVo.setRecipient_id(transactionInfo.getRecipent_id());//还是对应的章节ID（打赏、订阅、投票）
                transactionInfoVo.setRecipient_name(work_name.toString());
                transactionInfoVo.setRecipient_name_other(chapter_name.toString());
                transactionInfoVo.setTransaction_type(transactionInfo.analysisType());
                transactionInfoVo.setTransaction_mode(transactionInfo.analysisMode());
                transactionInfoVo.setTransaction_time(transactionInfo.analysisTime());
                transactionInfoVo.setTransaction_quantity(transactionInfo.getTransaction_quantity());
                transactionInfoVo.setTransaction_unit(transactionInfo.getTransaction_unit());
                getResult.add(transactionInfoVo);
            }
        }
        return getResult;
    }

    //获取该用户所有提现记录
    @Override
    public List<TransactionInfoVo> getTransactionOfWithdraw(int user_id) {
        List<TransactionInfo> getDao = transactionInfoDao.selectWithdrawInfoByUserId(user_id);
        List<TransactionInfoVo> getResult = new ArrayList<TransactionInfoVo>();
        String third_party_number = "";
        if (getDao.size()!=0){
            for (int i=0;i<getDao.size();i++){
                //根据获取到的transaction_info的ID获取提现的第三方的账号
                third_party_number = "";
                third_party_number += thirdPartyInfoDao.selectThirdNumberById(getDao.get(i).getTransaction_id());
                TransactionInfoVo transactionInfoVo = new TransactionInfoVo();
                transactionInfoVo.setTransaction_id(getDao.get(i).getTransaction_id());
                transactionInfoVo.setConsumer_id(getDao.get(i).getConsumer_id());
                transactionInfoVo.setConsumer_name("韶华书屋平台");
                transactionInfoVo.setRecipient_id(getDao.get(i).getRecipent_id());//还是对应的章节ID（打赏、订阅、投票）
                transactionInfoVo.setRecipient_name(third_party_number);
                transactionInfoVo.setRecipient_name_other("该用户");
                transactionInfoVo.setTransaction_type(getDao.get(i).analysisType());
                transactionInfoVo.setTransaction_mode(getDao.get(i).analysisMode());
                transactionInfoVo.setTransaction_time(getDao.get(i).analysisTime());
                transactionInfoVo.setTransaction_quantity(getDao.get(i).getTransaction_quantity());
                transactionInfoVo.setTransaction_unit(getDao.get(i).getTransaction_unit());
                getResult.add(transactionInfoVo);
            }
        }
        return getResult;
    }

    //提现金币（等待第三方转账成功，保存提现记录）
    @Override
    public TransactionInfo withdrawMoney(TransactionInfo withdrawInfo,String third_party_number) {
        TransactionInfo withdrawResult = new TransactionInfo();
        if (/*等待第三方转账成功,扣除对应用户金币数量,保存第三方账号*/
                userInfoDao.updateGoldCoinNumByUserId(withdrawInfo.getRecipent_id(),withdrawInfo.getTransaction_quantity()*(-10)) != (0)
                        &&transactionInfoDao.insertTransactionInfo(withdrawInfo)!=(0)){
            withdrawResult = transactionInfoDao.selectWithdrawRecordByInsertNewId();
            if (thirdPartyInfoDao.insertOneRecord(withdrawResult.getTransaction_id(),third_party_number) != 0){
                System.out.println("第三方账号保存成功");
            }
        }
        return withdrawResult;
    }

    //获取该作品的被订阅的所有记录
    @Override
    public List<TransactionInfo> getSubscribeInfo(int work_id) {
        return transactionInfoDao.selectSubscribeInfoByWorkId(work_id);
    }

    //统计近一个月的某作品的订阅量分布
    @Override
    public Map<String,List<Map<String,Object>>> getSubscriptionStatisticsData(int work_id) {
        //初始化返回值
        Map<String,List<Map<String,Object>>> theResult_all = new HashMap<String,List<Map<String,Object>>>();

        //初始化日期计算工具类
        String dateFormat = "yyyy-MM-dd";
        DateCalculation dateCalculation = new DateCalculation(dateFormat);

        //计算当前时间 以及 前30天的日期
        int dayNum = -30;
        String day = dateCalculation.getCertainTime(dateCalculation.getRightNow(),dayNum);
        System.out.println(dateCalculation.getRightNow()+" 的前"+dayNum*(-1)+"天为："+day);

        //获取作品发布时间
        Timestamp timestamp = worksInfoDao.selectWorkCreateTimeByWorkId(work_id);
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
        List<Map<String,Object>> getDao_nan = transactionInfoDao.selectSubscriptionStatisticsData(work_id,startTime,dateCalculation.getRightNow(),"男");
        List<Map<String,Object>> getDao_nv = transactionInfoDao.selectSubscriptionStatisticsData(work_id,startTime,dateCalculation.getRightNow(),"女");

        //使用自定义的统计帮助工具类的装配方法，将每一天的数据都装起来(男，女)
        StatisticalHelp statisticalHelp = new StatisticalHelp();
        List<Map<String,Object>> theResult_nan = statisticalHelp.assemblySubscriptionStatistics(days,getDao_nan);
        List<Map<String,Object>> theResult_nv = statisticalHelp.assemblySubscriptionStatistics(days,getDao_nv);

        //装配最终返回结果
        theResult_all.put("男",theResult_nan);
        theResult_all.put("女",theResult_nv);
        return theResult_all;
    }

    //获取该作品订阅的其它统计数据
    @Override
    public Map<String, Object> getOtherSubscriptionStatisticsData(int work_id) {

        //*获取该作品总订阅量
        int all_subscription_num = transactionInfoDao.selectAllSubscriptionNumByWorkId(work_id);
        //*获取该作品昨日新增订阅量
        int yesterday_subscription_num = 0;
        //初始化日期计算工具类
        String dateFormat = "yyyy-MM-dd";
        DateCalculation dateCalculation = new DateCalculation(dateFormat);

        //计算当前时间 以及 前1天的日期
        int dayNum = -1;
        String day = dateCalculation.getCertainTime(dateCalculation.getRightNow(),dayNum);
        System.out.println(dateCalculation.getRightNow()+" 的前"+dayNum*(-1)+"天为："+day);

        //获取作品发布时间
        Timestamp timestamp = worksInfoDao.selectWorkCreateTimeByWorkId(work_id);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String workCreateTime = sdf.format(timestamp);
        System.out.println("转化后的作品发布时间："+workCreateTime);

        //比较作品发布时间是否早于一月前时间，根据比较结果进行赋值
        if (!dateCalculation.compareTime(workCreateTime,day)){
            yesterday_subscription_num = transactionInfoDao.selectOneDaySubscriptionNumByWorkID(work_id,day);
        }

        //*获取该作品章节平均订阅量
        int chapter_num = chapterPostInfoDao.selectChapterNumByWorkId(work_id);
        double chapter_avg_subscription_num = (chapter_num == 0 ? 0:((double)all_subscription_num/chapter_num));
        //*获取该作品章节最高订阅量
        int chapter_max_subscription_num = transactionInfoDao.selectChapterMaxSubscriptionNumByWorkId(work_id);

        //装配订阅量信息到结果集合
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("all_subscription_num",all_subscription_num);
        map.put("yesterday_subscription_num",yesterday_subscription_num);
        map.put("chapter_avg_subscription_num",chapter_avg_subscription_num);
        map.put("chapter_max_subscription_num",chapter_max_subscription_num);
        return map;
    }
}
