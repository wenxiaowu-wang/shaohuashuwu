package com.shaohuashuwu.controller;



import com.shaohuashuwu.domain.vo.CommentInfoChildVo;
import com.shaohuashuwu.domain.vo.CommentInfoParentVo;
import com.shaohuashuwu.service.CommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/commentInfoController")
public class CommentInfoController {

    @Autowired
    public CommentInfoService commentInfoService;

    //获取书籍父级评论
    @ResponseBody
    @RequestMapping(path = "/getCommentParentInfoByWorkId/{id}")
    public List<CommentInfoParentVo> getCommentParentInfoByWorkId(@PathVariable(value = "id")Integer id){
        List<CommentInfoParentVo> getResult  = commentInfoService.getCommentParentInfoByWorkId(id);
        return getResult;
    }

    //获取章节父级评论
    @ResponseBody
    @RequestMapping(path = "/getCommentParentInfoByChapterId/{id}")
    public List<CommentInfoParentVo> getCommentParentInfoByChapterId(@PathVariable(value = "id")Integer id){

        List<CommentInfoParentVo> getResult  = commentInfoService.getCommentParentInfoByChapterId(id);
        return getResult;
    }

    //获取书籍次级评论
    @ResponseBody
    @RequestMapping(path = "/getCommentChildInfoByWorkId/{work_id}")
    public List<CommentInfoChildVo> getCommentChildInfoByWorkId(@PathVariable(value = "work_id")Integer work_id){
        List<CommentInfoChildVo> getResult  = commentInfoService.getCommentChildInfoByWorkId(work_id);
        return getResult;
    }

    //获取章节次级评论
    @ResponseBody
    @RequestMapping(path = "/getCommentChildInfoByChapterId/{id}")
    public List<CommentInfoChildVo> getCommentChildInfoByChapterId(@PathVariable(value = "id")Integer id){
        List<CommentInfoChildVo> getResult  = commentInfoService.getCommentChildInfoByChapterId(id);
        return getResult;
    }


    //存书籍评论
    @ResponseBody
    @RequestMapping(path = "/addCommentInfo/{user_id}/{comment_content}/{work_id}/{id}/{aid}")
    public Boolean addCommentInfo(@PathVariable(value = "user_id")Integer user_id,@PathVariable(value = "comment_content")String comment_content,@PathVariable(value = "work_id")Integer work_id,@PathVariable(value = "id")Integer id,@PathVariable(value = "aid")Integer aid){

        boolean addResult = false;

        Timestamp timestamp=new Timestamp(new Date().getTime());

        addResult = commentInfoService.addCommentInfo(user_id,timestamp,comment_content,work_id,id,aid);
        return addResult;
    }



    //存章节评论
    @ResponseBody
    @RequestMapping(path = "/addChapterCommentInfo/{user_id}/{comment_content}/{chapter_id}/{id}/{aid}")
    public Boolean addChapterCommentInfo(@PathVariable(value = "user_id")Integer user_id,@PathVariable(value = "comment_content")String comment_content,@PathVariable(value = "chapter_id")Integer chapter_id,@PathVariable(value = "id")Integer id,@PathVariable(value = "aid")Integer aid){

        boolean addResult = false;

        Timestamp timestamp=new Timestamp(new Date().getTime());

        addResult = commentInfoService.addChapterCommentInfo(user_id,timestamp,comment_content,chapter_id,id,aid);

        return addResult;
    }

    //删除评论 包含及联删除
    @ResponseBody
    @RequestMapping(path = "/deleteWorkComment/{comment_id}")
    public Boolean deleteWorkComment(@PathVariable(value = "comment_id")Integer comment_id){
        return commentInfoService.deleteWorkComment(comment_id);
    }



}
