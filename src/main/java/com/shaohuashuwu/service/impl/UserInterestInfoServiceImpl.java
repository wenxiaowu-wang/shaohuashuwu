package com.shaohuashuwu.service.impl;


import com.shaohuashuwu.dao.UserInterestInfoDao;
import com.shaohuashuwu.domain.UserinterestInfo;
import com.shaohuashuwu.service.UserInfoService;
import com.shaohuashuwu.service.UserInterestInfoService;
import com.shaohuashuwu.service.UserandWorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInterestInfoServiceImpl")
public class UserInterestInfoServiceImpl implements UserInterestInfoService {

    @Autowired
    UserInterestInfoDao userInterestInfoDao;

    /**
     * 修改用户兴趣标签
     * 小说详情
     * @param userinterestInfo
     * @return
     */
    @Override
    public int updateUserInterestInfo(UserinterestInfo userinterestInfo) {

        int ishave = userInterestInfoDao.selectCountByUser_idAndLabel_name(userinterestInfo);
        int addResult = 0;
        int updateResult = 0;
        System.out.println("用户情趣标签结果-----------"+ishave);

        if(ishave == 0){
             addResult = userInterestInfoDao.addUserinterestInfo(userinterestInfo);
        }else {
            updateResult= userInterestInfoDao.updateUserinterestInfo(userinterestInfo);
        }

        if (addResult == 1|| updateResult == 1){
            return 1;
        }
        else {
            return 0;
        }
    }
}
