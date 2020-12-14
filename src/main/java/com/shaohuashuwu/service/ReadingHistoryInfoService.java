package com.shaohuashuwu.service;

import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/11/28
 * 项目:shaohuashuwu
 * 描述:
 */
public interface ReadingHistoryInfoService {

    //根据作品ID获取该作品读者不同性别的年龄段分布情况
    public Map<String, List<Map<String,Object>>> getReaderAgeDistributionByWorkId(List<String> type,int work_id);

    //根据作品ID获取该作品读者阅读时间段的分布情况
    public Map<String, List<Map<String,Object>>> getReadingTimeDistributionByWorkId(List<String> type,int work_id);
}
