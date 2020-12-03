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
    @RequestMapping("/getAuthorInfoVo")
    public AuthorInfoVo getAuthorInfoVo( HttpServletRequest request, HttpServletResponse response){


        HttpSession session = request.getSession();
        session.setAttribute("user_id",1);
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));

        return authorInfoVoService.getAuthorInfoVo(user_id);
    }
}
