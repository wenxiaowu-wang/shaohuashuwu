package com.shaohuashuwu.controller.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;

/**
 * 包:com.shaohuashuwu.controller.session
 * 作者:王洪斌
 * 日期:2020/10/13
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/adminSession")
@SessionAttributes(value = {"admin_id"},types = {String.class})
public class AdminSession {
    /**
     * 向session中存入Admin的ID
     */
    @ResponseBody
    @RequestMapping(path = "/saveAdmin/{id}")
    public String saveAdmin(Model model, @PathVariable(value = "id")String id){
        //模拟登录的的时候进行的一些操作，包括根据ID获取用户信息，
        // 把部分信息放进session域中。

        System.out.println("向session域中存入数据");
        model.addAttribute("admin_id",id);
        return "Admin's Loading is complete";
    }


    /**
     * 从session中获取Admin的id
     */
    @ResponseBody
    @RequestMapping(path = "/getAdmin")
    public HashMap<String,Object> get(ModelMap modelMap){
        String admin_id = (String) modelMap.get("admin_id");

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("admin_id",admin_id);
        return map;
    }

    /**
     * 清除session中的值
     * 注意：全部删除，并不能指定删除
     */
    @RequestMapping(path = "/deleteAdmin")
    @ResponseBody
    public String delete(SessionStatus status){
        status.setComplete();
        return "delete all session_content success.";
    }
}
