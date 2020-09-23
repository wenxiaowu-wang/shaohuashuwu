package com.shaohuashuwu.test;

import com.shaohuashuwu.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 包:com.shaohuashuwu.test
 * 作者:王洪斌
 * 日期:2020/9/16
 * 项目:shaohuashuwu
 * 描述:
 */
public class lalala {

    @Autowired
    private AdminInfoService adminInfoService;

    public void deleteTest(){
        if (adminInfoService.deleteAdminInfo("hello")){
            System.out.println("test over");
        }
    }

    public static void main(String[] args) {
//        if (1 != (-1)){
//            System.out.println("lalala");
//        }
//        if(1 + (-1) != 0){
//            System.out.println("ahhaha");
//        }
//        System.out.println(1+(-1));
        lalala la =  new lalala();
        System.out.println("/****************/");

        la.deleteTest();

    }
}
