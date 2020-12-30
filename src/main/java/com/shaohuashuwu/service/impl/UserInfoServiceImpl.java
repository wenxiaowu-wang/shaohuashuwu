package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    private UserInfo userInfo;

    /**
     * 获取网站注册人数
     * 功能点：用户界面上的获取的用户数量
     * @return
     */
    @Override
    public int getUserNum() {
        return userInfoDao.selectUserNum();
    }

    /**
     * 获取用户登录信息
     * 功能点：用户界面上的登录的用户信息
     * @param user_id
     * @return
     */
    @Override
    public UserInfo getUserLoginInfo(int user_id) {
        return userInfoDao.selectUserLogiInfoByuser_id(user_id);
    }

    /**
     * 根据作品id获取用户信息
     * 功能点：作品详情时获取作者信息，
     * @param work_id
     * @return
     */
    @Override
    public UserInfo getUserInfoByWork_id(int work_id) {
        return userInfoDao.selectUserInfoByWork_id(work_id);
    }

    /**
     * 根据章节id获取作者信息
     * 功能点：阅读小说界面获取作者信息
     * @param chapter_id
     * @return
     */
    @Override
    public UserInfo getauthorInfoBychapterid(int chapter_id) {

        return userInfoDao.selectauthorInfoByChapter_id(chapter_id);
    }





    /**
     *  郝振威
     */


    @Override
    //登录部分 判断用户手机号、密码是否输入正确
    public int checkUserInfo(String phone_number, String password) {

        //1.先判断是否存在，如果不存在，直接返回false
        //2.如果存在判断密码是否正确，不正确返回false
        int ishave = 0;
        ishave = userInfoDao.isUserInfohaveByphone_number(phone_number);
        if (ishave == 0) {
            return 0;
        } else {
            //查询出用户密码
            String pwd = userInfoDao.selectUserPasswordByPhoneNumber(phone_number);
            if (pwd.equals(password)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    //判断手机号是否已存在（注册）
    @Override
    public int isUserInfohaveByphone_number(String phone_number) {
        int isExist = userInfoDao.isUserInfohaveByphone_number(phone_number);
//        System.out.println("存在返回1" + isExist);
        return isExist;
    }

    //注册
    @Override
    public Boolean insertUserInfo2(String phone_number, String password, String smsCode,String user_name,String head_portrait,String gender,String area,String birthday,int gold_bean_num,int gold_coin_num,int ticket_num) {
        System.out.println("注册插入数据");

        //将账号密码存入数据库
        Boolean insertResult = false;

        // System.out.println(""+phone_number+""+password+""+smsCode+""+user_name+""+head_portrait+""+gender+""+area+""+birthday);
        userInfo = new UserInfo(phone_number,password,user_name,head_portrait,gender,area,birthday,gold_bean_num,gold_coin_num,ticket_num);
        int exist = 0;
        //判断用户是否存在
        exist = userInfoDao.isUserInfohaveByphone_number(phone_number);
        // 证明用户未注册
        if (exist == 0) {
            int before = userInfoDao.selectUserInfoAllCount(phone_number);
            //System.out.println("注册之前总用户数：" + before);
            userInfoDao.insertUserInfoByAccount(userInfo);
            int after = userInfoDao.selectUserInfoAllCount(phone_number);
            // System.out.println("注册之后总用户数：" + after);
            if (after > before) {
                insertResult = true;
            }
        }
        return insertResult;
    }

    /*
        用户更改密码
     */
    @Override
    public Boolean updateUserPwd(String phone_number, String password) {

        boolean result = false;
        userInfo = new UserInfo(phone_number, password);
        if (userInfoDao.updateUserPwd(userInfo) != 0) {
            result = true;
        }
        System.out.println("updatePassword进入service层："+result);
        return result;
    }


    //根据手机号 获取用户ID 用户昵称 头像 性别 地区 生日
    @Override
    public Map<String, Object> getUserIdNameAndHeaderByPhone(String phone_number) {
        userInfo = new UserInfo();
        UserInfo userInfo = userInfoDao.selectUserInfoByPhoneNumber(phone_number);
        System.out.println("进入 Service getUserIdNameAndHeaderByPhone");
        Map<String, Object> getResult = new HashMap<String, Object>();
        if (userInfo != null) {
            getResult.put("user_id", userInfo.getUser_id());
            getResult.put("user_name", userInfo.getUser_name());
            getResult.put("head_portrait", userInfo.getHead_portrait());
            getResult.put("gender",userInfo.getGender());
            getResult.put("birthday",userInfo.getBirthday());
            getResult.put("area",userInfo.getArea());
            //测试
            System.out.println(userInfo.getUser_id());
            System.out.println(userInfo.getArea());
        }
        return getResult;
    }


    //更新用户资料
    @Override
    public Boolean updateUserData(int user_id, String user_name,  String gender,  String birthday,String area) {

        boolean updateResult = false;

        userInfo = new UserInfo(user_id,user_name, gender,birthday,area);

        if (userInfoDao.updatePersonalDataByID(userInfo) != 0) {
            updateResult = true;
        }
        System.out.println("updateUserData进入service层："+updateResult);
        return updateResult;

    }

    //判断用户名是否存在
    @Override
    public Boolean isUserNameHaveByUsername(String user_name,int user_id) {

        boolean isExist = userInfoDao.isUserNameHaveByUsername(user_name,user_id);

        return isExist;
    }

    @Override
    public int getAuthorIDByChapterID(int chapter_id) {
        return userInfoDao.selectAuthorIDByChapterID(chapter_id);
    }



    /**
     * 阿斌
     */
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

    //判断二级密码是否正确
    @Override
    public boolean isDoublePassword(int user_id, String pass) {
        boolean theResult = false;
        String getPass = userInfoDao.selectDoublePasswordById(user_id);
        if (getPass.equals(pass)){
            theResult = true;
        }
        return theResult;
    }
}
