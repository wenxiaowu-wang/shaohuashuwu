package com.shaohuashuwu.util;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取系统当前时间
 */
@Service("thisCurrentTime")
public class ThisCurrentTime {


    /**
     * 获取当前月份
     * @return
     */
    public String currentMonthTime(){
        //设置日期格式，月
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        String date = df.format(new Date());
        return date;
    }

    /**
     * 获取当前日期
     * @return
     */
    public String currentDayTime(){
        //设置日期格式，日
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        return date;
    }


    /**
     * 获取当前具体时间点
     * @return
     */
    public String currentTime() {
        //设置日期格式，秒
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        return date;
    }


}
