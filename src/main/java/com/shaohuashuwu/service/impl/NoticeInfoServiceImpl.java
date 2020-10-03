package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.NoticeInfoDao;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("noticeInfoService")
public class NoticeInfoServiceImpl implements NoticeInfoService {

    @Autowired
    public NoticeInfoDao noticeInfoDao;

}
