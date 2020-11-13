package com.shaohuashuwu.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ChapterInfoDao {

    //根据章节ID获取章节内容
    @Select("select chapter_content from chapter_info where chapter_id = #{chapter_id}")
    public String selectChapterContentByChapterId(int chapter_id);

    //更新对应章节ID的章节状态为0（下架）
    @Update("update chapter_info set chapter_state = 0 where chapter_id = #{chapter_id}")
    public int updateChapterStateByChapterId(int chapter_id);

    //根据章节ID获取章节标题名字
    @Select("select distinct chapter_title from chapter_info where chapter_id = #{param1}")
    public String selectChapterTitleByChapterId(int chapter_id);

}
