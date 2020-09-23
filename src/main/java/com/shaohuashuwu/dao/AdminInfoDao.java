package com.shaohuashuwu.dao;


import com.shaohuashuwu.domain.AdminInfo;
import org.apache.ibatis.annotations.*;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:管理员dao层
 */
public interface AdminInfoDao {

    // 用于返回该条数据插入后的id
    @SelectKey(statement = "select last_insert_id()", before = false, keyProperty = "id", resultType = Integer.class)

    //增加一条管理员信息
    @Insert("insert into admin_info(admin_id,admin_password) values(#{admin_id},#{admin_password})")
    public int insertAdminInfo(AdminInfo adminInfo);

    //删除一条管理员信息
    @Delete("delete from admin_info where admin_id = #{admin_id}")
    public int deleteAdminInfo(String admin_id);

    //查询一条管理员信息
    @Select("select * from admin_info where admin_id = #{admin_id}")
    @Results(id = "adminInfo",value = {
            @Result(id = true,column = "admin_id",property = "admin_id"),
            @Result(column = "admin_password",property = "admin_password")
    })
    public AdminInfo selectAdminInfoByAdminId(String admin_id);

    //更新一条管理员信息
    @Update("update admin_info set admin_password = #{admin_password} where admin_id = #{adimn_id}")
    public int updateAdminInfo(AdminInfo adminInfo);


}
