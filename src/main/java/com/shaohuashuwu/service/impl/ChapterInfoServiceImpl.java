package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.*;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import com.shaohuashuwu.service.ChapterInfoService;
import com.shaohuashuwu.util.ThisCurrentTime;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("chapterInfoService")
public class ChapterInfoServiceImpl implements ChapterInfoService {

    @Autowired
    public ChapterInfoDao chapterInfoDao;
    @Autowired
    public ChapterPostInfoDao chapterPostInfoDao;
    @Autowired
    public WorksInfoDao worksInfoDao;
    @Autowired
    public TransactionInfoDao transactionInfoDao;



    public ChapterPostInfo chapterPostInfo;
    public ChapterPostInfo nullchapterPostInfo;
    public WorksInfo worksInfo;
    public CatalogInfoVo catalogInfoVo;
    public ChapterInfo chapterInfo;
    public TransactionInfo transactionInfo;

    @Autowired
    public ThisCurrentTime thisCurrentTime;

    @Override
    public int insertchapter_info(ChapterInfo chapterInfo,int work_id) {

        System.out.println("-----");
        worksInfo = worksInfoDao.selectworkByid(work_id);

//        先判断work_id是否存在，若不存在创建一个，chapter_id指向1，user_id指向作者id

        int num = chapterPostInfoDao.selectwork_idNum(work_id);
        System.out.println("章节数量："+num);
        if(num == 0){
            nullchapterPostInfo = new ChapterPostInfo(worksInfo.getUser_id(),worksInfo.getWork_id(),1);
            chapterPostInfoDao.insertchapter_post_info(nullchapterPostInfo);

        }




        /**
         * 设置chapterInfo,添加chapter_info
         */
//        1.先获取最大的父章节id，为传入的小说设置上个章节
        int chapter_pid_max = 0;
        try {
            chapter_pid_max = chapterPostInfoDao.selectMaxchapter_idBywork_id(work_id);
            System.out.println("chapter_pid显示："+chapter_pid_max);
        }catch (NullPointerException e){
            System.out.println("空");
        }



        chapterInfo.setChapter_pid(chapter_pid_max);
        chapterInfo.setChapter_time(thisCurrentTime.currentTime());
        int a = chapterInfoDao.insertchapter_info(chapterInfo);
        System.out.println("添加章节Service层"+chapterInfo);
        System.out.println("受影响行数"+a);


        /**
         *添加chapter_post_info信息
         */
//        在此获取刚才添加章节的id

//        先获取chapter_pid数量
        int chapter_pid = chapterInfoDao.selectchapter_pidNum(chapter_pid_max);
        int maxchapter_id = 1;
        if(chapter_pid == 1){
            maxchapter_id = chapterInfoDao.selectChapter_idByChapter_pid(chapter_pid_max);
        }else {
            maxchapter_id = chapterInfoDao.sleectMaxChapter_idByChapter_pid(1);
        }


        System.out.println("maxchapter_id显示："+maxchapter_id);



        System.out.println("work_id:"+worksInfo.getWork_id());
        System.out.println("user_id:"+worksInfo.getUser_id());

        chapterPostInfo = new ChapterPostInfo(worksInfo.getUser_id(),worksInfo.getWork_id(),maxchapter_id);

        System.out.println(chapterPostInfo);
        chapterPostInfoDao.insertchapter_post_info(chapterPostInfo);



        return a;

    }

    //    依据chapter_id查询
    @Override
    public ChapterInfo selectchapterInfoByChapter_id(int chapter_id) {
        return chapterInfoDao.selectchapterInfoByChapter_id(chapter_id);
    }



