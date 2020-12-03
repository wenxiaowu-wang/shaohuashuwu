package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.ChapterPostInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterPostInfoDao {



    //依据作品id查询作品章节数,判断章节是否存在
    @Select("SELECT count(*) from chapter_post_info where work_id = #{work_id}")
    public int selectchapterNum(int work_id);

    //依据作品名称，查询作品章节数
    @Select("SELECT count(*) from chapter_post_info cp1 where cp1.work_id = " +
            " (select work_id from works_info w1 where w1.work_name = #{work_name})")
    public int getchapterNumBywork_name(String work_name);


    //依据章节id查询作品信息
    @Select("SELECT w.work_id,w.work_name FROM works_info w,chapter_post_info c WHERE w.`work_id` = c.`work_id` AND c.`chapter_id` = #{chapter_id}")
    public WorksInfo selectworkInfoByChapter_id(int chapter_id);

    //依据章节id查询作者信息
    @Select("SELECT u.user_id,u.user_name FROM user_info u,chapter_post_info c WHERE u.`user_id` = c.`user_id` AND c.`chapter_id` = #{chapter_id}")
    public UserInfo selectUserInfoByChapter_id(int chapter_id);

    //保存章节信息
    @Insert("insert into chapter_post_info(user_id,work_id,chapter_id) " +
            "values(#{user_id},#{work_id},#{chapter_id})")
    public int insertchapter_post_info(ChapterPostInfo chapterPostInfo);

    //跟据作品id获取最大章节id
    @Select("SELECT MAX(chapter_id) FROM chapter_post_info WHERE work_id = #{work_id}")
    public int selectMaxchapter_idBywork_id(int work_id);















    /*测试*/
    @Select("SELECT chapter_time ,chapter_id from chapter_info  where chapter_id =#{chapter_id}" )
    public List<ChapterInfo> getTest(Integer chapter_id);







    /*以下未修改*/









    //跟据作品id获取最大章节id
    @Select("SELECT MIN(chapter_id) FROM chapter_post_info WHERE work_id = #{work_id} and chapter_id != 1")
    public int selectMinchapter_idBywork_id(int work_id);



}
