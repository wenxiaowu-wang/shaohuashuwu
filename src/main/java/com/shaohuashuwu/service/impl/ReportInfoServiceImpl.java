package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.domain.ReportInfo;
import com.shaohuashuwu.service.ReportInfoService;
import com.shaohuashuwu.util.SensitivewordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("reportInfoService")
public class ReportInfoServiceImpl implements ReportInfoService {

    @Autowired
    public ReportInfoDao reportInfoDao;

    @Autowired
    public ChapterInfoDao chapterInfoDao;

    @Autowired
    public SensitivewordFilter sensitivewordFilter;

    @Autowired
    public NoticeInfoDao noticeInfoDao;

    @Autowired
    public NoticeStateInfoDao noticeStateInfoDao;

    @Autowired
    public WorksInfoDao worksInfoDao;

    ReportInfo reportInfo;

    /**
     * 下架上架时的做欧品设置，和章节状态修改
     * 功能点：处理作品信息修改处理信息，
     * @param
     * @return
     */
    @Override
    public int updateReportInfoByReport_id(Integer report_id,Integer handle_state) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Timestamp timestamp=Timestamp.valueOf(date);
        //驳回申请
        if (handle_state == 2){
            reportInfo = new ReportInfo(report_id,timestamp,handle_state);
            return reportInfoDao.updateReportInfoByReport_id(reportInfo);
        }
        //同意申请处理,下架章节
        else if(handle_state == 3){
            reportInfo = new ReportInfo(report_id,timestamp,handle_state);
            int updateResult = reportInfoDao.updateReportInfoByReport_id(reportInfo);
            int chapter_id = reportInfoDao.selectChapter_idByReport_id(report_id);
            chapterInfoDao.updateChapter_stateByChapter_id(chapter_id);
            return updateResult;
        }
        //撤销吹结果，上架章节
        else if(handle_state == 4){
            reportInfo = new ReportInfo(report_id,timestamp,2);
            int updateResult = reportInfoDao.updateReportInfoByReport_id(reportInfo);
            int chapter_id = reportInfoDao.selectChapter_idByReport_id(report_id);
            chapterInfoDao.updateChapter_stateByChapter_id2(chapter_id);
            return updateResult;

        }
        return 0;
    }

    /**
     * 阿斌
     */
    //保存一条举报信息
    @Override
    public boolean addReportInfo(ReportInfo reportInfo) {
        boolean addResult = false;
        if (reportInfoDao.insertReportInfo(reportInfo)!=(0)){
            addResult = true;
        }
        return addResult;
    }

    //举报章节信息
    @Override
    public int reportChapter(int user_id, int chapter_id, int work_id,int reason) {
        int result = 0;
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setUser_id(user_id);
        reportInfo.setChapter_id(chapter_id);
        reportInfo.setReport_reason(reason);// 保留一个举报原因
        reportInfo.setReport_time(timestamp);

        //将获取到的章节内容放到关键词检测工具类中分析敏感词个数
        int sensitiveWordNum = sensitivewordFilter.sensitiveWordNum(chapterInfoDao.selectChapterContentByChapterId(chapter_id));
        if (sensitiveWordNum > 2){
            //举报成功
            //下架该章节，通知该作者，保存举报信息
            chapterInfoDao.updateChapter_stateByChapter_id(chapter_id);
            reportInfo.setHandle_state(3);  //3 表示已经将对应章节下架
            if (reportInfoDao.insertReportInfo(reportInfo) != 0){

                //获取章节标题和对应作者ID
                String title = chapterInfoDao.selectChapterTitleByChapterId(chapter_id);
                int send_to = worksInfoDao.selectAuthorIdByWorkId(work_id);
                String str_reason = "";
                switch(reason){
                    case 1:{
                        str_reason = "政治敏感";
                        break;
                    }case 2:{
                        str_reason = "淫秽色情";
                        break;
                    }case 3:{
                        str_reason = "欺诈广告";
                        break;
                    }case 4:{
                        str_reason = "暴力血腥";
                        break;
                    }case 5:{
                        str_reason = "低俗恶趣";
                        break;
                    }case 6:{
                        str_reason = "侵权抄袭";
                        break;
                    }case 7:{
                        str_reason = "不实谣言";
                        break;
                    }case 8:{
                        str_reason = "宣传赌博";
                        break;
                    }case 9:{
                        str_reason = "人身攻击";
                        break;
                    } default:break;
                }
                NoticeInfo noticeInfo = new NoticeInfo();
                noticeInfo.setSend_by(0);   //0 代表系统用户，即韶华书屋系统
                noticeInfo.setSend_to(send_to);
                noticeInfo.setNotice_type(1);//设置该消息类型为系统通知
                noticeInfo.setNotice_content("您的‘"+title+"'章节涉嫌"+str_reason+"，目前已下架！");
                noticeInfo.setNotice_title("您的‘"+title+"'章节已下架！");
                noticeInfo.setSend_time(timestamp);
                noticeInfo.setNotice_tip(1);//设置该消息未读

                if (noticeInfoDao.insertOneNoticeInfo(noticeInfo) != 0){
                    //添加未读消息到notice_info成功
                    //这里怎么处理事务的回滚？待解答
                    NoticeInfo noticeInfo1 = noticeInfoDao.selectRecentTimeNoticeInfoBySendByToAndType(noticeInfo.getSend_by(),noticeInfo.getSend_to(),noticeInfo.getNotice_type());
                    if (noticeInfo1 != null){
                        if (noticeStateInfoDao.insertOrdinaryNoticeStateInfo(noticeInfo1.getNotice_id(), noticeInfo1.getSend_by(), noticeInfo1.getSend_to()) != 0){
                            //插入消息状态信息表中成功
                            result =  1;   //1 表示举报成功
                        }
                    }
                }
            }

        }else if (sensitiveWordNum == 2){
            //系统无法判断
            //保存举报信息，交由管理员处理
            reportInfo.setHandle_state(1);  //1 表示等待处理举报信息
            if (reportInfoDao.insertReportInfo(reportInfo) != 0){
                result =  2;   //2 表示系统无法判断
            }
        }
        return result;
    }
}
