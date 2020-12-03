package com.shaohuashuwu.test;

import com.shaohuashuwu.domain.vo.RankingInfoVo;
import com.shaohuashuwu.domain.vo.RankingInputInfoVo;
import com.shaohuashuwu.service.RankingInfoVoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestRankingInfoVo {

    @Autowired
    RankingInfoVoService rankingInfoVoService;

    RankingInputInfoVo rankingInputInfoVo;

    @Test
    public void testSelectListInfo(){

        rankingInputInfoVo = new RankingInputInfoVo(null,3,"1");
        System.out.println("显示结果"+rankingInfoVoService.getRankingListInfo(rankingInputInfoVo));
    }
}
