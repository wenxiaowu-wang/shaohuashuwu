package com.shaohuashuwu.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
public interface BookshelfInfoDao {


    //根据作品ID和读者性别获取喜欢该作品的对应性别用户喜欢的作品类型统计分布
    @Select("SELECT work_main_label,COUNT(bs_b.work_id)AS reader_num FROM bookshelf_info bs_a,bookshelf_info bs_b,user_info,works_info WHERE bs_a.user_id = bs_b.user_id AND bs_a.work_id = #{param1} AND user_info.user_id = bs_a.user_id AND works_info.work_id = bs_b.work_id AND gender LIKE #{param2} GROUP BY work_main_label ORDER BY work_main_label DESC")
    public List<Map<String,Object>> selectReaderLikeDistributionByWorkIdAndGender(int work_id,String gender);

}
