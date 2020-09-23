package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户web
 */
@Controller
@RequestMapping("/account")
public class AccountControler {

    @Autowired
    private AccountService accountService;
    @RequestMapping("/findAll")
    public String finAll(Model model){

        System.out.println("表现层输出-----------------========");

        List<Account> list = accountService.findAll();
        for (Account account:list){
            System.out.println(account);
        }

        model.addAttribute("list",list);
        accountService.findAll();





        return "list";
    }

    @RequestMapping("/save")
    public void finAll(Account account, HttpServletRequest request,HttpServletResponse response) throws IOException {

        System.out.println("查询-----------------========");

        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return ;
    }

    @RequestMapping("/test")
    public String test(Model model){

        System.out.println("跳转-----------------========");
        return "authorMainFragment";
    }

    @RequestMapping("/testtwo")
    public String testtwo(Model model){

        System.out.println("跳转222222");
        return "authorMainFragment";
    }


//    @RequestMapping("/testtwo")
//    public String testtwo(Model model){
//
//        System.out.println("跳转222222");
//        return "authorMian";
//    }



}
