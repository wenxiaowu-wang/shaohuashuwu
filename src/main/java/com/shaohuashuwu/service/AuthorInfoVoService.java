package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.AuthorInfoVo;

public interface AuthorInfoVoService {

    //查询作者信息
    public AuthorInfoVo getAuthorInfoVo(int user_id);
}
