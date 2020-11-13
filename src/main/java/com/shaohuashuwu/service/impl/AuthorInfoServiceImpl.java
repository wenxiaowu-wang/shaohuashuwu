package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.AttentionInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AuthorInfoVo;
import com.shaohuashuwu.service.AuthorInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorInfoServiceImpl")
public class AuthorInfoServiceImpl implements AuthorInfoVoService {

    @Autowired
    public AttentionInfoServiceImpl attentionInfoService;
    @Autowired
    public WorksInfoDao worksInfoDao;
    @Autowired
    public UserInfoDao userInfoDao;

    public AttentionInfo attentionInfo;
    public UserInfo userInfo;
    public AuthorInfoVo authorInfoVo;

    //获取作者信息
    @Override
    public AuthorInfoVo selectAuthorInfoVo(int user_id) {

//        获取关注数量
        int attentionnum = 0;
        try{
            attentionnum = attentionInfoService.selectCountAttentionNum(user_id);
        }catch (Exception e){
            System.out.println("关注空");
        }

        System.out.println("关注数量"+attentionnum);

//        获取总字数
        int allwork_word_num = 0;
        try {
            allwork_word_num = worksInfoDao.selectallWorknum(user_id);
        }catch (Exception e){
            System.out.println("总字数空");
        }
        System.out.println("作品总字数："+allwork_word_num);
//        用户信息

        try {
            userInfo = userInfoDao.selectuserInfoByuserid(user_id);
        }catch (Exception e){
            System.out.println("用户信息为空");
        }

        System.out.println("用户信息"+userInfo);

//        输出作者信息
        authorInfoVo = new AuthorInfoVo(userInfo.getUser_id(),userInfo.getUser_name(),userInfo.getHead_portrait(),attentionnum,allwork_word_num);

//        System.out.println("关注数量"+authorInfoVo.getAttention_num()+
//        "作品总字数"+authorInfoVo.getAllwork_word_num()+
//        "用户信息"+authorInfoVo.getUser_id()+
//        authorInfoVo.getUser_name()+
//        authorInfoVo.getHead_portrait());
//        System.out.println("作者信息"+authorInfoVo);
        return authorInfoVo;
    }
}
