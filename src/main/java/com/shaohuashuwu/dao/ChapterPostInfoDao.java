package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ChapterPostInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface ChapterPostInfoDao {

    //依据作品id查询作品章节数,判断章节是否存在
    //功能点：添加章节功能添加章节
    @Select("SELECT count(*) from chapter_post_info where work_id = #{work_id}")
    public int selectchapterNum(int work_id);

    //保存章节信息
    //功能点：添加章节功能添加章节
    @Insert("insert into chapter_post_info(user_id,work_id,chapter_id) " +
            "values(#{user_id},#{work_id},#{chapter_id})")
    public int insertchapter_post_info(ChapterPostInfo chapterPostInfo);

    //跟据作品id获取最大章节id
    //功能点：添加章节功能添加章节
    @Select("SELECT MAX(chapter_id) FROM chapter_post_info WHERE work_id = #{work_id}")
    public int selectMaxchapter_idBywork_id(int work_id);

    /**
     * 阿斌
     */
    //根据接受者ID获取对应章节的对应作品名字
    @Select("SELECT distinct works_info.work_name FROM works_info,chapter_post_info WHERE works_info.work_id = chapter_post_info.work_id AND chapter_post_info.chapter_id = #{param1}")
    public String selectWorkNameByChapterId(int chapter_id);

    //获取某作品的章节数
    @Select("SELECT IFNULL(COUNT(DISTINCT chapter_id),0) AS chapter_num FROM chapter_post_info WHERE work_id = #{param1}")
    public int selectChapterNumByWorkId(int work_id);
}
