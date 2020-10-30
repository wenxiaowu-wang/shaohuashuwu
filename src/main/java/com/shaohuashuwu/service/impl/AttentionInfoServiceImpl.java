package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.AttentionInfoDao;
import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.service.AttentionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("attentionInfoService")
public class AttentionInfoServiceImpl implements AttentionInfoService {

    @Autowired
    public AttentionInfoDao attentionInfoDao;


    //判断是否已经关注该作者
    @Override
    public boolean isAlreadyAttention(AttentionInfo attentionInfo) {
        boolean isAlreadyAttention = false;
        if(attentionInfoDao.selectAttentionInfo(attentionInfo)!=null){
            isAlreadyAttention = true;
        }
        return isAlreadyAttention;
    }

    //关注该作者（添加关注信息）
    @Override
    public boolean addAttentionInfo(AttentionInfo attentionInfo) {
        boolean addResult = false;
        if (attentionInfoDao.insertAttentionInfo(attentionInfo)!=(0)){
            addResult = true;
        }
        return addResult;
    }

    //获取所有关注作者的信息
    @Override
    public List<AttentionInfoVo> getAttentionAuthorInfo(int user_id) {
        List<AttentionInfoVo> getResult = new ArrayList<AttentionInfoVo>();
        List<UserInfo> userInfoList = attentionInfoDao.selectAttentionUserInfoByUserId(user_id);
        if (userInfoList.size()!=0){
            for (int i=0;i<userInfoList.size();i++){
                getResult.add(userInfoList.get(i).toAttentionInfoVo());    //装配关注信息值对象
            }
        }
        return getResult;
    }

    //获取该用户所有粉丝的值对象信息
    @Override
    public List<AttentionInfoVo> getFansInfo(int user_id) {
        List<AttentionInfoVo> getResult = new ArrayList<AttentionInfoVo>();
        List<UserInfo> userInfoList = attentionInfoDao.selectWereAttentionUserInfoByAuthorId(user_id);
        if (userInfoList.size()!=0){
            for (int i=0;i<userInfoList.size();i++){
                getResult.add(userInfoList.get(i).toAttentionInfoVo());    //装配关注信息值对象
            }
        }
        return getResult;
    }

    //获取与该用户互相关注的用户值对象信息
    @Override
    public List<AttentionInfoVo> getEachAttentionUserInfo(int user_id) {
        List<AttentionInfoVo> getResult = new ArrayList<AttentionInfoVo>();
        List<UserInfo> userInfoList = attentionInfoDao.selectEachAttentionUserInfoByUserId(user_id);
        if (userInfoList.size()!=0){
            for (int i=0;i<userInfoList.size();i++){
                getResult.add(userInfoList.get(i).toAttentionInfoVo());    //装配关注信息值对象
            }
        }
        return getResult;
    }

    //删除一条关注信息
    @Override
    public boolean deleteAttentionInfo(AttentionInfo attentionInfo) {
        boolean deleteResult = false;
        if (attentionInfoDao.deleteAttentionInfo(attentionInfo)!=(0)){
            deleteResult = true;
        }
        return deleteResult;
    }
}
