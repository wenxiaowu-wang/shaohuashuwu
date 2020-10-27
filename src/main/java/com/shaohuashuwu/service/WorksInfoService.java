package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
public interface WorksInfoService {

    //查询所有作品信息
    public List<WorksInfo> selectAllworks();

    //依据作品id查询作品信息
    public WorksInfo selectworkByid(int work_id);

    //添加一个作品
    public int insertworks_info(WorksInfo works_info);

    //通过作品名称查询作品
    public int selectWorkbywork_name(String work_name);

    //通过作品id修改作品状态
    public int updateWorkSerialStateByid(WorksInfo worksInfo);

    //获取各个不同类型的书籍数量
    public Difvolenum selectdifvolenum();

    public int selectxuanhuannum();

    //根据不同条件查询书籍，sql拼接查询寻
    public List<WorksInfo> selectworksneed (PageInfo pageInfo);

    public  int selectworkstotal(PageInfo pageInfo);

    public List<WorksInfo> selectworkbyinfoResult(WorksInfo worksInfo);


}
