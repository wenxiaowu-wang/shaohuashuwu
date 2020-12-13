package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.AuthorInfoVoDao;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AuthorInfoVo;
import com.shaohuashuwu.service.AuthorInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorInfoServiceImpl")
public class AuthorInfoServiceImpl implements AuthorInfoVoService {

    @Autowired
    private AuthorInfoVoDao authorInfoVoDao;

    public UserInfo userInfo;



    /**
     * 获取作者信息
     * 功能点：作者端顶部用户信息，
     * @param user_id
     * @return
     */
    @Override
    public AuthorInfoVo getAuthorInfoVo(int user_id) {

        return authorInfoVoDao.selectAuthorInfoVoByuser_id(user_id);
    }
}
