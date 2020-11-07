package com.shaohuashuwu.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ChapterPostInfoDao {

    //根据接受者ID获取对应章节的对应作品名字
    @Select("SELECT works_info.work_name FROM works_info,chapter_post_info WHERE works_info.work_id = chapter_post_info.work_id AND chapter_post_info.chapter_id = #{param1}")
    public String selectWorkNameByChapterId(int chapter_id);

}
