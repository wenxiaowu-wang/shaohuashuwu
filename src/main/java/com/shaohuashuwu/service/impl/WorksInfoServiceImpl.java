package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
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

    private Difvolenum difvolenum;

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
     * 功能点：关键字搜索搜索内容，
     * @param user_name
     * @return
     */
    public List<WorksInfo> getwork_nameByuser_name(String user_name){

        return worksInfoDao.selectwork_nameByuser_name(user_name);
    }

    /**
     * 模糊查询，依据模糊作品名称查询作品名称
     * 功能点：关键字搜索搜索内容，
     * @param worksInfo
     * @return
     */
    @Override
    public List<WorksInfo> getVaguework_nameBywork_name(WorksInfo worksInfo) {
        return worksInfoDao.selectVaguework_nameBywork_name(worksInfo);
    }

    /**
     * 根据不同条件查询书籍，sql拼接查询寻
     * 功能点：获取用全部作品界面的作品信息
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
     * 功能点：获取用全部作品界面的作品数量
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
     * 功能点：作品详情时获取作品信息，添加章节获取作品信息，作品设置中获取作品信息，下架作品中获取作品信息
     * @param work_id
     * @return
     */
    @Override
    public WorksInfo getworkInfoByWork_id(int work_id) {
        return worksInfoDao.selectworkInfoByWork_id(work_id);
    }

    /**
     * 根据作品id获取作者的其他作品信息
     * 功能点：作品详情时获取作者其他作品信息，
     * @param work_id
     * @return
     */
    @Override
    public List<WorksInfo> getOtherWorkInfoByWork_id(int work_id) {
        return worksInfoDao.selectOtherWorkInfoByWork_id(work_id);
    }


    /**
     * 依据章节id查询作品信息
     * 功能点：阅读小说界面获取作品信息
     * @param chapter_id
     * @return
     */
    @Override
    public WorksInfo getworkInfoByChapter_id(int chapter_id) {
        return worksInfoDao.selectworkInfoByChapter_id(chapter_id);
    }


    /**
     * 依据用户id查询该用户作品数量
     * 功能点：作者端顶部作品数量，
     * @param user_id
     * @return
     */
    @Override
    public int getWorksNumByUser_id(int user_id) {
        return worksInfoDao.selectWorksNumByUser_id(user_id);
    }

    /**
     * 根据用户id获取作品信息
     * 功能点：作者端工作台作品信息，
     * @param user_id
     * @return
     */
    @Override
    public List<WorksInfo> getWorksInfoByUser_id(int user_id) {

        return worksInfoDao.selectWorksInfoByUser_id(user_id);
    }

    /**
     * 根据作品名称判断作品是否存在
     * 添加作品功能验证作品是否存在
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
     * 功能点：添加作品功能添加作品
     * @param worksinfo
     * @return
     */
    @Override
    public int addworkInfo(WorksInfo worksinfo,int user_id) {

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

        int insertResult = worksInfoDao.insertworks_info(worksinfo);

        return insertResult;
    }

    /**
     * 根据作品id修改作品状态
     * 功能点：下架作品中修改作品设置信息
     * @return
     */
    @Override
    public int updateWorkInfoByworkid(WorksInfo worksInfo) {
        int updateResult = worksInfoDao.updateWorkInfoByworkid(worksInfo);
        return updateResult;
    }

    /**
     * 修改作品信息
     *功能点：修改作品信息
     * @param worksInfo
     * @return
     */
    @Override
    public void updateworkInfoByWork_id(WorksInfo worksInfo) {
         worksInfoDao.updateworkInfoByWork_id(worksInfo);
    }




    /*
    * 郝振威
    *
    *
    *
    *
    * */

    //根据作者ID获取所有对应的作品信息
    @Override
    public List<WorksInfo> getAllWorkInfoOfAuthorId(int user_id) {
        return worksInfoDao.selectAllByUserId(user_id);
    }

    //根据用户ID获取用户加入书架的作品的作品名字
    @Override
    public List<WorksInfo> getBookshelfWorkNameByWorkID(int user_id) {
        return worksInfoDao.selectBookshelfWorkNameByWorkID(user_id);
    }

    //根据用户ID获取用户阅读历史的作品的作品名字
    @Override
    public List<WorksInfo> getReadingHistoryWorkNameByWorkID(int user_id) {
        return worksInfoDao.selectReadingHistoryWorkNameByWorkID(user_id);
    }

    @Override
    public List<WorksInfo> getWorkIdNameByUserId(int user_id) {
        return worksInfoDao.selectWorkIdNameByUserId(user_id);
    }


    //根据章节ID获取作品名字
    @Override
    public String getWorkNameByChapterId(int chapter_id) {
        return worksInfoDao.selectWorkNameByChapterId(chapter_id);
    }

    @Override
    public String getWorkIdByChapterId(int chapter_id) {
        return worksInfoDao.selectWorkIdByChapterId(chapter_id);
    }


}
