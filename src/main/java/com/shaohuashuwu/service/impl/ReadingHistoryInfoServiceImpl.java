package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ReadingHistoryInfoDao;

import com.shaohuashuwu.domain.ReadingHistoryInfo;
import com.shaohuashuwu.service.ReadingHistoryInfoService;
import com.shaohuashuwu.utils.StatisticalHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("readingHistoryInfoService")
public class ReadingHistoryInfoServiceImpl implements ReadingHistoryInfoService {

    @Autowired
    private ReadingHistoryInfoDao readingHistoryInfoDao;



    private ReadingHistoryInfo readingHistoryInfo ;

    @Override
    public Boolean addReadingHistoryInfo(int user_id, int work_id, Timestamp reading_time) {
        System.out.println("添加阅读历史插入(huo更新)数据");
        boolean inserResulte = false;
        System.out.println("" + user_id + "、" + work_id + "、" + reading_time);

        readingHistoryInfo = new ReadingHistoryInfo(user_id, work_id, reading_time);

        int exist = 0;
        //判断用户是否存在
        exist = readingHistoryInfoDao.isReadingHistoryInfoExistByUserId(user_id, work_id);

        //exist = 0 说明该用户第一次阅读该小说 直接添加就行了
        //否则的话执行更新操作 只更改数据库的阅读时间就行了
        if (exist == 0) {

            int inserResulte1 = 0;
            inserResulte1 = readingHistoryInfoDao.insertReadingHistoryInfoByUserId(readingHistoryInfo);

            if (inserResulte1 == 1) {
                inserResulte = true;
            }
        } else {
            System.out.println("数据库中已存在，更新时间");
            if (readingHistoryInfoDao.updatePersonalDataByID(reading_time, user_id, work_id) != 0) {
                inserResulte = true;
                System.out.println("更新时间成功");
            }
        }
        System.out.println("serviceImpl" + inserResulte);
        return inserResulte;
    }

    //根据用户id判断以前是否看过这本小说（查看数据库该记录书否存在）
    @Override
    public int isReadingHistoryInfoExistByUserId(int user_id, int work_id) {

        int isExist = readingHistoryInfoDao.isReadingHistoryInfoExistByUserId(user_id, work_id);

        return isExist;
    }

    //删除单个历史记录
    @Override
    public Boolean deleteHistoryWorkByWorkId(int work_id) {
        boolean result = false;
        if (readingHistoryInfoDao.deleteHistoryWorkByWorkId(work_id)) {
            result = true;
        }
        return result;
    }

    //清空历史记录
    @Override
    public Boolean deleteHistoryWorkByUserId(int user_id) {
        boolean result = false;

        if (readingHistoryInfoDao.deleteHistoryWorkByUserId(user_id)) {
            result = true;
        }
        return result;
    }

    //查询用户浏览过要多少本书
    @Override
    public int getReadingHistoryCountByUserId(int user_id) {
        return readingHistoryInfoDao.selectReadingHistoryCountByUserId(user_id);
    }

    /**
     * 阿斌
     */
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

        return theResult;
    }

    //根据作品ID获取对应读者不同时间段的阅读分布情况
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

        return theResult;
    }
}
