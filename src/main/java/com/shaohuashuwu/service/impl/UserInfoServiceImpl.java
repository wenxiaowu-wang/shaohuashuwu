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



    /**
     * 获取网站注册人数
     * @return
     */
    @Override
    public int getUserNum() {
        return userInfoDao.selectUserNum();
    }

    /**
     * 获取用户登录信息
     * @param user_id
     * @return
     */
    @Override
    public UserInfo getUserLoginInfo(int user_id) {
        return userInfoDao.selectUserLogiInfoByuser_id(user_id);
    }

    /**
     * 根据作品id获取用户信息
     * @param work_id
     * @return
     */
    @Override
    public UserInfo getUserInfoByWork_id(int work_id) {
        return userInfoDao.selectUserInfoByWork_id(work_id);
    }

    /**
     * 根据章节id获取作者信息
     * @param chapter_id
     * @return
     */
    @Override
    public UserInfo getauthorInfoBychapterid(int chapter_id) {

        return userInfoDao.selectauthorInfoByChapter_id(chapter_id);
    }
























    /********以下未修改**********/















    /**
     * 依据用户名获取用户id
     * @param user_name
     * @return
     */
    @Override
    public int getuser_idByusername(String user_name) {


        return userInfoDao.selectuser_idByusername(user_name);
    }





}
