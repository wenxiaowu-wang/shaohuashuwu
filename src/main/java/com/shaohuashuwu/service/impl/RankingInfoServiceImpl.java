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


    private List<RankingInfoVo> rankingInfoVoList;
    private List<RankingInfoVo> rankingInfoVos;


    /**
     * 获取排行信息
     * 功能点：获取排行信息
     * @param rankingInputInfoVo
     * @return
     */
    @Override
    public List<RankingInfoVo> getRankingInfo(RankingInputInfoVo rankingInputInfoVo) {

        //模糊查询的时间类型
        if(rankingInputInfoVo.getTime_type() == 2){
            String monthTime = thisCurrentTime.currentMonthTime();
            rankingInputInfoVo.setTransaction_time(monthTime);
            System.out.println("获取月排行类型详情"+rankingInputInfoVo);
        }
        else if(rankingInputInfoVo.getTime_type() == 3){
            String dayTime = thisCurrentTime.currentDayTime();
            rankingInputInfoVo.setTransaction_time(dayTime);
            System.out.println("获取日排行类型详情"+rankingInputInfoVo);
        }

        //收藏榜
        if(rankingInputInfoVo.getTransaction_type() == 4){
            //获取10的榜单信息
            if(rankingInputInfoVo.getGetneednum() == 10){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    System.out.println("10总："+rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo).subList(0,10));
                    return rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo).subList(0,10);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){

                    rankingInfoVoList = rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        System.out.println("10月："+rankingInfoVoList.subList(0,10));
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        System.out.println("10月："+rankingInfoVoList.subList(0,10));
                        return rankingInfoVoList.subList(0,10);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);

                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        System.out.println("10月："+rankingInfoVoList.subList(0,10));
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        System.out.println("10月："+rankingInfoVoList.subList(0,10));
                        return rankingInfoVoList.subList(0,10);
                    }
                }
            }
            //获取榜单前20
            else if(rankingInputInfoVo.getGetneednum() == 20){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo).subList(0,20);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        System.out.println("---"+rankingInfoVoList.subList(0,20));
                        return rankingInfoVoList.subList(0,20);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
            }

        }
        //推荐榜
        else if(rankingInputInfoVo.getTransaction_type() == 3){
            //获取10的榜单信息
            if(rankingInputInfoVo.getGetneednum() == 10){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo).subList(0,10);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        return rankingInfoVoList.subList(0,10);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        return rankingInfoVoList.subList(0,10);
                    }
                }
            }
            //获取榜单前20
            else if(rankingInputInfoVo.getGetneednum() == 20){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo).subList(0,20);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
            }

        }
        //订阅榜
        else if(rankingInputInfoVo.getTransaction_type() == 2){
            //获取10的榜单信息
            if(rankingInputInfoVo.getGetneednum() == 10){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo).subList(0,10);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        return rankingInfoVoList.subList(0,10);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        System.out.println("订阅榜+++++++++++++++++++"+rankingInfoVoList.subList(0,10));
                        return rankingInfoVoList.subList(0,10);
                    }
                }
            }
            //获取榜单前20
            else if(rankingInputInfoVo.getGetneednum() == 20){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo).subList(0,20);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
            }
        }
        //打赏榜
        else if(rankingInputInfoVo.getTransaction_type() == 1){
            //获取10的榜单信息
            if(rankingInputInfoVo.getGetneednum() == 10){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo).subList(0,10);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        return rankingInfoVoList.subList(0,10);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<10;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,10);

                    }
                    else {
                        return rankingInfoVoList.subList(0,10);
                    }
                }
            }
            //获取榜单前20
            else if(rankingInputInfoVo.getGetneednum() == 20){
                //总
                if(rankingInputInfoVo.getTime_type() == 1){
                    return rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo).subList(0,20);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<21){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<20;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,20);

                    }
                    else {
                        return rankingInfoVoList.subList(0,20);
                    }
                }
            }
        }



        return null;
    }



}
