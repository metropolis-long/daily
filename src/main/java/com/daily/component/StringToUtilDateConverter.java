package com.daily.component;

import com.daily.tool.NullUtil;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @ClassName StringToUtilDateConverter
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/26 21:09
 * @VERSION 1.0.0
 */

//@Component
public class StringToUtilDateConverter implements Converter<String, Date> { // 这里的<String, Date>表示从string转换到Date

    static final String YYYY_MM_DD = "yyyy-MM-dd";
    static final String REG_YYYY_MM_DD = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}";
    static final String REG_NUMBER = "[0-9]{1,}";
    static final String YYYY_MM_DD2 = "yyyy/MM/dd";
    static final String REG_YYYY_MM_DD2 = "[0-9]{4}[/]{1}[0-9]{2}[/]{1}[0-9]{2}";
    @Override
    public Date convert(String timeStr) {
        if(NullUtil.isNull(timeStr)) {
            return null;
        }
        timeStr = timeStr.trim();

        try {
            DateTimeFormatter formatter;
            if(Pattern.matches(StringToUtilDateConverter.REG_YYYY_MM_DD, timeStr)) {
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
            }else if(Pattern.matches(StringToUtilDateConverter.REG_YYYY_MM_DD2, timeStr)) {
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD2);
            }else if(Pattern.matches(StringToUtilDateConverter.REG_NUMBER, timeStr)) {
                Long lDate = new Long(timeStr);
                return new Timestamp(lDate);
            } else {
                throw new RuntimeException(String.format("parser %s to Date fail", timeStr));
            }
            LocalDate localDate = LocalDate.parse(timeStr, formatter);
            Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);
            return date;
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", timeStr));
        }
    }


}