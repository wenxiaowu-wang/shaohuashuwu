package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.TransactionInfoDao;
import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import com.shaohuashuwu.service.RankingInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rankingInfoService")
public class RankingInfoServiceImpl implements RankingInfoVoService {

    @Autowired
    TransactionInfoDao transactionInfoDao;


    @Override
    public List<RankingInfoVo> selectListInfo(RankingInputInfoVo rankingInputInfoVo) {

        List list = transactionInfoDao.selectListInfo(rankingInputInfoVo);
        System.out.println(transactionInfoDao.selectListInfo(rankingInputInfoVo));
        return list;
    }


    @Override
    public List<RankingInfoVo> selectRankingListInfo(RankingInputInfoVo rankingInputInfoVo ) {

        List<RankingInfoVo> rankingInfoVoList = selectListInfo(rankingInputInfoVo);

        return rankingInfoVoList.subList(0, 10);
    }


}
