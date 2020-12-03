package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;

import java.util.List;

public interface RankingInfoVoService {

    //排行榜
    public List<RankingInfoVo> getListInfo(RankingInputInfoVo rankingInputInfoVo);

    //获取排行榜信息
    public List<RankingInfoVo> getRankingListInfo(RankingInputInfoVo rankingInputInfoVo);

}
