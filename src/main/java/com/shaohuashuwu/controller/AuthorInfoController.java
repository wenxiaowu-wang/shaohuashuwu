package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.AuthorInfoVo;
import com.shaohuashuwu.service.AuthorInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/authorInfoController")
public class AuthorInfoController {

    @Autowired
    private AuthorInfoVoService authorInfoVoService;
    private AuthorInfoVo authorInfoVo;

    @ResponseBody
    @RequestMapping("/selectAuthorInfoVo")
    public AuthorInfoVo selectAuthorInfoVo( HttpServletRequest request, HttpServletResponse response){

//        System.out.println();

        authorInfoVo = authorInfoVoService.selectAuthorInfoVo(11);

        System.out.println("selectAuthorInfoVo测试输出数据"+authorInfoVo);
        return authorInfoVo;
    }
}
