package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
    public ChapterInfo getnewChapterInfoByword_id(int work_id);

    //依据chapter_id查询章节信息
    public ChapterInfo getchapterInfoByChapter_id(int chapter_id);


    //保存章节信息
    public int addchapter_info(ChapterInfo chapterInfo, int work_id,int user_id);

















    /*****************以下未修改*********************/






    //查询作品目录
    public List<CatalogInfoVo> selectchaptercatalog(int user_id, int chapter_id) throws Exception;

    public List<CatalogInfoVo> selectchaptercatalogBywork_id(int user_id, int work_id) throws Exception;

    //依据pid查询章节信息

    public ChapterInfo selectchapterInfoByChapter_pid(int chapter_pid);


}
