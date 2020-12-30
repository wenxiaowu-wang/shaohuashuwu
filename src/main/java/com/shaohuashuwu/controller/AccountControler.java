package com.shaohuashuwu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.service.AccountService;
import com.shaohuashuwu.service.WorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.function.Predicate;

/**
 * 账户web
 */
@Controller
@RequestMapping("/account")
public class AccountControler {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WorksInfoService worksInfoService;


    @RequestMapping("/findAll")
    public String finAll(Model model){

        System.out.println("表现层输出-----------------========");

        List<Account> list = accountService.findAll();
        for (Account account:list){
            System.out.println(account);
        }

        model.addAttribute("list",list);
        return "list.jsp";
    }

    @RequestMapping("/save")
    public void finAll(Account account, HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("查询-----------------========");
        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return ;
    }


//    @RequestMapping("/findworksAll")
//    public String findworksAll(Model model){
//
//        System.out.println("测试1");
//        List list1 = worksInfoService.selectAllworks();
//        System.out.println(list1);
//        List<WorksInfo> list = worksInfoService.selectAllworks();
//        System.out.println("测试2");
//        System.out.println(list);
//        model.addAttribute("list",list);
//
//        return "testworks.jsp";
//    }

//    @ResponseBody
//    @RequestMapping("/findallworksdate")
//    public List<WorksInfo> findalldate(){
//
//        System.out.println("测试输出数据");
//        List<WorksInfo> list = worksInfoService.selectAllworks();
//        System.out.println(list);
//        return list;
//    }

    @RequestMapping("/findallworks")
    public String findallworks(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "worksMangagementInterface.html";
    }

    @RequestMapping("/addfindallworks")
    public String addfindallworks(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "newlyCreatedWorksInterface.html";
    }

    @RequestMapping("/usermain")
    public String usermain(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "userMainInterface.html";
    }
    @RequestMapping("/mangagement")
    public String mangagement(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "worksMangagementInterface.html";
    }

    @RequestMapping("/addworkInfo")
    public String addworksdate(@RequestBody WorksInfo worksInfodata) {

        System.out.println("添加书籍-------------");
        System.out.println(worksInfodata);
        worksInfoService.addworkInfo(worksInfodata,11);
        return  "forward:/account/findallworks";
    }

    @ResponseBody
    @RequestMapping(value = "/isworkname")
    public int isworkname(@RequestBody WorksInfo worksInfodata) {


        System.out.println("查找书籍-------------");
        String works_name = worksInfodata.getWork_name();
        System.out.println("---"+works_name+"------");
        System.out.println(worksInfodata);

        int num = worksInfoService.isworkname(works_name);
        System.out.println("controller层显示结果:"+num);


        return  num;
    }

    @RequestMapping("/adminhtml")
    public String adminhtml(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "adminLoginInterface.html";
    }

    @RequestMapping("/jubao")
    public String jubao(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "handlingReportInfoInterface.html";
    }

    @RequestMapping("/chuli")
    public String chuli(){


        System.out.println("zzzzzzzzzzzzz--------");
        return "changeTheProcessingResultInterface.html";
    }

    @RequestMapping("/paihang")
    public String paihang(){

        System.out.println("zzzzzzzzzzzzz--------");
        return "novelRankingInterface.html";
    }

    @RequestMapping("/loginusermain")
    public String loginusermain(){

        System.out.println("zzzzzzzzzzzzz--------");
        return "UserLogin.html";
    }

}
