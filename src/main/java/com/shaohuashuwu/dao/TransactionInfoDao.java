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

    //榜单查询
    /**
     ** 分析：
     *错 *     目的：根据不同日期，获取前100的不同类型排行信息，是要查询work_info信息
     * 错 *    条件：work_info表中work_main_label
     *错 *        transaction_info表中transaction_type，recipient_id
     *错 *      分析：1.先确定类型
     *错 *           2.进行时间判断，筛选出符合时间的人----------------------第一次错误，2,3顺序错误
     *错 *           3.在分组确定被投票人----------------------
     *错 *
     *错 *           4.进行主类型分类判定，判断出符合条件的主类型分类
     *错 *           5.统计大小排序获得work_id
     *           6.最后再获取work_info
     *
     *      实现：1.先设置查询条件，transaction_type的不同值时输出不同的排行
     *              select * from transaction_info where transaction_type = #{transaction_type}
     *           2.分组查询，GROUP BY ，recipient_id被投票者进行分组
     *           select * from transaction_info where transaction_type = #{transaction_type} GROUP BY recipient_id;
     *           3.拼接语句，拼接时间条件，初步方法，利用like进行模糊条件查询的判断
     *           <script>
     *               select * from transaction_info where 1=1 transaction_type
     *              <if test='transaction_time != null and transaction_time != \" \" '>
     *                  and transaction_time like '%${transaction_time}%'
     *              </if>
     *           </script>
     *            4.对work_info的进行主类型分类判定，判断出符合条件的主类型分类
     *            <script>
     *                select * from work_info w1 where w1.work_id =
     *                    (select t1.recipient_id from transaction_info t1 where 1=1 t1.transaction_type = #{transaction_type} GROUP BY t1.recipient_id
     *                       <if test='t1.transaction_time != null and t1.transaction_time != \" \" '>
     *                          and t1.transaction_time like '%${transaction_time}%'
     *                       </if>
     *                      )
     *                      <if test='w1.work_main_label != null and w1.work_main_label != \" \" '>
     *                         and w1.work_main_label = #{work_main_label}
     *                      </if>
     *
     *            </script>
     *
     *            5.依据最终的work_id，即recipient_id统计的transaction_quantity的值，sum累计和，并用 ORDER BY 排序输出
     *            <script>
     *
     *                select * from work_info w1 where w1.work_id =
     *                    (select t1.recipient_id from transaction_info t1 where 1=1 t1.transaction_type = #{transaction_type} GROUP BY t1.recipient_id
     *                       <if test='t1.transaction_time != null and t1.transaction_time != \" \" '>
     *                          and t1.transaction_time like '%${transaction_time}%'
     *                       </if>
     *错 *                      )
     *错 *                      <if test='w1.work_main_label != null and w1.work_main_label != \" \" '>
     *错 *                         and w1.work_main_label = #{work_main_label}
     *错 *                      </if>
     *错 *
     *错 *            </script>
     *
     *
     *
     *
     *
     *            1.依据id从work_id查询
     *            select w1.work_id from work_info w1 where w1.work_main_label = #{work_main_label}
     *            2、
                 select recipient_id,sum(transaction_quantity) sumnum,w2.work_main_label,w2.work_serial_state,w2.work_name from transaction_info,work_info w2
                 where 1=1
                 and transaction_type = #{transaction_type}
                 and recipient_id = w2.work_id
     *            <if test='transaction_time != null and transaction_time != \" \" '>
     *                and transaction_time like '%${transaction_time}%'
     *            </if>
     *            <if test='w1.work_main_label != null and w1.work_main_label != \" \" >
     *                and recipient_id = (select w1.work_id from work_info w1 where w1.work_main_label = #{work_main_label})
     *            </if>
     *            ORDER BY sumnum
     */

    @Select({
                "<script>",
                    "SELECT SUM(transaction_quantity) sumnum,w2.work_id work_id,w2.work_main_label work_main_label,w2.work_serial_state work_serial_state,w2.work_name work_name,w2.work_cover work_cover FROM transaction_info t1,works_info w2 WHERE 1=1 \n" +
                    "<if test=' work_main_label != null and work_main_label != \" \" '>",
                            " and recipient_id IN (SELECT w1.work_id FROM works_info w1 WHERE w1.work_main_label = #{work_main_label}) " ,
                    "</if>",
                    "<if  test='transaction_time != null and transaction_time != \" \" '>",
                        " and transaction_time like '%${transaction_time}%' ",
                    "</if>",
                    "AND transaction_type = #{transaction_type}\n" +
                    "AND w2.work_id = recipient_id\n" +
                    "GROUP BY recipient_id\n" +
                    "ORDER BY sumnum DESC",
                "</script>"
    })
    public List<RankingInfoVo> selectListInfo(RankingInputInfoVo rankingInputInfoVo);





}
