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
                    System.out.println("10总："+rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo).subList(0,2));
                    return rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo).subList(0,2);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){

                    rankingInfoVoList = rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        System.out.println("10月："+rankingInfoVoList.subList(0,2));
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        System.out.println("10月："+rankingInfoVoList.subList(0,2));
                        return rankingInfoVoList.subList(0,2);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectCollectionRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);

                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        System.out.println("10月："+rankingInfoVoList.subList(0,2));
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        System.out.println("10月："+rankingInfoVoList.subList(0,2));
                        return rankingInfoVoList.subList(0,2);
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
                    return rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo).subList(0,2);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        return rankingInfoVoList.subList(0,2);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        return rankingInfoVoList.subList(0,2);
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
                    return rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo).subList(0,2);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        return rankingInfoVoList.subList(0,2);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectsubscribeRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        return rankingInfoVoList.subList(0,2);
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
                    return rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo).subList(0,2);
                }
                //月
                else if (rankingInputInfoVo.getTime_type() == 2){
                    rankingInfoVoList = rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        return rankingInfoVoList.subList(0,2);
                    }
                }
                //日
                else if(rankingInputInfoVo.getTime_type() == 3){
                    rankingInfoVoList = rankingInfoVoDao.selectrewardRankingInfo(rankingInputInfoVo);
                    if (rankingInfoVoList.size()<3){
                        rankingInputInfoVo.setTime_type(1);
                        rankingInputInfoVo.setTransaction_time(null);
                        rankingInfoVos = getRankingInfo(rankingInputInfoVo);
                        for(int i = 0;i<2;i++){
                            rankingInfoVos.get(i).setSumnum(0);
                        }
                        rankingInfoVoList.addAll(rankingInfoVos);
                        return rankingInfoVoList.subList(0,2);

                    }
                    else {
                        return rankingInfoVoList.subList(0,2);
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
















    /**********未修改 */




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

        List list = rankingInfoVoDao.selectrecommendRankingInfo(rankingInputInfoVo);

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
