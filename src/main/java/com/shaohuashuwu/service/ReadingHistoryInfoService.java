package com.shaohuashuwu.service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ReadingHistoryInfoService {


    //添加阅读历史
    public Boolean addReadingHistoryInfo (int user_id, int work_id, Timestamp reading_time);

    //根据用户id判断以前是否看过这本小说（查看数据库该记录书否存在）
    public int isReadingHistoryInfoExistByUserId(int user_id,int work_id);

    //删除单个记录
    public Boolean deleteHistoryWorkByWorkId(int work_id);

    //删除所有
    public Boolean deleteHistoryWorkByUserId(int user_id);

    //查询用户浏览过要多少本书
    public int getReadingHistoryCountByUserId(int user_id);

    /**
     * 阿斌
     */
    //根据作品ID获取该作品读者不同性别的年龄段分布情况
    public Map<String, List<Map<String,Object>>> getReaderAgeDistributionByWorkId(List<String> type, int work_id);

    //根据作品ID获取该作品读者阅读时间段的分布情况
    public Map<String, List<Map<String,Object>>> getReadingTimeDistributionByWorkId(List<String> type,int work_id);
}
