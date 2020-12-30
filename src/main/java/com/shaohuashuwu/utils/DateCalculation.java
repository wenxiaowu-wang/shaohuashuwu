package com.shaohuashuwu.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 包:com.shaohuashuwu.utils
 * 作者:王洪斌
 * 日期:2020/11/24
 * 项目:shaohuashuwu
 * 描述:日期计算的工具类 包括string 转化为date 两个date比较差等
 */
public class DateCalculation {

    private Date startDate;
    private Date endDate;
    private String dateFormat;
    private SimpleDateFormat simpleDateFormat;

    public DateCalculation() {
    }

    public DateCalculation(String dateFormat) {
        this.dateFormat = dateFormat;
        this.simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    public DateCalculation(String time1,String time2,String dateFormat){
        this.simpleDateFormat = new SimpleDateFormat(dateFormat);
        try{
            this.startDate = this.simpleDateFormat.parse(time1);
            this.endDate = this.simpleDateFormat.parse(time2);
        }catch (ParseException e){
            e.printStackTrace();
            System.out.println("日期格式化失败");
        }
    }
    public DateCalculation(Date startDate, Date endDate, String dateFormat) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateFormat = dateFormat;
        this.simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    //比较a是否大于b两个日期之间的大小a>b?true:false;
    public boolean compareTime(String time_a,String time_b){

        long time1 = this.stringToDate(time_a).getTime();
        long time2 = this.stringToDate(time_b).getTime();
        return time1 > time2;
    }

    //比较两个Date类型的属性之间的大小;
    public boolean compareTime(){

        long time1 = this.startDate.getTime();
        long time2 = this.endDate.getTime();
        return time1 > time2;
    }

    //计算两个日期之间的差值(返回天数)
    public int getDateDifferenceDayNum(){
        long left = this.compareTime() ? this.startDate.getTime() : this.endDate.getTime();
        long right = this.compareTime() ? this.endDate.getTime() : this.startDate.getTime();
        long nd = 1000 * 24 * 60 * 60;
        long diff = left - right;
        return (int)(diff / nd);
    }

    //计算某个时间的后多少天的日期（为负值时为前几天）
    public String getCertainTime(String date,int dayNum){
        String result = null;
        try {
            Date date1 = this.simpleDateFormat.parse(date);
            Calendar now = Calendar.getInstance();
            now.setTime(date1);
            now.add(Calendar.DAY_OF_MONTH,dayNum);
            Date date2 = now.getTime();
            result = this.simpleDateFormat.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("日期转化失败,002");
        }
        return result;
    }

    //String转化为yyyy-MM-dd类型的date数据
    public Date stringToDate(String timeStr){
        try {
            return this.simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取当前时间返回规定格式字符串
    public String getRightNow(){
        return this.simpleDateFormat.format(new Date());
    }

    //获取开始到结束时间的每一天日期
    public List<String> getAllDayBetweenTwo(){
        List<String> days = new ArrayList<String>();
        String startDay = this.simpleDateFormat.format(this.startDate);
        days.add(startDay);
        int dayNum = this.getDateDifferenceDayNum();
        for (int i=1;i<=dayNum;i++){
            days.add(this.getCertainTime(startDay,i));
        }
        return  days;
    }

    //String转化为Timestamp
    public Timestamp stringToTimestamp(String time){
        return new Timestamp(this.stringToDate(time).getTime());
    }
}
