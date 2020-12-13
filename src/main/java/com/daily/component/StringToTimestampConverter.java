package com.daily.component;

import com.daily.tool.NullUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * @ClassName StringToTimestampConverter
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/26 21:05
 * @VERSION 1.0.0
 */
//@Component
public class StringToTimestampConverter implements Converter<String, Timestamp> { // 这里的<String, Timestamp>表示从string转换到timestamp

    static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    static final String REG_YYYY_MM_DD_HH_MM_SS_SSS = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}[ ]{1}[0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[.]{1}[0-9]{3}";

    static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
    static final String REG_YYYY_MM_DD_HH_MM_SS_S = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}[ ]{1}[0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[.]{1}[0-9]{1}";

    static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    static final String REG_YYYY_MM_DD_HH_MM_SS = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}[ ]{1}[0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}";

    static final String YYYY_MM_DD = "yyyy-MM-dd";
    static final String REG_YYYY_MM_DD = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}";
    static final String REG_NUMBER = "[0-9]{1,}";

    @Override
    public Timestamp convert(String timeStr) {
        if(NullUtil.isNull(timeStr)) {
            return null;
        }
        timeStr = timeStr.trim();

        try {
            DateTimeFormatter formatter;
            if(Pattern.matches(StringToTimestampConverter.REG_YYYY_MM_DD_HH_MM_SS_SSS, timeStr)){
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS);
            } else if(Pattern.matches(StringToTimestampConverter.REG_YYYY_MM_DD_HH_MM_SS_S, timeStr)){
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_S);
            } else if(Pattern.matches(StringToTimestampConverter.REG_YYYY_MM_DD_HH_MM_SS, timeStr)) {
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
            } else if(Pattern.matches(StringToTimestampConverter.REG_YYYY_MM_DD, timeStr)) {
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
                LocalDate localDate = LocalDate.parse(timeStr, formatter);
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                Timestamp timestamp = Timestamp.from(instant);
                return timestamp;
            } else if(Pattern.matches(StringToTimestampConverter.REG_NUMBER, timeStr)) {
                Long lDate = new Long(timeStr);
                return new Timestamp(lDate);
            } else {
                throw new RuntimeException(String.format("parser %s to Date fail", timeStr));
            }
            LocalDateTime localDateTime = LocalDateTime.parse(timeStr, formatter);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            return timestamp;
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", timeStr));
        }
    }


}