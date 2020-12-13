package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CatalogInfoVoDao {

    //查询章节目录
    //功能点：作品详情时获取目录新信息，
    @Select(" SELECT c1.chapter_id chapter_id,c1.chapter_title chapter_title,c1.chapter_charge chapter_charge," +
            " (SELECT COUNT(*) FROM transaction_info t1 WHERE t1.recipient_id = c1.chapter_id AND t1.consumer_id = #{user_id} AND t1.transaction_type = 2) subscribe " +
            " FROM chapter_info c1 " +
            " WHERE c1.chapter_id IN " +
            "   (SELECT cp1.chapter_id  " +
            "   FROM chapter_post_info cp1 " +
            "   WHERE cp1.work_id = #{work_id} " +
            " ORDER BY cp1.chapter_id) " +
            " AND c1.chapter_state != 3 " +
            " AND c1.chapter_state != 4 " +
            " and c1.chapter_id != 1 ")
    public List<CatalogInfoVo> selectchaptercatalogBywork_id(WorksInfo worksInfo);


    //依据用户id和作品id，获取章节目录信息，包括被下架章节
    // 功能点：添加作品获取章节信息
    @Select(" SELECT c1.chapter_id chapter_id,c1.chapter_title chapter_title,c1.chapter_charge chapter_charge,c1.chapter_state subscribe " +
            " FROM chapter_info c1 " +
            " WHERE c1.chapter_id IN " +
            "   (SELECT cp1.chapter_id  " +
            "   FROM chapter_post_info cp1 " +
            "   WHERE cp1.work_id = #{work_id} " +
            " ORDER BY cp1.chapter_id) " +
            " and c1.chapter_id != 1 ")
    List<CatalogInfoVo> selectchaptercatalogBywork_id2(int work_id);
}
