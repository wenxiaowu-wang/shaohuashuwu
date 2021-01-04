package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.WorkslabelInfo;
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
        return workslabelInfoService.getWorkslabelInfoByWork_id(work_id);
    }

    /**
     * 修改作品标签
     * 功能点修改作品信息
     * @param workslabelInfoList
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWorkslabelInfoByWork_id")
    public int updateWorkslabelInfoByWork_id(@RequestBody List<WorkslabelInfo> workslabelInfoList){
        return workslabelInfoService.updateWorkslabelInfoByWork_id(workslabelInfoList);
    }

}
