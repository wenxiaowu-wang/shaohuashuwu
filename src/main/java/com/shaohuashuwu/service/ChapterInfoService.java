package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.ChapterInfo;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ChapterInfoService {

    //根据作品id查询最新章节信息
    //功能点：作品详情时获取最新章节信息，
    public ChapterInfo getnewChapterInfoByword_id(int work_id);

    //依据chapter_id查询章节信息
    //功能点：阅读小说界面获取章节信息
    public ChapterInfo getchapterInfoByChapter_id(int chapter_id,int user_id);

    //保存章节信息
    //功能点：添加章节功能添加章节
    public int addchapter_info(ChapterInfo chapterInfo, int work_id,int user_id);




    /*
    * 郝振威
    * */
    //根据用户的ID，作品的ID 获取该用户未订阅章节的信息
    public List<ChapterInfo> getChapterInfoByUserIdWorkId(int work_id, int user_id);

    //根据用户的ID，作品的ID 获取该用户未订阅章节数量
    public int getChapterCountByUserIdWorkId(int work_id);

    //根据用户的ID，作品的ID 获取该用户已订阅章节的信息
    public List<ChapterInfo> getChapterInfoByUserIdWorkId2(int work_id,int user_id);

}
