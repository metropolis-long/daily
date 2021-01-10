package com.daily.tool;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Function;

public class DateUtil {


    public static void main(String[] args) {
        System.out.println(getTodayAfterDay(-2));
        System.out.println(getTodayWeekDay(-1));
    }


    public String getOneDayDate(Function<Integer, Date> method, Integer number) {
        Date date = method.apply(number);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
//        calendar.get
        return "";
    }

    /**
     * 获取此时之前之后数周的时间
     *
     * @param week 相差天数，负数时间前移
     * @return
     */
    public static Date getTodayWeekDay(Integer week) {
        Date date = new Date();//取时间
        if (week == null) {
            return date;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.WEEK_OF_MONTH, week);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }
    /**
     * 获取此时之前之后数周的时间
     *
     * @param week 相差天数，负数时间前移
     * @return
     */
    public static Date getDayWeekDay(Date date  ,Integer week) {
        if (week == null) {
            return date;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.WEEK_OF_MONTH, week);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }
    /**
     * 获取此时之前之后数天的时间
     *
     * @param day 相差天数
     * @return
     */
    public static Date getTodayAfterDay(Integer day) {
        Date date = new Date();//取时间
        if (day == null) {
            return date;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
//        calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
//        calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
//        calendar.add(calendar.DAY_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }
}
