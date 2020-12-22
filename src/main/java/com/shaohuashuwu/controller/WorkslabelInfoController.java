package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.WorkslabelInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import com.shaohuashuwu.service.WorkslabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/workslabelInfoController")
public class WorkslabelInfoController {

    @Autowired
    private WorkslabelInfoService workslabelInfoService;

    /**
     * 依据作品id查询作者自定义标签
     * 功能点：修改作品信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWorkslabelInfoByWork_id")
    public List<WorkslabelInfo> getWorkslabelInfoByWork_id(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id");

        int work_id=Integer.parseInt(String.valueOf(msg));

        System.out.println("获取用户自定义标签"+work_id);
        System.out.println("自定义标签内容："+workslabelInfoService.getWorkslabelInfoByWork_id(work_id));
        return workslabelInfoService.getWorkslabelInfoByWork_id(work_id);
    }

    @ResponseBody
    @RequestMapping("/updateWorkslabelInfoByWork_id")
    public int updateWorkslabelInfoByWork_id(@RequestBody List<WorkslabelInfo> workslabelInfoList,HttpServletRequest request, HttpServletResponse response){
        System.out.println("被调用了------------------------------------");

        System.out.println("自定义标签信息"+workslabelInfoList);

        return workslabelInfoService.updateWorkslabelInfoByWork_id(workslabelInfoList);
    }

}
