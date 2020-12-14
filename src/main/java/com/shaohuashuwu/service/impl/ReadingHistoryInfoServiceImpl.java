package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ReadingHistoryInfoDao;
import com.shaohuashuwu.service.ReadingHistoryInfoService;
import com.shaohuashuwu.utils.StatisticalHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/11/28
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("readerHistoryInfoService")
public class ReadingHistoryInfoServiceImpl implements ReadingHistoryInfoService {

    @Autowired
    public ReadingHistoryInfoDao readingHistoryInfoDao;


    @Override
    public Map<String, List<Map<String, Object>>> getReaderAgeDistributionByWorkId(List<String> type, int work_id) {
        Map<String, List<Map<String,Object>>> theResult = new HashMap<String,List<Map<String,Object>>>();
        List<Map<String,Object>> getDao_nan = readingHistoryInfoDao.selectReaderAgeDistributionByWorkIdAndGender(work_id,"男");
        List<Map<String,Object>> getDao_nv = readingHistoryInfoDao.selectReaderAgeDistributionByWorkIdAndGender(work_id,"女");

        //使用StatisticalHelp工具类进行数据解析以及补充
        StatisticalHelp statisticalHelp = new StatisticalHelp();
        getDao_nan = statisticalHelp.assemblyReadingDataDistribution(type,getDao_nan);
        getDao_nv = statisticalHelp.assemblyReadingDataDistribution(type,getDao_nv);

        theResult.put("男",getDao_nan);
        theResult.put("女",getDao_nv);
//        System.out.println("男性读者数据分布：");
//        for (Map<String,Object> map:theResult.get("男")){
//            System.out.println(map);
//        }
//        System.out.println("女性读者数据分布：");
//        for (Map<String,Object> map:theResult.get("女")){
//            System.out.println(map);
//        }
        return theResult;
    }

    //根据作品ID获取对应读者不同性别的年龄段分布情况
    @Override
    public Map<String, List<Map<String,Object>>> getReadingTimeDistributionByWorkId(List<String> type,int work_id) {

        Map<String, List<Map<String,Object>>> theResult = new HashMap<String,List<Map<String,Object>>>();
        List<Map<String,Object>> getDao_nan = readingHistoryInfoDao.selectReadingTimeDistributionByWorkIdAndGender(work_id,"男");
        List<Map<String,Object>> getDao_nv = readingHistoryInfoDao.selectReadingTimeDistributionByWorkIdAndGender(work_id,"女");

        //使用StatisticalHelp工具类进行数据解析以及补充
        StatisticalHelp statisticalHelp = new StatisticalHelp();
        getDao_nan = statisticalHelp.assemblyReadingDataDistribution(type,getDao_nan);
        getDao_nv = statisticalHelp.assemblyReadingDataDistribution(type,getDao_nv);

        theResult.put("男",getDao_nan);
        theResult.put("女",getDao_nv);
//        System.out.println("男性读者数据分布：");
//        for (Map<String,Object> map:theResult.get("男")){
//            System.out.println(map);
//        }
//        System.out.println("女性读者数据分布：");
//        for (Map<String,Object> map:theResult.get("女")){
//            System.out.println(map);
//        }
        return theResult;
    }

}
