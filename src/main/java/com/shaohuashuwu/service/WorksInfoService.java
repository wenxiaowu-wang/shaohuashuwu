package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.UserandWorksInfoVo;
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

    //获取各个不同类型的书籍数量
    public Difvolenum getdifvolenum();

    //依据作者名称查询作品名称
    public List<WorksInfo> getwork_nameByuser_name(String user_name);

    //模糊查询，依据模糊作品名称查询作品名称
    public List<WorksInfo> getVaguework_nameBywork_name( WorksInfo worksInfo);

    //获取全部作品页面的作品信息
    public List<WorksInfo> getworksneed (PageInfo pageInfo);

    //获取全部作品页面的作品数量
    public  int getworkstotal(PageInfo pageInfo);

    //依据作品id查询作品信息
    public WorksInfo getworkInfoByWork_id(int work_id);

    //根据作品id获取作者的其他作品信息
    public List<WorksInfo> getOtherWorkInfoByWork_id(int work_id);

    //依据章节id查询作品信息
    public WorksInfo getworkInfoByChapter_id(int chapter_id);

    //依据用户id查询该用户作品数量
    public int getWorksNumByUser_id(int user_id);

    //根据用户id获取作品信息
    public List<WorksInfo> getWorksInfoByUser_id(int user_id);


    //通过作品名称判断作品是否存在
    public int isworkname(String work_name);

    //添加一个作品
    public int addworksdate(WorksInfo works_info,int user_id);


    //通过作品id修改作品状态
    public int updateWorkInfoByworkid(WorksInfo worksInfo);










    /************************未完成修改********************/

    //查询所有作品信息
    public List<WorksInfo> selectAllworks();











    public int selectxuanhuannum();



    public List<WorksInfo> selectworkbyinfoResult(WorksInfo worksInfo);


    //    查询出作品信息和作者
    public List<UserandWorksInfoVo> selectUserandWork(WorksInfo worksInfo);



}
