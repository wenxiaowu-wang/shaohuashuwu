package com.shaohuashuwu.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.utils
 * 作者:王洪斌
 * 日期:2020/11/25
 * 项目:shaohuashuwu
 * 描述:
 */
public class StatisticalHelp {

    public StatisticalHelp(){

    }

    public List<Map<String,Object>> assemblySubscriptionStatistics(List<String> days,List<Map<String,Object>> getDao){
        List<Map<String,Object>> theResult = new ArrayList<Map<String,Object>>();
        //给结果赋值并放入结果列表
        boolean isHas = false;
        for(int i=0;i<days.size();i++){
            isHas = false;
            Map<String,Object> mapResult = new HashMap<String,Object>();
            String timeDate = days.get(i);
            mapResult.put("date_day",timeDate);
            for (Map<String,Object> mapData : getDao){
                if(mapData.containsValue(timeDate)){
                    isHas = true;
                    //获取到的统计数据里包含该日期的对应消息
                    BigDecimal num = (BigDecimal) mapData.get("subscription_quantity");
                    int subscriptionNum = num.intValue();

                    //给返回结果赋值
                    mapResult.put("subscription_quantity",subscriptionNum);

                    break;
                }
            }
            if (!isHas){
                //若不存在该项日期
                //给返回结果赋值
                mapResult.put("subscription_quantity",0);
            }
            //添加到结果列表
            theResult.add(mapResult);
        }
        return theResult;
    }
}
