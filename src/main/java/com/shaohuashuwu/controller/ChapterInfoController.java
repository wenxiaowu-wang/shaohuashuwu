package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.NoticeInfo;
import com.shaohuashuwu.service.ChapterInfoService;
import com.shaohuashuwu.service.NoticeInfoService;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/chapterInfoController")
public class ChapterInfoController {

    @Autowired
    public ChapterInfoService chapterInfoService;

    @Autowired
    public NoticeInfoService noticeInfoService;

    @Autowired
    public WorksInfoService worksInfoService;

    /**
     * 根据作品id查询最新章节信息
     * 功能点：作品详情时获取最新章节信息，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getnewChapterInfoByword_id")
    public ChapterInfo getnewChapterInfoByword_id(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id1");
        int work_id=Integer.parseInt(String.valueOf(msg));
        return chapterInfoService.getnewChapterInfoByword_id(work_id);
    }

    /**
     * 将章节id保存进入session
     * @param chapter_id
     * @param request
     * @param response
     */
    @RequestMapping("/saveChapter_idSession")
    public void saveChapter_idSession(Integer chapter_id, HttpServletRequest request, HttpServletResponse response)  {

        System.out.println("存储章节id："+chapter_id);
        HttpSession session = request.getSession();
        session.setAttribute("chapter_id",chapter_id);
    }

    /**
     * 根据章节id获取章节信息
     * 功能点：阅读小说界面获取章节信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getChapterInfoByChapter_id")
    public ChapterInfo getChapterInfoByChapter_id(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("chapter_id");
        int chapter_id=Integer.parseInt(String.valueOf(msg));
        Object msg2 = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg2));
        return chapterInfoService.getchapterInfoByChapter_id(chapter_id,user_id);
    }

    /**
     * 新建章节
     * 功能点：添加章节功能添加章节
     * @param chapterInfo
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addchapter_info")
    public int addchapter_info(@RequestBody ChapterInfo chapterInfo, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id1");
        int work_id=Integer.parseInt(String.valueOf(msg));

        Object user_id_o = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(user_id_o));
        int num = chapterInfoService.addchapter_info(chapterInfo,work_id,user_id);
        //发布成功 发送该作品的更新提示
        if(num == 1){
            String work_name = worksInfoService.getworkInfoByWork_id(work_id).getWork_name();
            NoticeInfo noticeInfo = new NoticeInfo();
            noticeInfo.setSend_by(work_id);//发送人：作品id
            noticeInfo.setSend_to(-1);//接收人 -1 表示群体
            noticeInfo.setNotice_type(2);//通知类型2 表示更新提醒
            noticeInfo.setNotice_content("《"+work_name+"》 "+chapterInfo.getChapter_title()+" 已更新，快来观看吧！");
            noticeInfo.setNotice_title("《"+work_name+"》已更新！");
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            noticeInfo.setSend_time(timestamp);
            noticeInfo.setNotice_tip(1);

            boolean theResult = noticeInfoService.addOrUpdateWorkUpdateNotice(noticeInfo);
        }
        return num;
    }



    /*
    * 郝振威
    *
    *
    * */

    /**
     * session中获取章节id
     */
    @ResponseBody
    @RequestMapping(path = "/getChapterId")
    public HashMap<String,Object> getChapterId(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Object msg1 = session.getAttribute("chapter_id");
        int chapter_id=Integer.parseInt(String.valueOf(msg1));

        HashMap<String,Object> map = new HashMap();
        map.put("chapter_id",chapter_id);
        return map;
    }


    //根据用户的ID，作品的ID 获取该用户未订阅章节的信息
    @RequestMapping(path = "/getChapterInfoByUserIdWorkId/{work_id}/{user_id}")
    @ResponseBody
    public List<ChapterInfo> getChapterInfoByUserIdWorkId(@PathVariable(value = "work_id")Integer work_id, @PathVariable(value = "user_id")Integer user_id){
        List<ChapterInfo> chapterInfo = chapterInfoService.getChapterInfoByUserIdWorkId(work_id,user_id);
        return chapterInfo;
    }

    //根据用户的ID，作品的ID 获取该用户未订阅章节数量
    @RequestMapping(path = "/getChapterCountByUserIdWorkId/{work_id}")
    @ResponseBody
    public int getChapterCountByUserIdWorkId(@PathVariable(value = "work_id")Integer work_id){
        return chapterInfoService.getChapterCountByUserIdWorkId(work_id);
    }

    //根据用户的ID，作品的ID 获取该用户已订阅章节的信息
    @RequestMapping(path = "/getChapterInfoByUserIdWorkId2/{work_id}/{user_id}")
    @ResponseBody
    public List<ChapterInfo> getChapterInfoByUserIdWorkId2(@PathVariable(value = "work_id")Integer work_id,@PathVariable(value = "user_id")Integer user_id){
        List<ChapterInfo> chapterInfo = chapterInfoService.getChapterInfoByUserIdWorkId2(work_id,user_id);
        return chapterInfo;
    }

}
