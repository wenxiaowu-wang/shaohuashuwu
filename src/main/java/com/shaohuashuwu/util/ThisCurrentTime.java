package com.shaohuashuwu.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取系统当前时间
 */
@Service("thisCurrentTime")
public class ThisCurrentTime {


    public String currentTime() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
//        System.out.println(date);

        return date;
    }


}
