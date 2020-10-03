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

}
