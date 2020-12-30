package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.AttentionInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.service.AttentionInfoService;
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
 * 日期:2020/10/17
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/attentionInfoController")
@SessionAttributes(value = {"user_name","user_id","head_portrait","selected_user_id","selected_user_name"},types = {String.class,Integer.class})
public class AttentionInfoController {

    @Autowired
    public AttentionInfoService attentionInfoService;

    /**
     * 添加关注信息
     * @param modelMap session域  获取 本用户ID和选中用户ID
     * @return 关注结果：int{
     *                      0:失败
     *                      1：成功
     *                      2：已关注
     *                  }
     */
    @ResponseBody
    @RequestMapping(path = "/payAttentionToUser")
    public int payAttentionToUser(ModelMap modelMap){
        int theResult = 0;//关注该用户或作者失败
        Integer user_id = (Integer)modelMap.get("user_id");
        Integer selected_user_id = (Integer)modelMap.get("selected_user_id");
        AttentionInfo attentionInfo = new AttentionInfo();
        attentionInfo.setReader_id(user_id);
        attentionInfo.setAuthor_id(selected_user_id);
        boolean isAttention = attentionInfoService.isAlreadyAttention(attentionInfo);
        if (isAttention){
           theResult = 2;//已关注该作者
        }else{
            boolean attentionResult = false;
            attentionResult = attentionInfoService.addAttentionInfo(attentionInfo);
            if (attentionResult){
                theResult = 1;//关注该作者或用户成功
            }
        }
        return theResult;
    }

    /**
     * 获取所有关注作者的信息
     * @param modelMap session域  获取 本用户ID
     * @return      List<关注信息值对象>
     */
    @ResponseBody
    @RequestMapping(path = "/getAttentionAuthorInfo")
    public List<AttentionInfoVo> getAttentionAuthorInfo(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        return attentionInfoService.getAttentionAuthorInfo(user_id);
    }

    /**
     * 获取该用户的粉丝信息
     * @param modelMap session域  获取 本用户ID
     * @return 粉丝信息 关注信息值对象 集合
     */
    @ResponseBody
    @RequestMapping(path = "/getFansInfo")
    public List<AttentionInfoVo> getFansInfo(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        return attentionInfoService.getFansInfo(user_id);
    }

    /**
     * 获取与该用户相互关注的用户信息
     * @param modelMap session域  获取 本用户ID
     * @return 与该用户相互关注的用户信息值对象（关注信息值对象） 集合
     */
    @ResponseBody
    @RequestMapping(path = "/getEachAttentionUserInfo")
    public List<AttentionInfoVo> getEachAttentionUserInfo(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        return attentionInfoService.getEachAttentionUserInfo(user_id);
    }

    /**
     * 删除对应的关注信息
     * @param modelMap     获取用户ID所用的session中的一块域的变量名
     * @param selected_id       选中用户ID
     * @return      删除结果 boolean
     */
    @ResponseBody
    @RequestMapping(path = "/deleteAttentionInfo/{selected_id}")
    public boolean deleteAttentionInfo(ModelMap modelMap,@PathVariable(value = "selected_id")Integer selected_id){
        Integer id = (Integer)modelMap.get("user_id");
        AttentionInfo attentionInfo = new AttentionInfo();
        attentionInfo.setReader_id(id);
        attentionInfo.setAuthor_id(selected_id);
        return attentionInfoService.deleteAttentionInfo(attentionInfo);
    }

    /**
     * 跳转到关注页面（暂时存在）
     */
    @RequestMapping(path = "/toPayAttemtion")
    public String toPayAttemtion(){
        System.out.println("跳转到关注页面（暂时存在）");
        return "payAttention.html";
    }

    @RequestMapping(path = "/toMyConcerned")
    public String toMyConcerned(){
        return "myConcernedInterface.html";
    }

}
