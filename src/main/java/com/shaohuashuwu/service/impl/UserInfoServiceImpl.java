package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.dao.ReadSettingInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    public UserInfoDao userInfoDao;

    @Autowired
    public ChapterPostInfoDao chapterPostInfoDao;



    @Override
    public UserInfo selectUserInfoByChapter_id(int chapter_id) {

        return chapterPostInfoDao.selectUserInfoByChapter_id(chapter_id);
    }
}
