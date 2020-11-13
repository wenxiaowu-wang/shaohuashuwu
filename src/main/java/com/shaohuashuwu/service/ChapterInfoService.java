package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.NoticeInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ChapterInfoService {

    //保存章节信息
    public int insertchapter_info(ChapterInfo chapterInfo,int work_id);

    //    依据chapter_id查询
    public ChapterInfo selectchapterInfoByChapter_id(int chapter_id);

}
