package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkWholeInfoVoService {

    //获取主页分类内容
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork);

    //通过搜索信息获取作品全部信息
    public List<WorkWholeInfoVo> getWorkWholeInfoBySelectinput(WorksInfo worksInfo);

    //通过作品名称查询作品全部信息，并且作品状态不能为3
    public List<WorkWholeInfoVo> getWorkWholeInfoVobywork_name(WorksInfo worksInfo);

    //根据用户id获取书架信息
    public List<WorkWholeInfoVo> getWorkWholeInfoByuser_id(int user_id) throws Exception;

    //根据用户id获取阅读历史表信息
    public List<WorkWholeInfoVo> getWorkWholeInfoToHistoryByUser_id(int user_id) throws Exception;


}
