package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ReadSettingInfoDao;
import com.shaohuashuwu.domain.ReadSettingInfo;
import com.shaohuashuwu.service.ReadSettingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("readSettingInfoService")
public class ReadSettingInfoServiceImpl implements ReadSettingInfoService {

    @Autowired
    public ReadSettingInfoDao readSettingInfoDao;


    public ReadSettingInfo readSettingInfo;

    /**
     * 获取设置信息
     * 功能点：用阅读小说界面设置获取，
     * @param user_id
     * @return
     */
    @Override
    public ReadSettingInfo getReadSettinginfo(int user_id) {

        //1.先判断是否存在
        int isReadingSetting = readSettingInfoDao.selectReadSettinginfoNumByuser_id(user_id);

        //2.如果存在就获取信息，不存在就进行设置，并保存
        if (isReadingSetting == 0){
            readSettingInfo = new ReadSettingInfo(null,user_id,1,1,24);
            int insertresult = readSettingInfoDao.insertreadSettingInfo(readSettingInfo);
            return readSettingInfo;
        }
        else{
            readSettingInfo = readSettingInfoDao.selectReadSettinginfoByuser_id(user_id);
            return readSettingInfo;
        }
    }

    /**
     * 通过用户id修改设置信息
     * 功能点：阅读小说界面修改设置，
     * @param readSettingInfo
     * @return
     */
    @Override
    public int updateReadSettingInfoByid(ReadSettingInfo readSettingInfo) {
        int updataResult = readSettingInfoDao.updateReadSettingInfoByid(readSettingInfo);
        return updataResult;
    }


}
