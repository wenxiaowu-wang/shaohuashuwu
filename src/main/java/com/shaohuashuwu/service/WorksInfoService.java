package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.WorksInfo;

import java.util.List;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
public interface WorksInfoService {

    //根据作者ID获取所有对应的作品信息
    public List<WorksInfo> getAllWorkInfoOfAuthorId(int user_id);

    //根据用户ID获取用户加入书架的作品的作品名字
    public List<WorksInfo> getBookshelfWorkNameByWorkID(int user_id);

    //根据用户ID获取用户阅读历史的作品的作品名字
    public List<WorksInfo> getReadingHistoryWorkNameByWorkID(int user_id);

    //根据用户的ID获取该用户写过的作品ID、作品名字
    public List<WorksInfo> getWorkIdNameByUserId(int user_id);

}
