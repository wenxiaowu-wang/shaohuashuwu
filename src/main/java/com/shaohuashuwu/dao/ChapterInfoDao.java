package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.domain.ChapterInfo;
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

    //保存章节信息
    @Insert("insert into chapter_info(chapter_pid,chapter_title,chapter_time,chapter_word_num,chapter_content,chapter_other_word,chapter_state,chapter_charge) " +
            "values(#{chapter_pid},#{chapter_title},#{chapter_time},#{chapter_word_num},#{chapter_content},#{chapter_other_word},#{chapter_state},#{chapter_charge})")
    public int insertchapter_info(ChapterInfo chapterInfo);

//    根据上一章查找下一章
    @Select("SELECT chapter_id from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectChapter_idByChapter_pid(int chapter_pid);


//  查询作品第一章chapter_pid
    @Select("SELECT count(*) from chapter_info where chapter_pid = #{chapter_pid}")
    public int selectchapter_pidNum(int chapter_pid);


//    依据chapter_pid查找最大的chapter_id
    @Select("SELECT Max(chapter_id) from chapter_info where chapter_pid = #{chapter_pid}")
    public int sleectMaxChapter_idByChapter_pid (int chapter_pid);

//    依据chapter_id查询
    @Select("SELECT * from chapter_info where chapter_id = #{chapter_id}")
    public ChapterInfo selectchapterInfoByChapter_id(int chapter_id);

}
