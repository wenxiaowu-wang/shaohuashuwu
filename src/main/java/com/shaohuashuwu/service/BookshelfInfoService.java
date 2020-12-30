package com.shaohuashuwu.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface BookshelfInfoService {

    //用户将小说收藏到书架
    public Boolean addBookshelfInfo(int user_id, int work_id, Timestamp collection_time);

    //查询某个用户书架的数量
    public int selectBookshelfInfoByUserId(int user_id);

    //移出书架
    public Boolean deleteBookshelfWorkByWorkId(int work_id,int user_id);

    //根据用户id删除收藏的书架内容
    public Boolean deleteBookshelfWorkByUserId(int user_id);

    /**
     * 阿斌
     */
    //根据作品ID获取该作品读者不同性别的年龄段分布情况
    public Map<String, List<Map<String,Object>>> getReaderLikeDistributionByWorkIdAndGender(List<String> type, int work_id);
}
