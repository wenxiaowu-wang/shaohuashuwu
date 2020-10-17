package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.UserInfoDao;
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


    /**
     * 在其它地方直接调用UserInfoDao了
     * @param user_id
     * @param updateNum
     * @return
     */
    //更新用户金豆数（加减都有可能）
    @Override
    public boolean updateUserGoldBean(int user_id, int updateNum) {
        boolean updateResult = false;
        if (userInfoDao.updateGoldBeanNumByUserId(user_id,updateNum)!=(0)){
            updateResult = true;
        }
        return updateResult;
    }

    //获取该用户当前金豆数
    @Override
    public int getGoldBeanNumOfUser(int user_id) {
        return userInfoDao.selectUserInfoByUserId(user_id).getGold_bean_num();
    }

    //获取该用户当前推荐票数
    @Override
    public int getTicketNumOfUser(int user_id) {
        return userInfoDao.selectUserInfoByUserId(user_id).getTicket_num();
    }

    //判断该用户是否已经是一位作者
    @Override
    public boolean isAlreadyBecameAuthor(int user_id) {
        boolean isAlreadyBecame = false;
        if (userInfoDao.selectUserInfoByUserId(user_id).getDouble_password() != null){
            isAlreadyBecame = true;
        }
        return isAlreadyBecame;
    }

    //更新该用户(作者)的二级密码
    @Override
    public boolean updateAuthorDoublePassword(int user_id, String double_password) {
        boolean updateResult = false;
        if (userInfoDao.updateDoublePasswordByUserId(user_id,double_password)!=(0)){
            updateResult = true;
        }
        return updateResult;
    }
}
