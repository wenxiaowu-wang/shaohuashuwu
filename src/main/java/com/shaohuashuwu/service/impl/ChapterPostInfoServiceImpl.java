package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.service.ChapterPostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chapterPostInfoService")
public class ChapterPostInfoServiceImpl implements ChapterPostInfoService {

    @Autowired
    private ChapterPostInfoDao chapterPostInfoDao;


    /**
     * 查询作品章节数量，判断作品是否创建了章节
     * @param work_id
     * @return
     */
    @Override
    public int getwork_idNum(int work_id) {
        return chapterPostInfoDao.selectchapterNum(work_id);
    }

    /**
     * 依据作品名称，查询作品章节数
     * @param work_name
     * @return
     */
    @Override
    public int getchapterNumBywork_name(String work_name) {
        return chapterPostInfoDao.getchapterNumBywork_name(work_name);
    }




}
