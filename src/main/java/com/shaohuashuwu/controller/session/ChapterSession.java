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
 * 日期:2020/10/14
 * 项目:shaohuashuwu
 * 描述:
 */
@Controller
@RequestMapping(path = "/chapterSession")
@SessionAttributes(value = {"chapter_id","chapter_title"},types = {Integer.class,String.class})
public class ChapterSession {

    /**
     * 向session中存入Chapter的ID和title
     */
    @ResponseBody
    @RequestMapping(path = "/saveChapter/{id}/{title}")
    public String saveChapter(Model model, @PathVariable(value = "id")Integer id, @PathVariable(value = "title")String title){
        //模拟选择章节和后的一些操作，方便从session中直接获取相关数据，而不用频繁访问数据库进行获取
        // 把部分信息放进session域中。

        System.out.println("向session域中存入数据");
        model.addAttribute("chapter_id",id);
        model.addAttribute("chapter_title",title);
//        System.out.println("解码前["+id+"] ["+title+"]");
//        //        name = URLDecoder.decode(name,"UTF-8");
//        title = URLDecoder.decode(title);
//        System.out.println("解码后 name is "+title);
        return "Chapter's Loading is complete";
    }

    /**
     * 从session中获取work的部分值
     */
    @ResponseBody
    @RequestMapping(path = "/getChapter")
    public HashMap<String,Object> getChapter(ModelMap modelMap){
        Integer chapter_id = (Integer)modelMap.get("chapter_id");
        String chapter_title = (String)modelMap.get("chapter_title");
        System.out.println("chapter_id = "+chapter_id+",chapter_title = "+chapter_title);

        HashMap<String,Object> map = new HashMap();
        map.put("chapter_id",chapter_id);
        map.put("chapter_title",chapter_title);
        return map;
    }

    /**
     * 清除session中的值
     * 注意：全部删除，并不能指定删除，其它session例如userSession和worksSession应该为同一个session
     */
    @RequestMapping(path = "/deleteChapter")
    public String delete(SessionStatus status){
        status.setComplete();
        return "test.html";
    }
}
