package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RankingInfoVoDao {

    //榜单查询
    /**
     * 分析
     *      1.查询数量，work_id,work_mian_label_workserial_sate,work_name,work_cover
     *      2.查询表为work_info作品表，transaction_info投票信息表
     *      3.判断主类型是否存在如果存在，依据主类型获取存在的work_id,依据work_id获取存在的章节id,依据章节id获取每个章节的投票信息
     *      4.如果投票时间段存在，就获取去该段时间内投票信息
     *      5.交易类型分别为1打赏，2订阅，3推荐票
     *      6.
     *      w2.work_id =  (select c1.work_id from chapter_post_info c1 where c1.chapter_id = t1.recipient_id)
     * */
    @Select({
            "<script>",
            "SELECT SUM(transaction_quantity) sumnum,w2.work_id work_id,w2.work_main_label work_main_label,w2.work_serial_state work_serial_state,w2.work_name work_name,w2.work_cover work_cover FROM transaction_info t1,works_info w2 WHERE 1=1 \n" +
             "<if test=' work_main_label != null and work_main_label != \" \" '>",
            " and t1.recipient_id IN (select c1.chapter_id from chapter_post_info c1 where c1.work_id in (SELECT w1.work_id FROM works_info w1 WHERE w1.work_main_label = #{work_main_label})) " ,
            "</if>",
            "<if  test='transaction_time != null and transaction_time != \" \" '>",
            " and t1.transaction_time like '%${transaction_time}%' ",
            "</if>",
            "AND t1.transaction_type = #{transaction_type}\n" +
            "AND w2.work_id = t1.recipient_id\n" +
            "GROUP BY recipient_id\n" +
            "ORDER BY sumnum DESC",
            "</script>"
    })
    public List<RankingInfoVo> selectListInfo(RankingInputInfoVo rankingInputInfoVo);


}
