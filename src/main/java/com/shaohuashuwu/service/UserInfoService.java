package com.shaohuashuwu.service;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface UserInfoService {

    //更新用户金豆数（加减都有可能）
    public boolean updateUserGoldBean(int user_id,int updateNum);

    //获取该用户当前金豆数
    public int getGoldBeanNumOfUser(int user_id);

    //获取该用户当前推荐票数
    public int getTicketNumOfUser(int user_id);

    //判断该用户是否已经是一位作者
    public boolean isAlreadyBecameAuthor(int user_id);

    //更新该用户(作者)的二级密码
    public boolean updateAuthorDoublePassword(int user_id,String double_password);

    public boolean isDoublePassword(int user_id,String pass);




}
