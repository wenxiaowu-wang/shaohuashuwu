package com.shaohuashuwu.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:管理员dao层
 */
public interface ThirdPartyInfoDao {

    //根据交易记录ID获取第三方账号
    @Select("select third_number from third_party_info where transaction_id = #{transaction_id}")
    public String selectThirdNumberById(int transaction_id);

    //添加一条第三方账号与交易ID记录
    @Insert("INSERT INTO third_party_info(transaction_id,third_number)VALUES(#{param1},#{param2})")
    public int insertOneRecord(int transaction_id,String third_number);
}
