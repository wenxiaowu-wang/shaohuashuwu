package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.UserinterestInfo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestInfoDao {

    //依据作品id湖区用户最感兴趣的标签信息
    //功能点：个性作品
    @Select("SELECT MAX(select_num) select_num,label_name FROM user_interest_info WHERE user_id = #{user_id}")
    public UserinterestInfo selectUserinterestInfoByUser_id(int user_id);

    //判断用户感兴趣标签是否存在
    //功能点：小说详情
    @Select("SELECT count(*)  FROM user_interest_info WHERE user_id = #{user_id} and label_name = #{label_name}")
    public int selectCountByUser_idAndLabel_name(UserinterestInfo userinterestInfo);

    //添加用户兴趣标签
    //动能点：小说详情
    @Insert("Insert into user_interest_info(user_id,label_name,select_num) values (#{user_id},#{label_name},#{select_num})")
    public int addUserinterestInfo(UserinterestInfo userinterestInfo);


    //修改用户兴趣标签数
    //动能点：小说详情
    @Update("Update user_interest_info SET select_num=select_num+1 where label_name = #{label_name} and user_id = #{user_id}")
    public int updateUserinterestInfo(UserinterestInfo userinterestInfo);

}
