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
        System.out.println("进入啦啦啦啦啦啦");
        System.out.println("选择类型：");
        System.out.println("选择类型---"+rankingInputInfoVo.getTransaction_type());
        System.out.println("日期类型===="+rankingInputInfoVo.getTime_type());
        System.out.println("输入信息"+rankingInputInfoVo);
//        System.out.println("输出数据"+rankingInfoVoService.getRankingInfo(rankingInputInfoVo));

        return rankingInfoVoService.getRankingInfo(rankingInputInfoVo);
    }












    /****************以下未修改*/


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
