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
@RequestMapping(path = "/userSession")
@SessionAttributes(value = {"user_id","user_name","head_portrait","selected_user_id","selected_user_name"},types = {String.class,Integer.class})
public class UserSession {


    /**
     * 向session中存入user的ID、name、头像、地区、生日、性别
     * 用于更改个人资料
     */
    @ResponseBody
    @RequestMapping(path = "/savePersonalData/{id}/{name}/{avatar}/{sex}/{birthday}/{area}")
    public String savePersonalData(Model model, @PathVariable(value = "id")Integer id,
                       @PathVariable(value = "name")String name,
                       @PathVariable(value = "avatar")String avatar,
                       @PathVariable(value = "sex")String sex,
                       @PathVariable(value = "birthday")String birthday,
                       @PathVariable(value = "area")String area){
        // 把部分信息放进session域中。
        System.out.println("个人资料：向session域中存入数据");
        model.addAttribute("user_id",id);
        model.addAttribute("user_name",name);
        model.addAttribute("head_portrait",avatar);
        model.addAttribute("gender",sex);
        model.addAttribute("birthday",birthday);
        model.addAttribute("area",area);

        System.out.println("控制台查看存入session的user_id："+id+"、user_name"+name+"、header_portrait"+avatar+"、gender"+sex+"、birthday"+birthday+"、area"+area);

        return "User's Loading is complete";
    }

    /**
     * login时 向session中存入user的phone_number/密码/code
     */
    @ResponseBody
    @RequestMapping(path = "/saveUserPhoneNumber/{phone_number}/{password}/{smsCode}")
    public String save(Model model, @PathVariable(value = "phone_number")String phone_number,@PathVariable(value = "password") String password,@PathVariable(value = "smsCode") String smsCode){

        System.out.println("向session域中存入数据");
        model.addAttribute("phone_number",phone_number);
        model.addAttribute("password",password);
        model.addAttribute("smsCode",smsCode);
        System.out.println("向session域中存入数据(changePassword)"+phone_number);
        System.out.println("向session域中存入数据(changePassword)"+password);
        System.out.println("向session域中存入数据(changePassword)"+smsCode);
        return "Admin's Loading is complete";
    }


    /**
     * 从session中获取user的 ID、name、头像、地区、生日、性别
     * 修改个人资料使用
     */
    @ResponseBody
    @RequestMapping(path = "/getPersonalData")
    public HashMap<String,Object> getPersonalData(ModelMap modelMap){

        Integer user_id = (Integer)modelMap.get("user_id");
        String user_name = (String)modelMap.get("user_name");
        String head_portrait = (String)modelMap.get("head_portrait");
        String gender = (String)modelMap.get("gender");
        String birthday = (String)modelMap.get("birthday");
        String area = (String)modelMap.get("area");
        System.out.println("user_id = "+user_id+",user_name = "+user_name+"、生日："+birthday);

        HashMap<String,Object> map = new HashMap();
        map.put("user_id",user_id);
        map.put("user_name",user_name);
        map.put("head_portrait",head_portrait);
        map.put("gender",gender);
        map.put("area",area);
        map.put("birthday",birthday);
        return map;
    }



    /**
     * 从session中获取user的phone_number/密码/code
     * 登录功能使用
     */
    @ResponseBody
    @RequestMapping(path = "/getPhoneNumber")
    public HashMap<String,Object> getPhoneNumber(ModelMap modelMap){

        String phone_number = (String) modelMap.get("phone_number");
        String password = (String) modelMap.get("password");
        String smsCode = (String) modelMap.get("smsCode");
        System.out.println("session域中获取数据(changePassword)"+phone_number);
        System.out.println("session域中获取数据(changePassword)"+password);
        System.out.println("session域中获取数据(changePassword)"+smsCode);
        HashMap<String,Object> map = new HashMap();
        map.put("phone_number",phone_number);
        map.put("password",password);
        map.put("smsCode",smsCode);
        return map;
    }
    /**
     * 清除session中的值
     * 注意：全部删除，并不能指定删除
     */
    @RequestMapping(path = "/deleteUser")
    public String delete(SessionStatus status){
        status.setComplete();
        return "test.html";
    }

    /**
     * 阿斌
     */
    /**
     * 向session中存入user的ID和name
     */
    @ResponseBody
    @RequestMapping(path = "/saveUser/{id}/{name}/{avatar}")
    public String save(Model model, @PathVariable(value = "id")Integer id,
                       @PathVariable(value = "name")String name,
                       @PathVariable(value = "avatar")String avatar){
        //模拟登录的的时候进行的一些操作，包括根据ID获取用户信息，
        // 把部分信息放进session域中。

        System.out.println("向session域中存入数据");
        model.addAttribute("user_id",id);
        model.addAttribute("user_name",name);
        model.addAttribute("head_portrait",avatar);
        System.out.println("解码前["+id+"] ["+name+"]");

        return "User's Loading is complete";
    }

    /**
     * 将选中的用户信息存入session中
     * @param model
     * @param id    选中用户的ID
     * @param name  选中的用户的name
     * @return String 选中用户装载完毕
     */
    @ResponseBody
    @RequestMapping(path = "/saveSelectedUser/{id}/{name}/{avatar}")
    public String saveSelectedUser(Model model, @PathVariable(value = "id")Integer id,
                                   @PathVariable(value = "name")String name,
                                   @PathVariable(value = "avatar")String avatar){
        model.addAttribute("selected_user_id",id);
        model.addAttribute("selected_user_name",name);
        model.addAttribute("selected_head_portrait",avatar);
        return "Selected User's loading is complete";
    }

    @ResponseBody
    @RequestMapping(path = "/saveSelectedUserId/{id}")
    public String saveSelectedUserId(Model model, @PathVariable(value = "id")Integer id){
        model.addAttribute("selected_user_id",id);
        return "Selected UserId's  loading is complete";
    }

    @RequestMapping(path = "/toTestHtml")
    public String save(){
        return "test.html";
    }
    /**
     * 从session中获取user的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/getUser")
    public HashMap<String,Object> get(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        String user_name = (String)modelMap.get("user_name");
        String head_portrait = (String)modelMap.get("head_portrait");
        System.out.println("user_id = "+user_id+",user_name = "+user_name);

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("user_id",user_id);
        map.put("user_name",user_name);
        map.put("head_portrait",head_portrait);
        return map;
    }
    /**
     * 从session中获取选择的user的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/getSelectedUser")
    public HashMap<String,Object> getSelectedUser(ModelMap modelMap){
        Integer selected_user_id = (Integer)modelMap.get("selected_user_id");
        String selected_user_name = (String)modelMap.get("selected_user_name");
        String selected_head_portrait = (String)modelMap.get("selected_head_portrait");
        System.out.println("selected_user_id = "+selected_user_id+",selected_user_name = "+selected_user_name);

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("selected_user_id",selected_user_id);
        map.put("selected_user_name",selected_user_name);
        map.put("selected_head_portrait",selected_head_portrait);
        return map;
    }


}
