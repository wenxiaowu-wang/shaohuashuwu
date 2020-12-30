package com.shaohuashuwu.controller.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.net.URLDecoder;
import java.util.HashMap;

/**
 * 包:com.shaohuashuwu.controller.session
 * 作者:王洪斌
 * 日期:2020/10/14
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/worksSession")
@SessionAttributes(value = {"work_id","work_name"},types = {Integer.class,String.class})
public class WorksSession {

    /**
     * 向session中存入work的ID和name
     */
    @ResponseBody
    @RequestMapping(path = "/saveWork/{id}/{name}")
    public String save(Model model, @PathVariable(value = "id")Integer id, @PathVariable(value = "name")String name){
        //模拟登录的的时候进行的一些操作，包括根据ID获取用户信息，
        // 把部分信息放进session域中。

        System.out.println("向session域中存入数据");
        model.addAttribute("work_id",id);
        model.addAttribute("work_name",name);
        System.out.println("解码前["+id+"] ["+name+"]");
        //        name = URLDecoder.decode(name,"UTF-8");
        name = URLDecoder.decode(name);
        System.out.println("解码后 name is "+name);
        return "Work's Loading is complete";
    }

    /**
     * 从session中获取work的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/getWork")
    public HashMap<String,Object> get(ModelMap modelMap){
        Integer work_id = (Integer)modelMap.get("work_id");
        String work_name = (String)modelMap.get("work_name");
        System.out.println("work_id = "+work_id+",work_name = "+work_name);

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("work_id",work_id);
        map.put("work_name",work_name);
        return map;
    }
    /**
     * 从session中获取work的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/getWorkName")
    public HashMap<String,Object> getWorkName(ModelMap modelMap){
        String work_name = (String)modelMap.get("work_name");

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("work_name",work_name);
        return map;
    }

    /**
     * 清除session中的值
     * 注意：全部删除，并不能指定删除
     */
    @RequestMapping(path = "/deleteWork")
    public String delete(SessionStatus status){
        status.setComplete();
        return "test.html";
    }
}
