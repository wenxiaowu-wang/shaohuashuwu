package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
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
    //功能点：关键字搜索搜索内容，
    public List<WorksInfo> getwork_nameByuser_name(String user_name);

    //模糊查询，依据模糊作品名称查询作品名称
    //功能点：关键字搜索搜索内容，
    public List<WorksInfo> getVaguework_nameBywork_name( WorksInfo worksInfo);

    //获取全部作品页面的作品信息
    //功能点：获取用全部作品界面的作品信息
    public List<WorksInfo> getworksneed (PageInfo pageInfo);

    //获取全部作品页面的作品数量
    //功能点：获取用全部作品界面的作品数量
    public  int getworkstotal(PageInfo pageInfo);

    //依据作品id查询作品信息
    //功能点：作品详情时获取作品信息，添加章节获取作品信息，作品设置中获取作品信息，下架作品中获取作品信息
    public WorksInfo getworkInfoByWork_id(int work_id);

    //根据作品id获取作者的其他作品信息
    //功能点：作品详情时获取作者其他作品信息，
    public List<WorksInfo> getOtherWorkInfoByWork_id(int work_id);

    //依据章节id查询作品信息
    //功能点：阅读小说界面获取作品信息
    public WorksInfo getworkInfoByChapter_id(int chapter_id);

    //依据用户id查询该用户作品数量
    //功能点：作者端顶部作品数量，
    public int getWorksNumByUser_id(int user_id);

    //根据用户id获取作品信息
    //功能点：作者端工作台作品信息，
    public List<WorksInfo> getWorksInfoByUser_id(int user_id);


    //通过作品名称判断作品是否存在
    //添加作品功能验证作品是否存在
    public int isworkname(String work_name);

    //添加一个作品
    //功能点：添加作品功能添加作品
    public int addworkInfo(WorksInfo works_info,int user_id);


    //通过作品id修改作品状态
    //功能点：下架作品中修改作品设置信息
    public int updateWorkInfoByworkid(WorksInfo worksInfo);




}
