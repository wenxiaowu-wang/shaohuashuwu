package com.shaohuashuwu.dao;



import com.shaohuashuwu.domain.UserinterestInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.PageInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface WorksInfoDao {

    //依据作类型查询作品数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = #{work_main_label} and work_serial_state != 3 ")
    public int selectnumBywork_main_label(String work_main_label);

    //依据作者名称查询作品名称
    //功能点：关键字搜索搜索内容，
    @Select("Select w1.work_name work_name" +
            "   from works_info w1 " +
            "   where w1.user_id = " +
            "   (select u1.user_id from user_info u1 where u1.user_name = #{user_name})")
    public List<WorksInfo> selectwork_nameByuser_name(String user_name);


    //模糊查询，依据模糊作品名称查询作品名称
    //功能点：关键字搜索搜索内容，
    @Select("Select w1.work_name work_name " +
            "   from works_info w1 " +
            "   where w1.work_id in " +
            "   (select w2.work_id from works_info w2 where w2.work_name like '%${work_name}%'  and w2.work_name != #{work_name})")
    public List<WorksInfo> selectVaguework_nameBywork_name(WorksInfo worksInfo);

    //按条件查询作品信息，作品主副类型，作品状态
    @Select({
            "<script>",
            "SELECT * FROM works_info where 1=1 and work_serial_state != 3 and work_serial_state != 4",
            "<if test='work_main_label != null and work_main_label != \" \" '>",
            "and work_main_label = #{work_main_label} ",
            "</if>",
            "<if test='work_vice_label != null and work_vice_label != \" \" '>",
            "and work_vice_label = #{work_vice_label} ",
            "</if>",
            "<if test='work_serial_state != null and work_serial_state != \" \" '>",
            "and work_serial_state = #{work_serial_state} ",
            "</if>",
            "<if test='work_name != null and work_name != \" \" '>",
            "and work_name like '%${work_name}%'  and work_name != #{work_name}",
            "</if>",
            "</script>"
    })
    public List<WorksInfo> selectworksneed(PageInfo pageInfo);

    //依据作品id查询作品信息
    //功能点：作品详情时获取作品信息，添加章节获取作品信息，作品设置中获取作品信息，下架作品中获取作品信息
    @Select("select * from works_info where work_id = #{work_id}")
    public WorksInfo selectworkInfoByWork_id(int work_id);


    //依据作品id获取该作品作者的其他作品信息
    @Select(" select w1.work_id work_id,w1.work_name work_name,w1.work_cover work_cover,w1.work_introduct work_introduct,w1.work_word_num work_word_num " +
            " from works_info w1 " +
            " where w1.user_id = " +
            "   (select w2.user_id " +
            "    from works_info w2" +
            "    where w2.work_id = #{work_id})" +
            " and w1.work_serial_state != 3")
    public List<WorksInfo> selectOtherWorkInfoByWork_id(int work_id);


    //依据用户id查询该用户作品数量
    //功能点：作者端顶部作品数量，
    @Select("select count(*) from works_info where user_id = #{user_id}")
    public int selectWorksNumByUser_id(int user_id);

    //根据用户id获取作品信息
    //功能点：作者端工作台作品信息，
    @Select("select work_id,work_cover,work_name,work_main_label,work_vice_label,work_serial_state,work_word_num,work_tip_num,work_subscribe_num,work_vote_num,work_create_time  " +
            " from works_info where user_id = #{user_id}")
    public List<WorksInfo> selectWorksInfoByUser_id(int user_id);

    //通过作品名称查询作品数量，判断作品是否存在
    //功能点：作者端工作台作品信息，
    @Select("select count(*) from works_info where work_name = #{work_name}")
    public int selectWorkbywork_name(String work_name);

    //通过作品id修改作品信息
    //功能点：下架作品中修改作品设置信息
    @Update("update works_info set work_serial_state = #{work_serial_state} where work_id = #{work_id} ")
    public int updateWorkInfoByworkid(WorksInfo worksInfo);

    //依据章节id查询作品信息
    //功能点：阅读小说界面获取作品信息
    @Select("SELECT w.work_id,w.work_name FROM works_info w,chapter_post_info c WHERE w.`work_id` = c.`work_id` AND c.`chapter_id` = #{chapter_id}")
    public WorksInfo selectworkInfoByChapter_id(int chapter_id);

    //添加作品信息
    //功能点：添加作品功能添加作品
    @Insert("insert into works_info(work_name,work_cover,user_id,work_main_label,work_vice_label,work_serial_state,work_introduct,work_other_word,work_word_num,work_tip_num,work_subscribe_num,work_vote_num,work_create_time) " +
            "values (#{work_name},#{work_cover},#{user_id},#{work_main_label},#{work_vice_label},#{work_serial_state},#{work_introduct},#{work_other_word},#{work_word_num},#{work_tip_num},#{work_subscribe_num},#{work_vote_num},#{work_create_time} )")
    public int insertworks_info(WorksInfo worksInfo);


    //修改作品信息
    //功能点：修改作品信息
    @Update("UPDATE works_info SET work_cover = #{work_cover},work_main_label = #{work_main_label},work_vice_label = #{work_vice_label},work_serial_state = #{work_serial_state},work_introduct = #{work_introduct},work_other_word = #{work_other_word} WHERE work_id = #{work_id}")
    public void updateworkInfoByWork_id(WorksInfo worksInfo);

    //修改作品总字数
    //功能点：上传章节
    @Update("Update works_info SET work_word_num=work_word_num+#{work_word_num} where work_id = #{work_id}")
    public int updateWork_word_numByWork_id(WorksInfo worksInfo);



    //通过作品完结类型获取作品信息
    //功能点：个性推荐
    @Select(
            {"<script>",
                    " SELECT work_name FROM works_info WHERE work_serial_state = 2 " ,
                    " <if test='label_name != null and label_name != \" \" '>",
                    " and work_main_label = #{label_name} ",
                    " </if>",
                    " ORDER BY work_vote_num DESC LIMIT 10",
                    "</script>"
            })
    public List<WorksInfo> selectworkInfoBywork_main_label1(UserinterestInfo userInterestInfo);

    //通过作品连载类型获取作品信息
    //功能点：个性推荐
    @Select(
            {"<script>",
                    " SELECT work_name FROM works_info WHERE work_serial_state = 1 " ,
                    " <if test='label_name != null and label_name != \" \" '>",
                    " and work_main_label = #{label_name} ",
                    " </if>",
                    " ORDER BY work_vote_num DESC LIMIT 10",
             "</script>"
            })
    public List<WorksInfo> selectworkInfoBywork_main_label2(UserinterestInfo userInterestInfo);


    /*
    * 郝振威
    *
    *
    *
    *
    *
    *
    *
    * */










    //根据用户ID获取用户加入书架的作品的作品名字
    @Select("SELECT w1.* FROM bookshelf_info,works_info w1 WHERE bookshelf_info.work_id = w1.work_id AND bookshelf_info.user_id = #{user_id} ORDER BY collection_time DESC")
    @ResultMap("worksInfo")
    public List<WorksInfo> selectBookshelfWorkNameByWorkID(int user_id);


    //根据用户ID获取用户加入书架的作品的作品名字
    @Select("SELECT w1.* FROM reading_history_info,works_info w1 WHERE reading_history_info.work_id = w1.work_id AND reading_history_info.user_id = #{user_id} ORDER BY reading_time DESC")
    @ResultMap("worksInfo")
    public List<WorksInfo> selectReadingHistoryWorkNameByWorkID(int user_id);


    //根据用户的ID获取该用户写过的作品ID、作品名字
    @Select("select work_id,work_name from works_info where user_id = #{user_id}")
    public List<WorksInfo> selectWorkIdNameByUserId(int user_id);




/*
*郝振威
*
* */


    //根据章节ID获取作品名字
    @Select("SELECT work_name FROM works_info WHERE work_id = (SELECT work_id FROM chapter_post_info WHERE chapter_id = #{param1})")
    public String selectWorkNameByChapterId(int chapter_id);

    //根据章节ID获取作品Id
    @Select("SELECT work_id FROM chapter_post_info WHERE chapter_id = #{param1}")
    public String selectWorkIdByChapterId(int chapter_id);

    /**
     * 阿斌
     */
    //根据作者ID获取所有对应的作品信息
    @Select("select *from works_info where user_id = #{user_id}")
    @Results(id = "worksInfo",value = {
            @Result(id = true,column = "work_id",property = "work_id"),
            @Result(column = "work_cover",property = "work_cover"),
            @Result(column = "work_name",property = "work_name"),
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "work_main_label",property = "work_main_label"),
            @Result(column = "work_vice_label",property = "work_vice_label"),
            @Result(column = "work_serial_state",property = "work_serial_state"),
            @Result(column = "work_introduct",property = "work_introduct"),
            @Result(column = "work_other_word",property = "work_other_word"),
            @Result(column = "work_word_num",property = "work_word_num"),
            @Result(column = "work_tip_num",property = "work_tip_num"),
            @Result(column = "work_subscribe_num",property = "work_subscribe_num"),
            @Result(column = "work_vote_num",property = "work_vote_num")
    })
    public List<WorksInfo> selectAllByUserId(int user_id);

    //根据作品ID获取对应作者ID
    @Select("select user_id from works_info where work_id = #{work_id}")
    public int selectAuthorIdByWorkId(int work_id);

    //根据作品ID获取作品名字
    @Select("select work_name from works_info where work_id = #{work_id}")
    public String selectWorkNameByWorkId(int work_id);

    //根据作品ID获取作品发布时间
    @Select("SELECT work_create_time FROM works_info WHERE work_id = #{param1}")
    public Timestamp selectWorkCreateTimeByWorkId(int work_id);

    //根据作品ID更新投票数量
    @Update("update works_info set work_vote_num = work_vote_num + (#{param2}) where work_id = #{param1}")
    public int updateWorkVoteNumByWorkId(int work_id,int voteNum);

    //根据作品id更新打赏数量
    @Update("update works_info set work_tip_num = work_tip_num + (#{param2}) where work_id = #{param1}")
    public int updateWorkTipNumByWorkId(int work_id,int tipNum);

}

