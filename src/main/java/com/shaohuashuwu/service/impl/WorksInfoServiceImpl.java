package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.domain.vo.UserandWorksInfoVo;
import com.shaohuashuwu.service.AttentionInfoService;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public ChapterPostInfoDao chapterPostInfoDao;
    @Autowired
    public UserInfoDao userInfoDao;



    @Autowired
    public AttentionInfoService attentionInfoService;


    private WorksInfo worksInfo;
    private Difvolenum difvolenum;
    private UserInfo userInfo;


    /**
     * 获取分类统计的作品信息
     * @return 分类作品数量
     */
    @Override
    public Difvolenum getdifvolenum() {

        int xuanhuannum = worksInfoDao.selectnumBywork_main_label("玄幻");
        int qihuannum = worksInfoDao.selectnumBywork_main_label("奇幻");
        int wuxianum = worksInfoDao.selectnumBywork_main_label("武侠");
        int xianxianum = worksInfoDao.selectnumBywork_main_label("仙侠");
        int dushinum = worksInfoDao.selectnumBywork_main_label("都市");
        int xianshinum = worksInfoDao.selectnumBywork_main_label("现实");
        int lishinum = worksInfoDao.selectnumBywork_main_label("历史");
        int junshinum = worksInfoDao.selectnumBywork_main_label("军事");
        int youxinum = worksInfoDao.selectnumBywork_main_label("游戏");
        int xuanyinum = worksInfoDao.selectnumBywork_main_label("悬疑");
        int kehuannum = worksInfoDao.selectnumBywork_main_label("科幻");
        int tiyunum = worksInfoDao.selectnumBywork_main_label("体育");
        int qingxiaoshuonum = worksInfoDao.selectnumBywork_main_label("轻小说");

        difvolenum = new Difvolenum(xuanhuannum, qihuannum, wuxianum, xianxianum, dushinum,
                xianshinum, lishinum, junshinum, youxinum, xuanyinum, kehuannum, tiyunum, qingxiaoshuonum);

        return difvolenum;
    }


    /**
     * 依据作者名称查询作品名称
     * @param user_name
     * @return
     */
    public List<WorksInfo> getwork_nameByuser_name(String user_name){

        return worksInfoDao.selectwork_nameByuser_name(user_name);
    }

    /**
     * 模糊查询，依据模糊作品名称查询作品名称
     * @param worksInfo
     * @return
     */
    @Override
    public List<WorksInfo> getVaguework_nameBywork_name(WorksInfo worksInfo) {
        return worksInfoDao.selectVaguework_nameBywork_name(worksInfo);
    }



    /**
     * 根据不同条件查询书籍，sql拼接查询寻
     * @param pageInfo
     * @return
     */
    @Override
    public List<WorksInfo> getworksneed(PageInfo pageInfo) {
        //获取作品当前页，和每页数量
        int works_page = pageInfo.getWorks_page();
        int works_page_num = pageInfo.getWorks_page_num();
        //页面开始作品，页面结束作品
        int start = (works_page - 1) * works_page_num;
        int end = start + works_page_num;
        if (end > worksInfoDao.selectworksneed(pageInfo).size()) {
            end = worksInfoDao.selectworksneed(pageInfo).size();
        }

        return worksInfoDao.selectworksneed(pageInfo).subList(start, end);
    }

    /**
     * 获取全部作品页面，作品数量
     * @param pageInfo
     * @return
     */
    @Override
    public int getworkstotal(PageInfo pageInfo) {
        //返回作品数量
        return worksInfoDao.selectworksneed(pageInfo).size();
    }


    /**
     * 依据作品id查询作品信息
     * @param work_id
     * @return
     */
    @Override
    public WorksInfo getworkInfoByWork_id(int work_id) {
        return worksInfoDao.selectworkInfoByWork_id(work_id);
    }

    /**
     * 根据作品id获取作者的其他作品信息
     * @param work_id
     * @return
     */
    @Override
    public List<WorksInfo> getOtherWorkInfoByWork_id(int work_id) {
        return worksInfoDao.selectOtherWorkInfoByWork_id(work_id);
    }


    /**
     * 依据章节id查询作品信息
     * @param chapter_id
     * @return
     */
    @Override
    public WorksInfo getworkInfoByChapter_id(int chapter_id) {
        return chapterPostInfoDao.selectworkInfoByChapter_id(chapter_id);
    }


    /**
     * 依据用户id查询该用户作品数量
     * @param user_id
     * @return
     */
    @Override
    public int getWorksNumByUser_id(int user_id) {
        return worksInfoDao.selectWorksNumByUser_id(user_id);
    }

    /**
     * 根据用户id获取作品信息
     * @param user_id
     * @return
     */
    @Override
    public List<WorksInfo> getWorksInfoByUser_id(int user_id) {

        return worksInfoDao.selectWorksInfoByUser_id(user_id);
    }

    /**
     * 根据作品名称判断作品是否存在
     * @param work_name
     * @return
     */
    @Override
    public int isworkname(String work_name) {
        int num = worksInfoDao.selectWorkbywork_name(work_name);
        return num;
    }

    /**
     * 新建作品
     * @param worksinfo
     * @return
     */
    @Override
    public int addworksdate(WorksInfo worksinfo,int user_id) {

//      设置作品其他信息
        String work_main_label = worksinfo.getWork_main_label();
        if(work_main_label.equals("都市")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/dushicover.png");
        }
        else if(work_main_label.equals("军事")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/junshicover.png");
        }
        else if(work_main_label.equals("科幻")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/kehuancover.png");
        }
        else if(work_main_label.equals("历史")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/lishicover.png");
        }
        else if(work_main_label.equals("奇幻")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/qihaucover.png");
        }
        else if(work_main_label.equals("轻小说")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/qingxiaoshuocover.png");
        }
        else if(work_main_label.equals("体育")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/tiyucover.png");
        }
        else if(work_main_label.equals("武侠")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/wuxiacover.png");
        }
        else if(work_main_label.equals("现实")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/xianshicover.png");
        }
        else if(work_main_label.equals("仙侠")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/xianxiacover.png");
        }
        else if(work_main_label.equals("玄幻")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/xuanhuancover.png");
        }
        else if(work_main_label.equals("悬疑")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/xuanyicover.png");
        }
        else if(work_main_label.equals("游戏")){
            worksinfo.setWork_cover("https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/cover/youxicover.png");
        }

        worksinfo.setUser_id(user_id);
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

    /**
     * 根据作品id修改作品状态
     * @return
     */
    @Override
    public int updateWorkInfoByworkid(WorksInfo worksInfo) {
        int num = worksInfoDao.updateWorkInfoByworkid(worksInfo);
        return num;
    }




















    /************以下未完成***********/

















    @Override
    public List<WorksInfo> selectAllworks() {

        System.out.println("输出全部作品---");
        return worksInfoDao.selectAllworks();
    }










    @Override
    public int selectxuanhuannum() {

        int num = worksInfoDao.selectnumBywork_main_label("玄幻");
        System.out.println("num---" + num);
        return 0;
    }



    /**
     * 按关键字搜索小说
     * @param needworksInfo
     * @return
     */
    @Override
    public List<WorksInfo> selectworkbyinfoResult(WorksInfo needworksInfo) throws NullPointerException{

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
//        String user_name = needworksInfo.getWork_name();
//
//        try {
//            userInfo = userInfoDao.selectuserInfoByusername(user_name);
//            needworksInfo.setUser_id(userInfo.getUser_id());
//        }catch (Exception e){
//            System.out.println("异常"+e);
//        }
//
//        if (userInfo == null) {
//            System.out.println("空了");
//        }
//        else {
//            if (worksInfoDao.selectWorkInfobyuser_id(needworksInfo) == null) {
//                System.out.println("空了");
//            }
//            else {
//
//                worksInfoLists.addAll(worksInfoDao.selectWorkInfobyuser_id(needworksInfo));
//
//            }
//        }

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

    @Override
    public List<UserandWorksInfoVo> selectUserandWork(WorksInfo worksInfo) {


        List<UserandWorksInfoVo> list = worksInfoDao.selectUserandWork(worksInfo);

        System.out.println("输出list----"+list);
        return list;
    }




}
