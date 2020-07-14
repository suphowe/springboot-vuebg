package com.soft.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 时间公共类
 * @author suphowe
 */
public class DateUtil {

    /**
     * LocalDateTime转Date
     * @param localDateTime LocalDateTime时间
     * @return Date时间
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        return date;
    }
}
