package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * 包:com.shaohuashuwu.service
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public interface UserInfoService {


    //依据章节id查询作品信息
    public UserInfo selectUserInfoByChapter_id(int chapter_id);
}
