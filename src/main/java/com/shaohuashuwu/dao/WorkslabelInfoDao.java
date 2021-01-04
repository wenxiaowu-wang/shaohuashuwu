package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.WorkslabelInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkslabelInfoDao {

    //依据作品id查询作者自定义标签
    //功能点：修改作品信息
    @Select("SELECT * FROM works_label_info WHERE work_id = #{work_id}")
    public List<WorkslabelInfo> selectWorkslabelInfoByWork_id(int work_id);

    //依据作品id添加作者自定义标签
    //功能点：修改作品信息
    @Insert("insert into works_label_info(label_name,work_id) values (#{label_name},#{work_id})")
    public int insertWorkslabelInfoByWork_id(WorkslabelInfo workslabelInfo);

    //依据作品id删除作者自定义标签
    //功能点：修改作品信息
    @Delete("DELETE  FROM works_label_info WHERE work_id = #{work_id}")
    public int delectWorkslabelInfoByWork_id(int work_id);
}
