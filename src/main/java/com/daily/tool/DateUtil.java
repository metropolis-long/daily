package com.daily.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    /**
     *
     * @param dateStr "2005-12-15 15:30:23"
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date getDateFromStr(String dateStr,String format){
        //创建SimpleDateFormat对象实例并定义好转换格式
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        System.out.println("把当前时间转换成字符串：" + sdf.format(new Date()));

        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     *
     * @param date "2005-12-15 15:30:23"
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getDateStr2Date(Date date,String format){
        if (date== null){
            throw new NullPointerException("");
        }
        LocalDateTime localDateTime= date.toInstant()
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        //格式化日期时间类型为字符串
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(format);
        String ss = dateTimeFormatter.format(localDateTime).toString();
        return ss;
    }
}
