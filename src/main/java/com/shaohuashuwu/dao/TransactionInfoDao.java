package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface TransactionInfoDao {

    //查询是否订阅
    @Select("SELECT count(*) from transaction_info where consumer_id = #{consumer_id} and recipient_id = #{recipient_id} and transaction_type = 2")
    public int subscribeResult(TransactionInfo transactionInfo);





}
