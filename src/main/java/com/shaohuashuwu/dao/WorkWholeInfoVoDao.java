package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkWholeInfoVoDao {

    //依据作品状态排序查询作品信息
    @Select("SELECT w1.work_id work_id,w1.work_cover work_cover,w1.work_name work_name,w1.user_id user_id,w1.work_main_label work_main_label,w1.work_introduct work_introduct,u1.user_name user_name " +
            " FROM works_info w1 , user_info u1 " +
            " WHERE w1.work_serial_state = #{work_serial_state} " +
            " AND w1.user_id =u1.user_id " +
            " ORDER BY  w1.work_vote_num " +
            " DESC limit 6 ")
    public List<WorkWholeInfoVo> selectWorkWholeInfoVoDaoBywork_state(int work_serial_state);

    //获取最新作品，并且已经发布章节
    @Select("SELECT w1.work_id work_id,w1.work_cover work_cover,w1.work_name work_name,w1.user_id user_id,w1.work_main_label work_main_label,w1.work_introduct work_introduct,u1.user_name user_name " +
            " FROM works_info w1 , user_info u1 " +
            " WHERE w1.work_serial_state = #{work_serial_state}  " +
            " AND w1.user_id = u1.user_id  " +
            " AND w1.work_id IN (SELECT c1.work_id FROM chapter_post_info c1 GROUP BY c1.work_id) " +
            " and w1.work_serial_state != 3" +
            " ORDER BY  w1.work_id  " +
            " DESC LIMIT 6")
    public List<WorkWholeInfoVo> selectNewWorkWholeInfoVoDao(int work_serial_state);

    //通过作品名称查询作品全部信息，并且作品状态不能为3
    //功能点：关键字搜索搜索内容，
    @Select(
            {"<script>",
            " SELECT w2.work_id work_id,w2.work_cover work_cover,w2.work_name work_name," +
                    " u1.user_id user_id,w2.work_main_label work_main_label,w2.work_serial_state work_serial_state," +
                    " w2.work_introduct work_introduct,w2.work_vote_num work_vote_num,u1.user_name user_name," +
                    " c1.chapter_id chapter_id,c1.chapter_title chapter_title,c1.chapter_time chapter_time " +
                    " FROM works_info w2,chapter_info c1,user_info u1 " +
                    " WHERE u1.user_id = " +
                    "   (SELECT cp2.user_id " +
                    "       FROM chapter_post_info cp2 " +
                    "       WHERE cp2.chapter_id = " +
                    "           (SELECT MAX(cp1.chapter_id) " +
                    "               FROM chapter_post_info cp1 " +
                    "                   WHERE cp1.work_id =  " +
                    "                   (SELECT w1.work_id FROM works_info w1 WHERE w1.work_name = #{work_name}))) " +
                    " AND c1.chapter_id =  " +
                    "   (SELECT cp2.chapter_id " +
                    "       FROM chapter_post_info cp2 " +
                    "       WHERE cp2.chapter_id = " +
                    "           (SELECT MAX(cp1.chapter_id) " +
                    "               FROM chapter_post_info cp1 " +
                    "               WHERE cp1.work_id =  " +
                    "               (SELECT w1.work_id FROM works_info w1 WHERE w1.work_name = #{work_name}))) " +
                    " AND w2.work_id = " +
                    "   (SELECT cp2.work_id " +
                    "       FROM chapter_post_info cp2 " +
                    "       WHERE cp2.chapter_id = " +
                    "           (SELECT MAX(cp1.chapter_id) " +
                    "               FROM chapter_post_info cp1 " +
                    "               WHERE cp1.work_id = " +
                    "                   (SELECT w1.work_id FROM works_info w1 WHERE w1.work_name = #{work_name})))" +
                    " and w2.work_serial_state != 3 ",
                    " <if test='work_main_label != null and work_main_label != \" \" '>",
                    " and work_main_label = #{work_main_label} ",
                    " </if>",
                    " <if test='work_vice_label != null and work_vice_label != \" \" '>",
                    " and work_vice_label = #{work_vice_label} ",
                    " </if>",
                    " <if test='work_serial_state != null and work_serial_state != \" \" '>",
                    " and work_serial_state = #{work_serial_state} ",
                    " </if>",

            "</script>"
    }
    )
    public WorkWholeInfoVo selectWorkWholeInfoVobywork_name(WorksInfo worksInfo);


    //通过作者名称查询作品全部信息，并且作品状态不能为3
    @Select(
            {"<script>",
                    " SELECT w2.work_id work_id,w2.work_cover work_cover,w2.work_name work_name," +
                            " u1.user_id user_id,w2.work_main_label work_main_label,w2.work_serial_state work_serial_state," +
                            " w2.work_introduct work_introduct,w2.work_vote_num work_vote_num,u1.user_name user_name," +
                            " c1.chapter_id chapter_id,c1.chapter_title chapter_title,c1.chapter_time chapter_time " +
                            " FROM works_info w2,chapter_info c1,user_info u1 " +
                            " WHERE 1=1 and w2.work_serial_state != 3" +
                            " and u1.user_id = " +
                            "   (SELECT cp2.user_id " +
                            "       FROM chapter_post_info cp2 " +
                            "       WHERE cp2.chapter_id = " +
                            "           (SELECT MAX(cp1.chapter_id) " +
                            "               FROM chapter_post_info cp1 " +
                            "                   WHERE cp1.work_id =  " +
                            "                   (SELECT w1.work_id FROM works_info w1 WHERE w1.work_name = #{work_name}))) " +
                            " AND c1.chapter_id =  " +
                            "   (SELECT cp2.chapter_id " +
                            "       FROM chapter_post_info cp2 " +
                            "       WHERE cp2.chapter_id = " +
                            "           (SELECT MAX(cp1.chapter_id) " +
                            "               FROM chapter_post_info cp1 " +
                            "               WHERE cp1.work_id =  " +
                            "               (SELECT w1.work_id FROM works_info w1 WHERE w1.work_name = #{work_name}))) " +
                            " AND w2.work_id = " +
                            "   (SELECT cp2.work_id " +
                            "       FROM chapter_post_info cp2 " +
                            "       WHERE cp2.chapter_id = " +
                            "           (SELECT MAX(cp1.chapter_id) " +
                            "               FROM chapter_post_info cp1 " +
                            "               WHERE cp1.work_id = " +
                            "                   (SELECT w1.work_id FROM works_info w1 WHERE w1.work_name = #{work_name})))" +
                            " and w2.work_serial_state != 3 ",
                    " <if test='work_main_label != null and work_main_label != \" \" '>",
                    " and w2.work_main_label = #{work_main_label} ",
                    " </if>",
                    " <if test='work_vice_label != null and work_vice_label != \" \" '>",
                    " and w2.work_vice_label = #{work_vice_label} ",
                    " </if>",
                    " <if test='work_serial_state != null and work_serial_state != \" \" '>",
                    " and w2.work_serial_state = #{work_serial_state} ",
                    " </if>",

                    "</script>"
            }
    )
    public List<WorkWholeInfoVo> selectWorkWholeInfoVobyuser_name(WorksInfo worksInfo);

}
