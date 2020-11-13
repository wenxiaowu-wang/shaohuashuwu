package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.AttentionInfoDao;
import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.domain.vo.AuthorInfoVo;
import com.shaohuashuwu.service.AttentionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




    @Override
    public int selectCountAttentionNum(int user_id) {
        int attentionNum = attentionInfoDao.selectCountAttentionNum(user_id);

        System.out.println("输出关注数量"+attentionNum);
        return attentionNum;
    }
}
