package com.shaohuashuwu.test;


import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.service.WorksInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestWorks {

    @Autowired
    private WorksInfoService worksInfoService;
    @Autowired
    WorksInfoDao worksInfoDao;
//    @Autowired
    private WorksInfo worksInfo;
    private PageInfo pageInfo;

    @Test
    public void testfindAll(){
        List list = worksInfoService.selectAllworks();

        System.out.println(list);
    }

    @Test
    public void testaddWorks(){

        worksInfo = new WorksInfo("求魔",null,"玄幻",
                "东方玄幻",null,
                "这是一个作品介绍","这是作者给读者的话",null,null,null,null,null);

        int list = worksInfoService.addworksdate(worksInfo,1);


        System.out.println(list);
    }

    @Test
    public void testEorksnum(){
//        int num = worksInfoService.getWorkbywork_name("西游记");
//
//        System.out.println(" ---"+num);
    }

//    @Test
//    public void testSelectworkByid(){
//        worksInfo = worksInfoService.selectworkByid(14);
//        System.out.println(worksInfo);
//    }

    /*
    根据作品id修改作品状态
     */
    @Test
    public void testUpdateWorkSerialStateByid(){
        worksInfo = new WorksInfo(33,1);

        int num = worksInfoService.updateWorkInfoByworkid(worksInfo);
        System.out.println(num);
    }

    /**
     * 统计个标签数量
     */
    @Test
    public void testdifvolenum(){
        worksInfoService.getdifvolenum();
//        worksInfoService.selectxuanhuannum();
    }

    @Test
    public  void testworksinneed(){
        worksInfo = new WorksInfo("玄幻",null,1);

        pageInfo = new PageInfo("玄幻",null,null,1,10);

        List list = worksInfoService.getworksneed(pageInfo);

        System.out.println("数组中长度为：---"+list.size());
    }


    @Test
    public  void selectworkbyinfoResult(){


        worksInfo = new WorksInfo("不可思议","玄幻",null,null);

        List list = worksInfoService.selectworkbyinfoResult(worksInfo);
//        List list = worksInfoService.selectworksneed(pageInfo);

        System.out.println("数组中长度为：---"+list.size());
        System.out.println("最终结果"+list);
    }

    @Test
    public  void selectUserandWork(){


        worksInfo = new WorksInfo(14);

        List list = worksInfoService.selectUserandWork(worksInfo);
//        List list = worksInfoService.selectworksneed(pageInfo);

        System.out.println("数组中长度为：---"+list.size());
        System.out.println("最终结果"+list);
    }



    @Test
    public void testSelectworkInfoByChapter_id(){
        worksInfoService.getworkInfoByChapter_id(10);
    }



    @Test
    public  void selectworkBuwork_name(){

        worksInfo = new WorksInfo("不可思议",null,null,null);

            System.out.println("输出"+worksInfoDao.selectVaguework_nameBywork_name(worksInfo));
    }

}
