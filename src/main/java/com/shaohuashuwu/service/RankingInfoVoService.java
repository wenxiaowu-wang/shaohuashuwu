package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;

import java.util.List;

public interface RankingInfoVoService {

    //排行榜
    public List<RankingInfoVo> selectListInfo(RankingInputInfoVo rankingInputInfoVo);

    //前十排行榜
    public List<RankingInfoVo> selectRankingListInfo(RankingInputInfoVo rankingInputInfoVo);

}
