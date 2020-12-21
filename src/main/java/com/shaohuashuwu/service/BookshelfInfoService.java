package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.BookshelfInfoVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface BookshelfInfoService {

    //用户将小说收藏到书架
    public Boolean addBookshelfInfo(int user_id, int work_id, Timestamp collection_time);

    //查询某个用户书架的数量
    public int selectBookshelfInfoByUserId(int user_id);

    //移出书架
    public Boolean deleteBookshelfWorkByWorkId(int work_id);

    //根据用户id删除收藏的书架内容
    public Boolean deleteBookshelfWorkByUserId(int user_id);
}
