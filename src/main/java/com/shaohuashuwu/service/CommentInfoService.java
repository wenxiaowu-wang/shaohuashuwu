package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.CommentInfo;
import com.shaohuashuwu.domain.vo.CommentInfoChildVo;
import com.shaohuashuwu.domain.vo.CommentInfoParentVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface CommentInfoService {


    //获取书籍父级评论信息
    public List<CommentInfoParentVo> getCommentParentInfoByWorkId(int work_id);

    //获取章节父级评论信息
    public List<CommentInfoParentVo> getCommentParentInfoByChapterId(int chapter_id);

    //获取书籍次级评论信息
    public List<CommentInfoChildVo> getCommentChildInfoByWorkId(int work_id);

    //获取章节次级评论信息
    public List<CommentInfoChildVo> getCommentChildInfoByChapterId(int chapter_id);

    //父级评论 评论章节 增加一条评论
    public Boolean addChapterCommentInfo(int user_id, Timestamp comment_time, String comment_content, int chapter_id,int comment_pid);

    //父级评论 评论图书 增加一条评论
    public Boolean addCommentInfo(int user_id, Timestamp comment_time, String comment_content, int work_id,int comment_pid);

    //删除评论 包含及联删除
    public Boolean deleteWorkComment(int comment_id);

}
