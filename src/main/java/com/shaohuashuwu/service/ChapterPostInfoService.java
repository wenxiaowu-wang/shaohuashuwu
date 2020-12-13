package com.shaohuashuwu.service;


public interface ChapterPostInfoService {

    //查询作品章节数,判断章节是否存在
    public int getwork_idNum(int work_id);

    //依据作品名称，查询作品章节数
    public int getchapterNumBywork_name(String work_name);


}
