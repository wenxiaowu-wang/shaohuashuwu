package com.shaohuashuwu.service;


import org.apache.ibatis.annotations.Delete;

import java.sql.Timestamp;

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
}
