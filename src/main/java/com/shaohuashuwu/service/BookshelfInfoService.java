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
public interface BookshelfInfoService {

    //根据作品ID获取该作品读者不同性别的年龄段分布情况
    public Map<String, List<Map<String,Object>>> getReaderLikeDistributionByWorkIdAndGender(List<String> type,int work_id);
}
