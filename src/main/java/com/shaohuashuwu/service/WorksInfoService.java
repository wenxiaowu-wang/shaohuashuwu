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
    public List<WorksInfo> getWorkInfoOfAuthor(int user_id);
}
