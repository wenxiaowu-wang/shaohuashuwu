package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.AdminInfo;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
public interface AdminInfoService {

    //添加一个管理员信息
    public boolean insertAdminInfo(AdminInfo adminInfo);

    //删除一个管理员信息
    public boolean deleteAdminInfo(String admin_id);

    //判断是否是该管理员
    public boolean isAdmainRight(AdminInfo adminInfo);

    //更新管理员密码,该adminInfo为修改后的AdminInfo
    public boolean updateAdminPassword(AdminInfo adminInfo);
}
