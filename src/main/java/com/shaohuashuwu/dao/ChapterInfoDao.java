package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.ChapterPostInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    //获取依据作品id最新章节信息
    @Select(" SELECT c1.chapter_id chapter_id,c1.chapter_title chapter_title " +
            " from chapter_info c1 " +
            " where c1.chapter_id = " +
            "   (select max(cp1.chapter_id) " +
            "   from chapter_post_info cp1 " +
            "   where cp1.work_id =#{work_id})")
    public ChapterInfo selectnewChapterInfoByword_id(int work_id);

    //    依据chapter_id查询
    @Select("SELECT * from chapter_info where chapter_id = #{chapter_id} ")
    public ChapterInfo selectchapterInfoByChapter_id(int chapter_id);


    //保存章节信息
    @Insert("insert into chapter_info(chapter_pid,chapter_title,chapter_time,chapter_word_num,chapter_content,chapter_other_word,chapter_state,chapter_charge) " +
            "values(#{chapter_pid},#{chapter_title},#{chapter_time},#{chapter_word_num},#{chapter_content},#{chapter_other_word},#{chapter_state},#{chapter_charge})")
    public int insertchapter_info(ChapterInfo chapterInfo);


    // 根据chapter_pid查询作品数目
    @Select("SELECT count(*) from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectchapter_pidNum(int chapter_pid);

    //根据章节pid查找id
    @Select("SELECT chapter_id from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectChapter_idByChapter_pid(int chapter_pid);

    //当chapter_pid==1时，查找最大的chapter_id
    @Select("SELECT Max(chapter_id) from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectMaxChapter_idByChapter_pid (int chapter_pid);














    /*****************以下未修改*********************/














    //依据pid查询章节信息
    @Select("SELECT * from chapter_info where chapter_pid = #{chapter_pid} ")
    public ChapterInfo selectchapterInfoByChapter_pid(int chapter_pid);



}
