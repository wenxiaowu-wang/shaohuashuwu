package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RankingInfoVoDao {

    //榜单推荐票信息系
    //功能点：查看榜单信息
    @Select({
            "<script>",
            " SELECT r1.work_id,SUM(r1.transaction_quantity) sumnum,w1.`work_cover` work_cover,w1.`work_name` work_name,w1.`work_main_label` work_main_label " +
                    " ,w1.`work_vice_label` work_vice_label,w1.work_serial_state work_serial_state,w1.`work_introduct` work_introduct,w1.`work_other_word` work_other_word " +
                    " FROM recommendRanking_info r1,works_info w1 " +
                    " WHERE 1=1 " +
                    "AND w1.work_id = r1.work_id " +
                    "AND w1.work_serial_state != 3"+
                    "<if  test='transaction_time != null and transaction_time != \" \" '>",
                    " and r1.transaction_time like '%${transaction_time}%' ",
                    "</if>",
                    "<if  test='work_main_label != null and work_main_label != \" \" '>",
                    " and w1.work_main_label = #{work_main_label} ",
                    "</if>",
                    " GROUP BY r1.work_id " +
                    " ORDER BY sumnum DESC ",
            "</script>"
    })
    public List<RankingInfoVo> selectrecommendRankingInfo(RankingInputInfoVo rankingInputInfoVo);


    //榜单打赏信息系
    //功能点：查看榜单信息
    @Select({
            "<script>",
            " SELECT r1.work_id,SUM(r1.transaction_quantity) sumnum,w1.`work_cover` work_cover,w1.`work_name` work_name,w1.`work_main_label` work_main_label " +
                    " ,w1.`work_vice_label` work_vice_label,w1.work_serial_state work_serial_state,w1.`work_introduct` work_introduct,w1.`work_other_word` work_other_word " +
                    " FROM rewardRanking_info r1,works_info w1 " +
                    " WHERE 1=1 " +
                    "AND w1.work_id = r1.work_id " +
                    "AND w1.work_serial_state != 3"+
            "<if  test='transaction_time != null and transaction_time != \" \" '>",
            " and r1.transaction_time like '%${transaction_time}%' ",
            "</if>",
            "<if  test='work_main_label != null and work_main_label != \" \" '>",
            " and w1.work_main_label = #{work_main_label} ",
            "</if>",
            " GROUP BY r1.work_id " +
             " ORDER BY sumnum DESC ",
            "</script>"
    })
    public List<RankingInfoVo> selectrewardRankingInfo(RankingInputInfoVo rankingInputInfoVo);


    //榜单订阅信息系
    //功能点：查看榜单信息
    @Select({
            "<script>",
            " SELECT r1.work_id,SUM(r1.transaction_quantity) sumnum,w1.`work_cover` work_cover,w1.`work_name` work_name,w1.`work_main_label` work_main_label " +
                    " ,w1.`work_vice_label` work_vice_label,w1.work_serial_state work_serial_state,w1.`work_introduct` work_introduct,w1.`work_other_word` work_other_word " +
                    " FROM subscribeRanking_info r1,works_info w1 " +
                    " WHERE 1=1 " +
                    "AND w1.work_id = r1.work_id " +
                    "AND w1.work_serial_state != 3"+
            "<if  test='transaction_time != null and transaction_time != \" \" '>",
            " and r1.transaction_time like '%${transaction_time}%' ",
            "</if>",
            "<if  test='work_main_label != null and work_main_label != \" \" '>",
            " and w1.work_main_label = #{work_main_label} ",
            "</if>",
            " GROUP BY r1.work_id " +
              " ORDER BY sumnum DESC ",
            "</script>"
    })
    public List<RankingInfoVo> selectsubscribeRankingInfo(RankingInputInfoVo rankingInputInfoVo);

    //收藏帮
    //功能点：榜单信息
    @Select({
            "<script>",
            "SELECT b1.work_id,COUNT(b1.user_id) sumnum ,w1.`work_cover` work_cover,w1.`work_name` work_name,w1.`work_main_label` work_main_label " +
                    " ,w1.`work_vice_label` work_vice_label,w1.work_serial_state work_serial_state,w1.`work_introduct` work_introduct,w1.`work_other_word` work_other_word " +
                    " FROM bookshelf_info b1,works_info w1 " +
                    " WHERE 1 = 1" +
            "<if  test='transaction_time != null and transaction_time != \" \" '>",
            " and  b1.collection_time like '%${transaction_time}%' ",
            "</if>",
            "<if  test='work_main_label != null and work_main_label != \" \" '>",
            " and w1.work_main_label = #{work_main_label} ",
            "</if>",
            " AND b1.`work_id` = w1.`work_id` " +
            " AND w1.work_serial_state != 3 " +
            " GROUP BY b1.work_id " +
            " ORDER BY sumnum DESC ",
            "</script>"
    })
    public List<RankingInfoVo> selectCollectionRankingInfo(RankingInputInfoVo rankingInputInfoVo);

}
