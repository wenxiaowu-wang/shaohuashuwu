package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.ChapterInfo;
import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import com.shaohuashuwu.service.RankingInfoVoService;
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
@RequestMapping("/rankingInfoController")
public class RankingInfoController {
    @Autowired
    private RankingInfoVoService rankingInfoVoService;

    private RankingInfoVo rankingInfoVo;


    /**
     * 分页获取排行信息
     * @param rankingInputInfoVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRankingListInfo")
    public List<RankingInfoVo> getRankingListInfo(@RequestBody RankingInputInfoVo rankingInputInfoVo) {
        System.out.println("进入/rankingInfoController/getRankingListInfo");
        return rankingInfoVoService.getRankingListInfo(rankingInputInfoVo);
    }



}
