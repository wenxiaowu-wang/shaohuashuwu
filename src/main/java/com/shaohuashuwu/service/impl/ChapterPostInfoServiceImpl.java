package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.service.ChapterPostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chapterPostInfoService")
public class ChapterPostInfoServiceImpl implements ChapterPostInfoService {

    @Autowired
    private ChapterPostInfoDao chapterPostInfoDao;





}
