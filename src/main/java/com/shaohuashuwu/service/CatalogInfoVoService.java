package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.CatalogInfoVo;

import java.util.List;

public interface CatalogInfoVoService {


    //依据用户id和作品id，获取章节目录信息
    public List<CatalogInfoVo> getchaptercatalogBywork_id(int user_id,int work_id) throws Exception;

    //依据用户id和章节id，获取章节目录信息
    public List<CatalogInfoVo> getchaptercatalogBychapter_id(int user_id,int chapter_id) throws Exception;

    //依据用户id，获取章节目录信息
    public List<CatalogInfoVo> getchaptercatalogBywork_id2(int work_id);
}
