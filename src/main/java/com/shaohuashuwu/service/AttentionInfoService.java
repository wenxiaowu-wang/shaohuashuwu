package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.domain.vo.UserandWorksInfoVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface AttentionInfoService {


    //    查询关注数量
    public int selectCountAttentionNum(int user_id);
}
