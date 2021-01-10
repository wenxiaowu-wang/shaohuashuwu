package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.vo.IsChapterHaveVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface TransactionInfoDao {


    //依据章节id查询章节信息
    //功能点：阅读小说界面获取章节信息
    @Select(" SELECT COUNT(*) FROM transaction_info " +
            " WHERE transaction_type =2 " +
            " AND consumer_id = #{user_id} " +
            " AND recipient_id = #{chapter_id} ")
    public int selectsubscribeResultBychapterAnduser_id(IsChapterHaveVo isChapterHaveVo);



    /*
    * 郝振威
    *
    *
    *
    * */
    @Update("UPDATE works_info  SET work_subscribe_num = work_subscribe_num + #{param1} WHERE work_id = #{param2}")
    public boolean updateWorkSubscribeNumByWorkId(int num,int work_id);

    /**
     * 阿斌
     */
    //增加一条交易记录
    @Insert("insert into transaction_info(transaction_id,consumer_id,recipient_id," +
            "transaction_type,transaction_mode,transaction_time,transaction_quantity," +
            "transaction_unit) values(#{transaction_id},#{consumer_id},#{recipient_id}," +
            "#{transaction_type},#{transaction_mode},#{transaction_time},#{transaction_quantity}," +
            "#{transaction_unit})")
    public int insertTransactionInfo(TransactionInfo transactionInfo);

    //根据获取最新的 insert or update 的某个表中的ID（在此作为transaction_id）获取该交易记录
    @Select("SELECT * FROM transaction_info WHERE transaction_id = (SELECT LAST_INSERT_ID())")
    public TransactionInfo selectWithdrawRecordByInsertNewId();

    //删除一条交易信息
    @Delete("delete from admin_info where transaction_id = #{transaction_id}")
    public int deleteTransactionInfo(int transaction_id);

    //查询对应消费记录信息
    @Select("select * from transaction_info where consumer_id = #{user_id}")
    @Results(id = "transactionInfo",value = {
            @Result(id = true,column = "transaction_id",property = "transaction_id"),
            @Result(column = "consumer_id",property = "consumer_id"),
            @Result(column = "recipient_id",property = "recipient_id"),
            @Result(column = "transaction_type",property = "transaction_type"),
            @Result(column = "transaction_mode",property = "transaction_mode"),
            @Result(column = "transaction_time",property = "transaction_time"),
            @Result(column = "transaction_quantity",property = "transaction_quantity"),
            @Result(column = "transaction_unit",property = "transaction_unit"),
    })
    public List<TransactionInfo> selectConsumptionInfoByUserId(int user_id);//查询对应消费记录信息

    //查询对应收入记录信息，应更改，接受者的ID为章节ID
    @Select("SELECT DISTINCT transaction_info.* FROM transaction_info,chapter_post_info,chapter_info WHERE transaction_info.recipient_id = chapter_info.chapter_id AND chapter_info.chapter_id = chapter_post_info.chapter_id AND chapter_post_info.user_id = #{user_id}")
    @ResultMap("transactionInfo")
    public List<TransactionInfo> selectIncomeInfoByUserId(int user_id);

    //查询对应提现记录信息
    @Select("select * from transaction_info where recipient_id = #{user_id} and transaction_type = 4")
    @ResultMap("transactionInfo")
    public List<TransactionInfo> selectWithdrawInfoByUserId(int user_id);

    //查询该作品被订阅记录信息
    @Select("select * from transaction_info where recipient_id = #{user_id} and transaction_type = 2")
    @ResultMap("transactionInfo")
    public List<TransactionInfo> selectSubscribeInfoByWorkId(int work_id);

    //根据用户ID统计该用户的金币收入数量
    @Select("select IFNULL(sum(transaction_quantity),0)from transaction_info where recipient_id = #{user_id}")
    public int selectAllIncomeGoldNum(int user_id);

    //根据交易id更新一条对应交易记录信息
    @Update("update transaction_info set consumer_id = #{consumer_id},recipient_id = #{recipient_id}," +
            "transaction_type = #{transaction_type},transaction_mode = #{transaction_mode}," +
            "transaction_time = #{transaction_time},transaction_quantity = #{transaction_quantity}," +
            "transaction_unit = #{transaction_unit} where transaction_id = #{transaction_id}")
    public int updateTransactionInfo(TransactionInfo transactionInfo);

    //测试选择某一时间段的信息是否成功(测试使用)
    @Select("SELECT * FROM transaction_info WHERE transaction_time >=  #{param1} AND transaction_time <=  #{param2}")
    @ResultMap("transactionInfo")
    public List<TransactionInfo> selectSomeTimeOfTransactionInfo(String time_1,String time_2);

    //1.统计某一时间段的订阅记录的条数（选择时间段【精确到日%Y-%m-%d】、选择性别、选择作品ID）
    @Select("SELECT DATE_FORMAT(subscription_time,'%Y-%m-%d')date_day,IFNULL(SUM(transaction_quantity),0)subscription_quantity FROM subscription_view WHERE gender = #{param4} AND subscription_time >= #{param2} AND subscription_time <= #{param3} AND work_id = #{param1} GROUP BY DATE_FORMAT(subscription_time,'%Y-%m-%d')")
    public List<Map<String,Object>> selectSubscriptionStatisticsData(int work_id, String start_time, String end_time, String gender);

    //测试返回map集合数据
    @Select("SELECT user_id,user_name FROM user_info WHERE user_name like #{param1}")
    public List<Map<String,Object>> selectTestResultMapData(String work_name);

    //1.01根据作品ID获取总订阅数量
    @Select("SELECT IFNULL(SUM(transaction_quantity),0) AS all_subscription_num FROM subscription_view WHERE work_id = #{work_id}")
    public int selectAllSubscriptionNumByWorkId(int work_id);

    //1.02根据作品ID获取某天的订阅量
    @Select("SELECT IFNULL(SUM(transaction_quantity),0) AS yesterday_subscription_num FROM subscription_view WHERE work_id = #{param1} and DATE_FORMAT(subscription_time,'%Y-%m-%d') = #{param2}")
    public int selectOneDaySubscriptionNumByWorkID(int work_id,String one_day);

    //1.04根据作品ID获取章节最高订阅量
    @Select("SELECT IFNULL(MAX(T.chapter_subscription_num),0) AS chapter_max_subscription_num FROM (SELECT IFNULL(SUM(transaction_quantity),0) AS chapter_subscription_num, chapter_id FROM subscription_view WHERE work_id = #{param1} GROUP BY chapter_id) AS T")
    public int selectChapterMaxSubscriptionNumByWorkId(int work_id);
}
