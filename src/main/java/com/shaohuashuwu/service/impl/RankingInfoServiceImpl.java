package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.RankingInfoVoDao;
import com.shaohuashuwu.dao.TransactionInfoDao;
import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import com.shaohuashuwu.service.RankingInfoVoService;
import com.shaohuashuwu.util.ThisCurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rankingInfoService")
public class RankingInfoServiceImpl implements RankingInfoVoService {

    //工具类
    @Autowired
    private ThisCurrentTime thisCurrentTime;

    @Autowired
    TransactionInfoDao transactionInfoDao;

    @Autowired
    RankingInfoVoDao rankingInfoVoDao;




    /**
     * 获取排行信息
     * @param rankingInputInfoVo
     * @return
     */
    @Override
    public List<RankingInfoVo> getListInfo(RankingInputInfoVo rankingInputInfoVo) {

        //1.获取时间，为1时，重新将时间设置为总榜，为2时，获取月份榜单，为3时，获取日榜
        System.out.println("截取时间"+rankingInputInfoVo.getTransaction_time());
        if(rankingInputInfoVo.getTransaction_time().equals("1")){
            System.out.println("走1");
            rankingInputInfoVo.setTransaction_time(null);
        }
        else if(rankingInputInfoVo.getTransaction_time().equals("2")){
            System.out.println("走2");
            String monthTime = thisCurrentTime.currentMonthTime();
            System.out.println("时间："+monthTime);
            rankingInputInfoVo.setTransaction_time(monthTime);
        }
        else if(rankingInputInfoVo.getTransaction_time().equals("3")){
            System.out.println("走3");
            String dayTime = thisCurrentTime.currentDayTime();
            System.out.println("时间："+dayTime);
            rankingInputInfoVo.setTransaction_time(dayTime);
        }

        List list = rankingInfoVoDao.selectListInfo(rankingInputInfoVo);

        return list;
    }


    /**
     * 分页获取排行榜信息
     * @param rankingInputInfoVo
     * @return
     */
    /**
     * 未完善，当获取信息没有10条时，显示内容
     * @param rankingInputInfoVo
     * @return
     */
    @Override
    public List<RankingInfoVo> getRankingListInfo(RankingInputInfoVo rankingInputInfoVo) {

        List<RankingInfoVo> rankingInfoVoList = getListInfo(rankingInputInfoVo);

        //获取ranking长度，如果长度小于10，获取总排行榜，推荐票数设置为0
//        if(rankingInfoVoList.size()<10){
//            rankingInputInfoVo.setTransaction_time(null);
//            List<RankingInfoVo> buchongrankingInfoVoList = getRankingListInfo(rankingInputInfoVo);
//
//            rankingInfoVoList.addAll(buchongrankingInfoVoList);
//        }

        int listnum = 10;
        if(rankingInfoVoList.size() >= 10){
            listnum = 10;
        }
        else {
            listnum = rankingInfoVoList.size();
        }


        return rankingInfoVoList.subList(0, listnum);
    }


}
