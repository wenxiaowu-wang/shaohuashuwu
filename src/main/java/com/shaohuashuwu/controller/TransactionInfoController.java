package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.TransactionInfo;
import com.shaohuashuwu.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


/**
 * 包:com.shaohuashuwu.controller
 * 作者:王洪斌
 * 日期:2020/9/15
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/transactionInfoController")
@SessionAttributes(value = {"user_name","user_id"},types = {String.class,Integer.class})
public class TransactionInfoController {

    @Autowired
    private TransactionInfoService transactionInfoService;


    /*
     * 郝振威
     * */


    @ResponseBody
    @RequestMapping(path = "/subscribeAChapterGUN/{userId}/{subBeanNum}/{chapterId}/{addBeanNum}")
    public boolean subscribeAChapterGUN(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "subBeanNum") Integer subBeanNum,@PathVariable(value = "chapterId") Integer chapterId,@PathVariable(value = "addBeanNum") Integer addBeanNum) throws Exception {
        boolean subsResult ;
        subsResult = transactionInfoService.subscribeAChapterGUN(userId, subBeanNum, chapterId, addBeanNum);
        return subsResult;
    }


    //多个订阅单个章节H(回滚)
    @ResponseBody
    @RequestMapping(value = "/subscribeChapterGUN/{userId}/{chapterId}")
    public Boolean subscribeChapterGUN(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "chapterId") List<Integer> chapterId)  {
        boolean subsResult = true ;

        for (int id : chapterId) {
            subsResult = transactionInfoService.subscribeAChapterGUN(userId, -10, id, 10);
        }
        return subsResult;
    }

}
