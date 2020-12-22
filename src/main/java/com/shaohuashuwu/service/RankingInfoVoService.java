package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import java.util.List;

public interface RankingInfoVoService {

    //获取排行信息
    // 功能点：获取排行信息
    public List<RankingInfoVo> getRankingInfo(RankingInputInfoVo rankingInputInfoVo);


    //排行榜
    public List<RankingInfoVo> getListInfo(RankingInputInfoVo rankingInputInfoVo);

    //获取排行榜信息
    public List<RankingInfoVo> getRankingListInfo(RankingInputInfoVo rankingInputInfoVo);

}
