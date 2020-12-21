package com.shaohuashuwu.service.impl;


import com.shaohuashuwu.dao.BookshelfInfoDao;
import com.shaohuashuwu.dao.ReadingHistoryInfoDao;
import com.shaohuashuwu.domain.BookshelfInfo;
import com.shaohuashuwu.domain.ReadingHistoryInfo;
import com.shaohuashuwu.domain.vo.BookshelfInfoVo;
import com.shaohuashuwu.service.BookshelfInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("bookshelfInfoService")
public class BookshelfInfoServiceImpl implements BookshelfInfoService {

    @Autowired
    public BookshelfInfoDao bookshelfInfoDao;

    BookshelfInfo bookshelfInfo;

    //用户将小说收藏到书架
    @Override
    public Boolean addBookshelfInfo(int user_id, int work_id, Timestamp collection_time) {
        System.out.println("加入书架");
        boolean inserResulte = false;
        System.out.println(""+user_id+"、"+work_id+"、"+collection_time);
        bookshelfInfo = new BookshelfInfo(user_id,work_id,collection_time);
        int exist = 0;

        //判断书架是否存在该书籍
        exist = bookshelfInfoDao.isBookshelfInfoExistByUserId(user_id,work_id);

        //exist = 0 说明该小说不存在书架内 直接添加就行了
        if (exist == 0) {
            int before = bookshelfInfoDao.selectBookshelfInfoAllCount();
            System.out.println("加入书架之前书架小说总数：" + before);
            bookshelfInfoDao.insertBookshelfInfoByUserId(bookshelfInfo);
            int after = bookshelfInfoDao.selectBookshelfInfoAllCount();
            System.out.println("加入书架之后小说总数：" + after);
            if (after > before) {
                inserResulte = true;
            }
        }
        return inserResulte;
    }

    //查询某个用户书架收藏小说的数量
    @Override
    public int selectBookshelfInfoByUserId(int user_id) {

        int selectResulte = -1;
        if (bookshelfInfoDao.selectBookshelfInfoByUserId(user_id) >=0){
            selectResulte = bookshelfInfoDao.selectBookshelfInfoByUserId(user_id);
        }
        return selectResulte;
    }


    //移出书架
    @Override
    public Boolean deleteBookshelfWorkByWorkId(int work_id) {
        return bookshelfInfoDao.deleteBookshelfWorkByWorkId(work_id);
    }

    //根据用户id删除收藏的书架内容
    @Override
    public Boolean deleteBookshelfWorkByUserId(int user_id) {
        return bookshelfInfoDao.deleteBookshelfWorkByUserId(user_id);
    }


}
