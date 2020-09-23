package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterInfoDao;
import com.shaohuashuwu.dao.NoticeInfoDao;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.service.ChapterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public NoticeInfoDao noticeInfoDao;

    //智能判断举报是否成功
    @Override
    public int reportDetectionChapter(int chapter_id, int report_reason) {
        int reportResult = 0;
        String chapterContent = chapterInfoDao.selectChapterContentByChapterId(chapter_id);
        /*智能判断举报是否成功，0成功，1失败，2智能无法判断，等待管理员审核*/

        return report_reason;
    }

    //下架对应章节并发送通知给对应作者
    @Override
    public boolean dealViolationChapterAndSendNotice(NoticeInfo noticeInfo,Integer chapter_id) {
        boolean dealResult = false;
        if(chapterInfoDao.updateChapterStateByChapterId(chapter_id)!=(0)){
            if (noticeInfoDao.insertOneNoticeInfo(noticeInfo)!=(0)){
                dealResult = true;
            }
        }
        return dealResult;
    }
}
