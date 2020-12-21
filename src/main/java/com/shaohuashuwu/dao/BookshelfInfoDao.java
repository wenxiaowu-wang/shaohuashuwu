package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.BookshelfInfo;
import com.shaohuashuwu.domain.vo.BookshelfInfoVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookshelfInfoDao {

    //加入到书架
    @Insert("insert into bookshelf_info(user_id,work_id,collection_time)values(#{user_id},#{work_id},#{collection_time})")
    public void insertBookshelfInfoByUserId(BookshelfInfo bookshelfInfo);

    //根据用户id，作品id判断是否已加入到书架
    @Select("select count(*) from bookshelf_info where user_id = #{param1} AND work_id = #{param2}")
    public int isBookshelfInfoExistByUserId(int user_id,int work_id);

    //查询书架总数
    @Select("select count(*) from bookshelf_info")
    public int selectBookshelfInfoAllCount();

    //根据user_id查询该用户 书架共有几本书
    @Select("select count(*) from bookshelf_info where user_id = #{user_id}")
    public int selectBookshelfInfoByUserId(int user_id);


    //移出书架
    @Delete("DELETE FROM bookshelf_info WHERE work_id = #{work_id}")
    public Boolean deleteBookshelfWorkByWorkId(int work_id);


    //根据用户id删除收藏的书架内容
    @Delete("DELETE FROM bookshelf_info WHERE user_id = #{user_id}")
    public Boolean deleteBookshelfWorkByUserId(int user_id);





}
