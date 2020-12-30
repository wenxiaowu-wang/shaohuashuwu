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

    //解析并装配阅读数据（包括读者年龄分布装配，读者阅读时间段分布装配，读者喜欢作品类型分布装配）
    public List<Map<String,Object>> assemblyReadingDataDistribution(List<String> type, List<Map<String,Object>> getDao){
        List<Map<String,Object>> theResult = new ArrayList<Map<String,Object>>();
        String keyName = "";
        String suffix = "";
        if (type.get(0).contains("岁")){
            keyName = "age_type";
            suffix = "点";
        }else if(type.get(0).contains("玄幻")){
            keyName = "work_main_label";
        }else{
            keyName = "date_hour";
        }
        for (String type_one:type){
            int num = 0;
            for (Map<String,Object> getDao_one:getDao){
                if (getDao_one.containsValue(type_one)){
                    Number number = (Number)getDao_one.get("reader_num");
                    num = number.intValue();
                }
            }
            Map<String,Object> map = new HashMap<String,Object>();

            map.put(keyName,type_one+suffix);
            map.put("reader_num",num);
            theResult.add(map);
        }
        return theResult;
    }

    //解析读者喜欢标签统计数据 (闲置 使用于单个解析排序，与前端所要展示数据不符)
    public List<Map<String,Object>> assemblyReaderLikeDistribution(List<String> type, List<Map<String,Object>> getDao){
        List<Map<String,Object>> theResult = new ArrayList<Map<String,Object>>();
        for (Map<String,Object> getDao_one:getDao){
            int num = 0;
            Number number = (Number)getDao_one.get("reader_num");
            num = number.intValue();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("work_main_label", getDao_one.get("work_main_label"));
            map.put("reader_num",num);
            theResult.add(map);
        }
        //补全标签
        for (String type_one:type){
            boolean has = false;
            for (Map<String,Object> getDao_one:getDao){
                if (getDao_one.containsValue(type_one)){
                    has = true;
                }
            }
            if (!has){
                //如果不包含补齐的标签
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("work_main_label",type_one);
                map.put("reader_num",0);
                theResult.add(map);
            }

        }
        return theResult;
    }

    //冒泡排序 ASC
    public Map<String, List<Map<String, Object>>> sortingReaderNumASC(List<Map<String,Object>> getDao_nan, List<Map<String,Object>> getDao_nv){
        Map<String, List<Map<String,Object>>> theResult = new HashMap<String,List<Map<String,Object>>>();
        for (int i=0;i<getDao_nan.size();i++){
            boolean flag = false;//交换标志
            Map<String,Object> get_nan_i = getDao_nan.get(i);
            Map<String,Object> get_nv_i = getDao_nv.get(i);
            Map<String,Object> max_nan_now = get_nan_i;
            Map<String,Object> max_nv_now = get_nv_i;
            int max_subscript = i;
            for (int j=i+1;j<getDao_nan.size();j++){
                Map<String,Object> get_nan_j = getDao_nan.get(j);
                Map<String,Object> get_nv_j = getDao_nv.get(j);
                //如果外层i对应元素小于其后面的元素：记录位置，交换（内循环完成一遍）
                if (((int)max_nan_now.get("reader_num")+(int)max_nv_now.get("reader_num")) < ((int)get_nan_j.get("reader_num")+(int)get_nv_j.get("reader_num"))){
                    max_nan_now  = get_nan_j;
                    max_nv_now  = get_nv_j;
                    max_subscript = j;
                    flag = true;
                    System.out.println("第j="+j+" 大于 第i="+i);
                }
//                System.out.println("第i="+i+"【比较】第j="+j+" || "+((int)max_nan_now.get("reader_num")+(int)max_nv_now.get("reader_num")) +"【比较】"+((int)get_nan_j.get("reader_num")+(int)get_nv_j.get("reader_num")));
            }
            if (flag){
                getDao_nan.set(i,max_nan_now);
                getDao_nan.set(max_subscript,get_nan_i);
                getDao_nv.set(i,max_nv_now);
                getDao_nv.set(max_subscript,get_nv_i);
                flag = false;
            }
        }
        theResult.put("男",getDao_nan);
        theResult.put("女",getDao_nv);
        return theResult;
    }
}
