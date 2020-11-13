package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.AttentionInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.service.AttentionInfoService;
import com.shaohuashuwu.service.UserandWorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userandWorksInfoService")
public class UserandWorksInfoServiceImpl implements UserandWorksInfoService {

    @Autowired
    public AttentionInfoDao attentionInfoDao;


    @Override
    public int selectUserandWorksInfo(int user_id) {



        return 0;
    }
}
