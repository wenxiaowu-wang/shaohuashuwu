package com.shaohuashuwu.test;

import com.shaohuashuwu.service.CatalogInfoVoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCatalogInfoVo {

    @Autowired
    CatalogInfoVoService catalogInfoVoService;

    @Test
    public void test() throws Exception {
        catalogInfoVoService.getchaptercatalogBywork_id(1,76);
    }

}
