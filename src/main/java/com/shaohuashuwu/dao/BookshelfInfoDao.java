package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.BookshelfInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
    @Delete("DELETE FROM bookshelf_info WHERE work_id = #{param1} and user_id = #{param2}")
    public Boolean deleteBookshelfWorkByWorkId(int work_id,int user_id);


    //根据用户id删除收藏的书架内容
    @Delete("DELETE FROM bookshelf_info WHERE user_id = #{user_id}")
    public Boolean deleteBookshelfWorkByUserId(int user_id);


    //根据作品ID和读者性别获取喜欢该作品的对应性别用户喜欢的作品类型统计分布
    @Select("SELECT work_main_label,COUNT(bs_b.work_id)AS reader_num FROM bookshelf_info bs_a,bookshelf_info bs_b,user_info,works_info WHERE bs_a.user_id = bs_b.user_id AND bs_a.work_id = #{param1} AND user_info.user_id = bs_a.user_id AND works_info.work_id = bs_b.work_id AND gender LIKE #{param2} GROUP BY work_main_label ORDER BY work_main_label DESC")
    public List<Map<String,Object>> selectReaderLikeDistributionByWorkIdAndGender(int work_id, String gender);



}
