package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import java.util.List;

public interface WorkWholeInfoVoService {

    //获取主页分类内容
    //功能点：获取主页信息
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork);

    //通过搜索信息获取作品全部信息
    //功能点：关键字搜索搜索内容，
    public List<WorkWholeInfoVo> getWorkWholeInfoBySelectinput(WorksInfo worksInfo);

    //通过作者id信息获取作品全部信息
    //功能点：作品信息内容，
    public List<WorkWholeInfoVo> getworkWholeInfoVoByauthor_id(int author_id);

    //通过用户id信息获取作品全部信息
    //功能点：个性推荐作品
    List<WorkWholeInfoVo> getworkWholeInfoVoByuser_id(int user_id) throws Exception;
}
