package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface AttentionInfoService {

    //判断是否已经关注该作者
    public boolean isAlreadyAttention(AttentionInfo attentionInfo);

    //关注该作者（添加关注信息）
    public boolean addAttentionInfo(AttentionInfo attentionInfo);

    //获取所有关注作者的信息
    public List<AttentionInfoVo> getAttentionAuthorInfo(int user_id);

    //删除一条关注信息
    public boolean deleteAttentionInfo(AttentionInfo attentionInfo);
}
