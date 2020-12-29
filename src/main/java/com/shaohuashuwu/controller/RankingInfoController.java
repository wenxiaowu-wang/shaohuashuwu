package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import com.shaohuashuwu.service.RankingInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/rankingInfoController")
public class RankingInfoController {
    @Autowired
    private RankingInfoVoService rankingInfoVoService;

    private RankingInfoVo rankingInfoVo;

    /**
     * 获取排行信息
     * 功能点：获取排行信息
     * @param rankingInputInfoVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRankingInfo")
    public List<RankingInfoVo> getRankingInfo(@RequestBody RankingInputInfoVo rankingInputInfoVo) {
        return rankingInfoVoService.getRankingInfo(rankingInputInfoVo);
    }

}