    /**
     * 获取作品目录信息
     * @param chapter_id
     * @return
     */
    @Override
    public List<CatalogInfoVo> selectchaptercatalog(int user_id,int chapter_id) throws Exception{



        /**
         * 步骤：
         * 1.查询chapterPostInfo表，依据章节id获取作品id
         * 2.查询chapterPostInfo表，依据作品id获取最小的章节id
         * 3.获取章节数目用于循环
         * 4.循环查询chapterInfo表，作品id=pid，查询作品id，循环查询
         *      注：1.查询时应该判断是否被封禁，被封禁则查询不到
         *              解决：sql限定错，获取得到然后剔除，原因sql直接限制会导致下个章节id获取不到
         *          2.查询表transactionInfo表，判断是否购买过章节，增加到新类中
         *          3.将chapter_id_min设为父pid，获取下一章节id
         *      问题：现在我要不要返回时，带是否付款--
         *          分析：带付款作用是在界面显示时，小锁是否解开，应该要有
         *          结果：带
         *      问题：怎么带？是新建一个类？还是返回两个类？
         *          分析：1.一个类简单，2.两个类，前端怎么判断？循环判断？难
         *          结果：新建类，return新类
         */

        System.out.println("接收到数据");
        System.out.println("user——id数据："+user_id);
        System.out.println("user——id数据："+chapter_id);
        //1.查询chapterPostInfo表，依据章节id获取作品id
        worksInfo =chapterPostInfoDao.selectworkInfoByChapter_id(chapter_id);
        int work_id = worksInfo.getWork_id();
        System.out.println("获取作品id"+work_id);

        List<CatalogInfoVo>  catalogInfoVoList = selectchaptercatalogBywork_id(user_id,work_id);

        // 2.查询chapterPostInfo表，依据作品id获取最小的章节id
//        int chapter_id_min = chapterPostInfoDao.selectMinchapter_idBywork_id(work_id);
//        System.out.println("获取最小章节id"+chapter_id_min);
//
//        //3.获取章节数目用于循环
//        int chapter_num = chapterPostInfoDao.selectwork_idNum(work_id);
//        System.out.println("获取目录数量"+chapter_num);
//
//        //4.循环查询chapterInfo表，作品id=pid，查询作品id，循环查询
//        int subscribeResult = 0;
//        for (int i = 1;i < chapter_num ;i++){
//            System.out.println("i的值---"+i);
//            //章节信息系
//            chapterInfo = chapterInfoDao.selectchapterInfoByChapter_id(chapter_id_min);
//            System.out.println("获取章节信息"+chapterInfo);
//            //是否订阅
//            transactionInfo = new TransactionInfo(user_id,chapter_id_min);
//            subscribeResult = transactionInfoDao.subscribeResult(transactionInfo);
//            System.out.println("获取是否订阅"+subscribeResult);
//            //设置目录信息
//            //如果作品状态 == 1，就加入目录，！= 1，就不加
//            if(chapterInfo.getChapter_state() == 1){
//                catalogInfoVo = new CatalogInfoVo(chapterInfo.getChapter_id(),chapterInfo.getChapter_title(),chapterInfo.getChapter_charge(),subscribeResult);
//                System.out.println("获取目录信息----"+catalogInfoVo);
//                System.out.println("获取目录信息+++++"+catalogInfoVo);
//                catalogInfoVoList.add(catalogInfoVo);
//            }
//
//            //将chapter_id_min设为父pid，获取下一章节id
//            if(i != (chapter_num - 1)){
//                chapter_id_min = chapterInfoDao.selectChapter_idByChapter_pid(chapter_id_min);
//            }
//            System.out.println("获取最小章节id+++++"+chapter_id_min);
//            System.out.println("循环内目录"+catalogInfoVoList);
//        }
//        System.out.println("最终目录"+catalogInfoVoList);
        return catalogInfoVoList;
    }


    @Override
    public List<CatalogInfoVo> selectchaptercatalogBywork_id(int user_id,int work_id) throws Exception{

        List<CatalogInfoVo> catalogInfoVoList = new ArrayList<CatalogInfoVo>();
        // 2.查询chapterPostInfo表，依据作品id获取最小的章节id
        int chapter_id_min = chapterPostInfoDao.selectMinchapter_idBywork_id(work_id);
        System.out.println("获取最小章节id"+chapter_id_min);

        //3.获取章节数目用于循环
        int chapter_num = chapterPostInfoDao.selectwork_idNum(work_id);
        System.out.println("获取目录数量"+chapter_num);

        //4.循环查询chapterInfo表，作品id=pid，查询作品id，循环查询
        int subscribeResult = 0;
        for (int i = 1;i < chapter_num ;i++){
            System.out.println("i的值---"+i);
            //章节信息系
            chapterInfo = chapterInfoDao.selectchapterInfoByChapter_id(chapter_id_min);
            System.out.println("获取章节信息"+chapterInfo);
            //是否订阅
            transactionInfo = new TransactionInfo(user_id,chapter_id_min);
            subscribeResult = transactionInfoDao.subscribeResult(transactionInfo);
            System.out.println("获取是否订阅"+subscribeResult);
            //设置目录信息
            //如果作品状态 == 1，就加入目录，！= 1，就不加
            if(chapterInfo.getChapter_state() == 1){
                catalogInfoVo = new CatalogInfoVo(chapterInfo.getChapter_id(),chapterInfo.getChapter_title(),chapterInfo.getChapter_charge(),subscribeResult);
                System.out.println("获取目录信息----"+catalogInfoVo);
                System.out.println("获取目录信息+++++"+catalogInfoVo);
                catalogInfoVoList.add(catalogInfoVo);
            }

            //将chapter_id_min设为父pid，获取下一章节id
            if(i != (chapter_num - 1)){
                chapter_id_min = chapterInfoDao.selectChapter_idByChapter_pid(chapter_id_min);
            }
            System.out.println("获取最小章节id+++++"+chapter_id_min);
            System.out.println("循环内目录"+catalogInfoVoList);
        }
        System.out.println("最终目录"+catalogInfoVoList);
        return catalogInfoVoList;

    }

    @Override
    public ChapterInfo selectchapterInfoByChapter_pid(int chapter_pid) {

        chapterInfo = chapterInfoDao.selectchapterInfoByChapter_pid(chapter_pid);

        return chapterInfo;
    }


}
