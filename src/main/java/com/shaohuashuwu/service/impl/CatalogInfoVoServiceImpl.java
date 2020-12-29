package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.CatalogInfoVoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.CatalogInfoVo;
import com.shaohuashuwu.service.CatalogInfoVoService;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("catalogInfoVoService")
public class CatalogInfoVoServiceImpl implements CatalogInfoVoService {

    @Autowired
    private CatalogInfoVoDao catalogInfoVoDao;

    @Autowired
    private WorksInfoService worksInfoService;


    WorksInfo worksInfo;


    /**
     * 依据用户id和作品id，获取章节目录信息
     * 功能点：作品详情时获取目录新信息，阅读小说界面获取目录信息,
     * @param user_id
     * @param work_id
     * @return
     */
    @Override
    public List<CatalogInfoVo> getchaptercatalogBywork_id(int user_id, int work_id) throws Exception{

        System.out.println("用户---------"+user_id+"作品id："+work_id);
        WorksInfo worksInfo = new WorksInfo(work_id,null,user_id);
        return catalogInfoVoDao.selectchaptercatalogBywork_id(worksInfo);
    }

    /**
     * 依据章节id，获取章节目录信息
     * 功能点：阅读小说界面获取目录信息
     * @param user_id
     * @param chapter_id
     * @return
     * @throws Exception
     */
    @Override
    public List<CatalogInfoVo> getchaptercatalogBychapter_id(int user_id, int chapter_id) throws Exception {

        worksInfo = worksInfoService.getworkInfoByChapter_id(chapter_id);

        return getchaptercatalogBywork_id(user_id,worksInfo.getWork_id());
    }

    /**
     * 依据用户id和作品id，获取章节目录信息，包括被下架章节
     * 功能点：添加作品获取章节信息
     * @param work_id
     * @return
     */
    @Override
    public List<CatalogInfoVo> getchaptercatalogBywork_id2(int work_id) {
        return catalogInfoVoDao.selectchaptercatalogBywork_id2(work_id);
    }
}
