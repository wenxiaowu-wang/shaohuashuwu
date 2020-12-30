package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.AdminInfoDao;
import com.shaohuashuwu.domain.AdminInfo;
import com.shaohuashuwu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */

@Service("adminInfoService")
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Override
    public boolean insertAdminInfo(AdminInfo adminInfo) {
        return false;
    }

    @Override
    public boolean deleteAdminInfo(String admin_id) {
        boolean deleteResult = false;
        int a = adminInfoDao.deleteAdminInfo(admin_id);
        if (a !=(0)){
            deleteResult = true;
            System.out.println("deleteTest get int(true)："+a);
        }else{
            System.out.println("deleteTest get int(false)："+a);

        }

        return true;
    }

    //判断是否存在该管理员
    @Override
    public boolean isAdmainRight(String admin_id,String admin_password) {
        boolean isAdmin = false;
        AdminInfo trueAdmin = adminInfoDao.selectAdminInfoByAdminId(admin_id);
        if (trueAdmin.getAdmin_password().equals(admin_password)){
            isAdmin = true;
        }
        return isAdmin;
    }

    //修改管理员密码
    @Override
    public boolean updateAdminPassword(String admin_id,String admin_password) {
        boolean updateResult = false;
        int num = 0;
        num = adminInfoDao.updateAdminInfo(admin_id,admin_password);
        if (num!=0){
            updateResult = true;
        }
        return updateResult;
    }

    @Override
    public AdminInfo getOneByName(String name) {
        return adminInfoDao.selectAdminInfoByAdminId(name);
    }
}
