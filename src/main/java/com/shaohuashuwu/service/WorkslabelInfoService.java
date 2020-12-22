package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorkslabelInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkslabelInfoService {


    //依据作品id查询作者自定义标签
    //功能点：修改作品信息
    public List<WorkslabelInfo> getWorkslabelInfoByWork_id(int work_id);

    //依据作品id修改作者自定义标签
    //功能点：修改作品信息
    public int updateWorkslabelInfoByWork_id(List<WorkslabelInfo> workslabelInfoList);
}
