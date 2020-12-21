package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.CommentInfoDao;
import com.shaohuashuwu.domain.CommentInfo;
import com.shaohuashuwu.domain.vo.ChapterCommentInfo;
import com.shaohuashuwu.domain.vo.CommentInfoChildVo;
import com.shaohuashuwu.domain.vo.CommentInfoParentVo;
import com.shaohuashuwu.service.CommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service("commentInfoService")
public class CommentInfoServiceImpl implements CommentInfoService {

    @Autowired
    public CommentInfoDao commentInfoDao;

    CommentInfo commentInfo;

    ChapterCommentInfo chapterCommentInfo;



    //父级评论 评论图书 增加一条评论 书籍
    @Override
    public Boolean addCommentInfo(int user_id, Timestamp comment_time, String comment_content, int work_id, int comment_pid) {

        boolean addResult = false;
        commentInfo = new CommentInfo(user_id, comment_time, comment_content, work_id, comment_pid);

        int i = 0;
        i = commentInfoDao.insertCommentInfo(commentInfo);

        if (i != 0) {
            addResult = true;
        }

        return addResult;
    }

    //父级评论 评论章节 增加一条评论 章节
    @Override
    public Boolean addChapterCommentInfo(int user_id, Timestamp comment_time, String comment_content, int chapter_id, int comment_pid) {

        boolean addResult = false;

        chapterCommentInfo = new ChapterCommentInfo(user_id, comment_time, comment_content, chapter_id, comment_pid);

        if (commentInfoDao.insertChapterCommentInfo(chapterCommentInfo) != 0) {
            addResult = true;
        }
        return addResult;
    }


    //获取书籍父级评论信息
    @Override
    public List<CommentInfoParentVo> getCommentParentInfoByWorkId(int work_id) {

        List<CommentInfoParentVo> getResult = commentInfoDao.selectCommentParentInfoByWorkId(work_id);
        return getResult;
    }

    //获取章节父级评论信息
    @Override
    public List<CommentInfoParentVo> getCommentParentInfoByChapterId(int chapter_id) {

        List<CommentInfoParentVo> getResult = commentInfoDao.selectCommentParentInfoByChapterId(chapter_id);
        return getResult;

    }


    //获取书籍次级评论信息
    @Override
    public List<CommentInfoChildVo> getCommentChildInfoByWorkId(int work_id) {

        List<CommentInfoChildVo> getResult = commentInfoDao.selectCommentChildInfoByWorkId(work_id);

        return getResult;
    }

    //获取章节次级评论信息
    @Override
    public List<CommentInfoChildVo> getCommentChildInfoByChapterId(int chapter_id) {

        List<CommentInfoChildVo> getResult = commentInfoDao.selectCommentChildInfoByChapterId(chapter_id);
        return getResult;
    }



    //删除评论 包含及联删除
    @Override
    public Boolean deleteWorkComment(int comment_id) {
        return commentInfoDao.deleteWorkComment(comment_id);
    }

}