package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.UserinterestInfo;
import com.shaohuashuwu.domain.WorkslabelInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestInfoDao {

    //依据作品id湖区用户最感兴趣的标签信息
    //功能点：个性作品
    @Select("SELECT MAX(select_num) select_num,label_name FROM user_interest_info WHERE user_id = #{user_id}")
    public UserinterestInfo selectUserinterestInfoByUser_id(int user_id);

}
