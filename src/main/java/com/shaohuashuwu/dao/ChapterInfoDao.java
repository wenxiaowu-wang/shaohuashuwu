package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.ChapterInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface ChapterInfoDao {

    //获取依据作品id最新章节信息,且章节状态不能为3
    //功能点：作品详情时获取最新章节信息，
    @Select("SELECT c2.chapter_id chapter_id,c2.chapter_title chapter_title  " +
            " FROM chapter_info c2  " +
            " WHERE c2.chapter_id = " +
            "    (SELECT MAX(c1.chapter_id)  " +
            "    FROM chapter_info c1 " +
            "    WHERE c1.chapter_id IN  " +
            "        (SELECT cp1.chapter_id  " +
            "        FROM chapter_post_info cp1  " +
            "        WHERE cp1.work_id = 76) " +
            "    AND c1.chapter_state != 3)")
    public ChapterInfo selectnewChapterInfoByword_id(int work_id);

    //    依据chapter_id查询
    //功能点：阅读小说界面获取章节信息
    @Select("SELECT * from chapter_info where chapter_id = #{chapter_id} ")
    public ChapterInfo selectchapterInfoByChapter_id(int chapter_id);


    //保存章节信息
    //功能点：添加章节功能添加章节
    @Insert("insert into chapter_info(chapter_pid,chapter_title,chapter_time,chapter_word_num,chapter_content,chapter_other_word,chapter_state,chapter_charge) " +
            "values(#{chapter_pid},#{chapter_title},#{chapter_time},#{chapter_word_num},#{chapter_content},#{chapter_other_word},#{chapter_state},#{chapter_charge})")
    public int insertchapter_info(ChapterInfo chapterInfo);


    // 根据chapter_pid查询作品数目
    //功能点：添加章节功能添加章节
    @Select("SELECT count(*) from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectchapter_pidNum(int chapter_pid);

    //根据章节pid查找id
    //功能点：添加章节功能添加章节
    @Select("SELECT chapter_id from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectChapter_idByChapter_pid(int chapter_pid);

    //当chapter_pid==1时，查找最大的chapter_id
    //功能点：添加章节功能添加章节
    @Select("SELECT Max(chapter_id) from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectMaxChapter_idByChapter_pid (int chapter_pid);

    //依据举报id修改下架章节
    //功能点：处理举报章章节
    @Select("UPDATE chapter_info SET chapter_state = 2 where chapter_id = #{chapter_id}")
    public void updateChapter_stateByChapter_id (int chapter_id);

    //依据举报id修改上架章节
    //功能点：更改处理结果举报章章节
    @Select("UPDATE chapter_info SET chapter_state = 1 where chapter_id = #{chapter_id}")
    public void updateChapter_stateByChapter_id2 (int chapter_id);


}
