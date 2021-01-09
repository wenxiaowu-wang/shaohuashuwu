package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.CommentInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.ChapterCommentInfo;
import com.shaohuashuwu.domain.vo.CommentInfoChildVo;
import com.shaohuashuwu.domain.vo.CommentInfoParentVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface CommentInfoDao {


    //获取书籍父级评论信息
    @Select("SELECT * FROM user_info,comment_info WHERE user_info.user_id = comment_info.user_id AND  comment_pid = 2 AND comment_info.work_id= #{work_id} ORDER BY comment_id DESC")
    public List<CommentInfoParentVo> selectCommentParentInfoByWorkId(int work_id);

    //获取章节父级评论信息
    @Select("SELECT * FROM user_info,comment_info WHERE user_info.user_id = comment_info.user_id AND  comment_pid = 2 AND comment_info.chapter_id= #{chapter_id} ORDER BY comment_id DESC")
    public List<CommentInfoParentVo> selectCommentParentInfoByChapterId(int chapter_id);

    //获取书籍次级评论信息
    @Select("SELECT u1.user_name AS parent_name,u2.user_name,u2.user_id,u2.head_portrait,c2.comment_time,c2.comment_content,c2.comment_id,c2.comment_pid,c2.comment_aid FROM user_info u1,user_info u2,comment_info c1,comment_info c2 WHERE u2.user_id = c2.user_id AND u1.user_id = c1.user_id AND c2.comment_pid >2 AND c2.work_id= #{work_id} AND c2.comment_pid = c1.comment_id ORDER BY comment_id DESC")
    public List<CommentInfoChildVo> selectCommentChildInfoByWorkId(int work_id);

    //获取章节次级评论信息
    @Select("SELECT u1.user_name AS parent_name,u2.user_name,u2.user_id,u2.head_portrait,c2.comment_time,c2.comment_content,c2.comment_id,c2.comment_pid,c2.comment_aid FROM user_info u1,user_info u2,comment_info c1,comment_info c2 WHERE u2.user_id = c2.user_id AND u1.user_id = c1.user_id AND c2.comment_pid >2 AND c2.chapter_id = #{chapter_id} AND c2.comment_pid = c1.comment_id ORDER BY comment_id DESC")
    public List<CommentInfoChildVo> selectCommentChildInfoByChapterId(int chapter_id);


    //父级评论 评论图书 增加一条评论
    @Insert("insert into comment_info (user_id,comment_time,comment_content,work_id,comment_pid,comment_aid) values(#{user_id},#{comment_time},#{comment_content},#{work_id},#{comment_pid},#{comment_aid})")
    public int insertCommentInfo(CommentInfo commentInfo);

    //父级评论 评论章节 增加一条评论
    @Insert("insert into comment_info (user_id,comment_time,comment_content,chapter_id,comment_pid,comment_aid) values(#{user_id},#{comment_time},#{comment_content},#{chapter_id},#{comment_pid},#{comment_aid})")
    public int insertChapterCommentInfo(ChapterCommentInfo chapterCommentInfo);

    //删除评论 包含及联删除
    @Delete("DELETE FROM comment_info WHERE comment_id = #{id}")
    public Boolean deleteWorkComment(int comment_id);

}
