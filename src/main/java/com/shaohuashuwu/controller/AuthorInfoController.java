package com.shaohuashuwu.controller;


import com.shaohuashuwu.domain.vo.AuthorInfoVo;
import com.shaohuashuwu.service.AuthorInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    /**
     * 获取作者信息
     * 功能点：作者端顶部用户信息，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAuthorInfoVo")
    public AuthorInfoVo getAuthorInfoVo( HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        return authorInfoVoService.getAuthorInfoVo(user_id);
    }

    /**
     * 获取作者信息通过用户点击
     * 功能点：查看作者信息，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAuthorInfoByUserclick")
    public AuthorInfoVo getAuthorInfoByUserclick( HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("author_id");
        int author_id=Integer.parseInt(String.valueOf(msg));
        return authorInfoVoService.getAuthorInfoVo(author_id);
    }
}
