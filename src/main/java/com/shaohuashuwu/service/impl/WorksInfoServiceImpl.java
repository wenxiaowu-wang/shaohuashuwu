package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.service.WorksInfoService;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("worksInfoService")
public class WorksInfoServiceImpl implements WorksInfoService {

    @Autowired
    public WorksInfoDao worksInfoDao;

    @Autowired
    public UserInfoDao userInfoDao;
    //    @Autowired
    private WorksInfo worksInfo;
    private Difvolenum difvolenum;
    private UserInfo userInfo;


    @Override
    public List<WorksInfo> selectAllworks() {

        System.out.println("输出全部作品---");
        return worksInfoDao.selectAllworks();
    }

    /**
     * 依据作品id查询作品信息
     *
     * @param work_id
     * @return
     */
    @Override
    public WorksInfo selectworkByid(int work_id) {
        System.out.println("依据作品id查询作品信息--");
        System.out.println(worksInfoDao.selectworkByid(work_id));

        return worksInfoDao.selectworkByid(work_id);
    }

    /**
     * 新建作品
     *
     * @param worksinfo
     * @return
     */
    @Override
    public int insertworks_info(WorksInfo worksinfo) {
        System.out.println("业务层，添加新作品");
//      设置作品其他信息
        worksinfo.setUser_id(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        worksinfo.setWork_create_time(date);
        worksinfo.setWork_serial_state(1);
        worksinfo.setWork_word_num(0);
        worksinfo.setWork_tip_num(0);
        worksinfo.setWork_subscribe_num(0);
        worksinfo.setWork_vote_num(0);

        int sureorno = worksInfoDao.insertworks_info(worksinfo);
        System.out.println("是否受到影响：" + sureorno);

        return sureorno;
    }

    @Override
    public int selectWorkbywork_name(String work_name) {

        int num = worksInfoDao.selectWorkbywork_name(work_name);
        System.out.println("查询到的行数" + num);
        return num;
    }

    /**
     * 根据作品id修改作品状态
     *
     * @return
     */
    @Override
    public int updateWorkSerialStateByid(WorksInfo worksInfo) {
        System.out.println("server层查询");
//
//        worksInfo.setWork_id(10);
//        worksInfo.setWork_serial_state(2);
        int num = worksInfoDao.updateWorkSerialStateByid(worksInfo);
        System.out.println("server层查询成功");
        return num;
    }

    @Override
    public Difvolenum selectdifvolenum() {

        int xuanhuannum = worksInfoDao.selectxuanhuannum();
        int qihuannum = worksInfoDao.selectqihuannum();
        int wuxianum = worksInfoDao.selectwuxianum();
        int xianxianum = worksInfoDao.selectxianxianum();
        int dushinum = worksInfoDao.selectdushinum();
        int xianshinum = worksInfoDao.selectxianshinum();
        int lishinum = worksInfoDao.selectlishinum();
        int junshinum = worksInfoDao.selectjunshinum();
        int youxinum = worksInfoDao.selectyouxinum();
        int xuanyinum = worksInfoDao.selectxuanyinum();
        int kehuannum = worksInfoDao.selectkehuannum();
        int tiyunum = worksInfoDao.selecttiyunum();
        int qingxiaoshuonum = worksInfoDao.selectqingxiaoshuonum();

        difvolenum = new Difvolenum(xuanhuannum, qihuannum, wuxianum, xianxianum, dushinum,
                xianshinum, lishinum, junshinum, youxinum, xuanyinum, kehuannum, tiyunum, qingxiaoshuonum);
        System.out.println("不同标签数量---" + difvolenum);


        return difvolenum;
    }

    @Override
    public int selectxuanhuannum() {

        int num = worksInfoDao.selectxuanhuannum();
        System.out.println("num---" + num);
        return 0;
    }

    /**
     * 根据不同条件查询书籍，sql拼接查询寻
     *
     * @param pageInfo
     * @return
     */
    @Override
    public List<WorksInfo> selectworksneed(PageInfo pageInfo) {
        System.out.println("service查询--");
        System.out.println(worksInfoDao.selectworksneed(pageInfo));

        int works_page = pageInfo.getWorks_page();
        int works_page_num = pageInfo.getWorks_page_num();
        System.out.println("页数" + works_page);
        System.out.println("每页个数" + works_page_num);
//        return worksInfoDao.selectworksneed(works_info).subList(1,3);
        int start = (works_page - 1) * works_page_num;
        int end = start + works_page_num;
        if (end > worksInfoDao.selectworksneed(pageInfo).size()) {
            end = worksInfoDao.selectworksneed(pageInfo).size();
        }
//        selectworkstotal(worksInfoDao.selectworksneed(pageInfo).size());
        return worksInfoDao.selectworksneed(pageInfo).subList(start, end);
    }

    @Override
    public int selectworkstotal(PageInfo pageInfo) {
        return worksInfoDao.selectworksneed(pageInfo).size();
    }

    /**
     * 按关键字搜索小说
     * @param needworksInfo
     * @return
     */
    @Override
    public List<WorksInfo> selectworkbyinfoResult(WorksInfo needworksInfo) {

//        向返回集合中添加数据
        List<WorksInfo> worksInfoLists = new ArrayList<WorksInfo>();
        System.out.println("显示数据++");



//        1.先获取书籍名称相同的书籍
        worksInfo = worksInfoDao.selectWorkInfobywork_name(needworksInfo);
        if (worksInfo == null) {
            System.out.println("1空了");
        }
        else {
            worksInfoLists.add(worksInfo);
        }

//        2.再获取作者名称和提供类型相同的数据
        String user_name = needworksInfo.getWork_name();
        userInfo = userInfoDao.selectuserInfoByusername(user_name);
        needworksInfo.setUser_id(userInfo.getUser_id());
        if (userInfo == null) {
            System.out.println("空了");
        }
        else {
            if (worksInfoDao.selectWorkInfobyuser_id(needworksInfo) == null) {
                System.out.println("空了");
            }
            else {

                worksInfoLists.addAll(worksInfoDao.selectWorkInfobyuser_id(needworksInfo));

            }
        }

//         3.最后模糊查询获取数据
//        结果长度大于30就不在显示，少于30就获取
        if (worksInfoLists.size() >= 30) {
            System.out.println("到这了1111---");
            return worksInfoLists;
        }
        else {
            worksInfoLists.addAll(worksInfoDao.selectworksneed(needworksInfo));
            if (worksInfoLists.size() == 0) {
                System.out.println("到这了2222---");
                return null;
            } else if (worksInfoLists.size() != 0 && worksInfoLists.size() <= 30) {
                System.out.println("到这了3333---");
                return worksInfoLists;
            } else  {
                System.out.println("到这了4444---");
                return worksInfoLists.subList(0, 2);
            }
        }


    }



}
