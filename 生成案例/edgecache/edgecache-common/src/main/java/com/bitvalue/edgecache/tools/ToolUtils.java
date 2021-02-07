package com.bitvalue.edgecache.tools;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wubo
 */
public class ToolUtils {

    /*** 获取用户的真实IP,防止代理 */
    public static String getRequestIp(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static Date getDateBefore(Integer before){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE,-(before - 1));
        return calendar.getTime();
    }


    public static Integer getInterval(Date start,Date end){
        long betweenHours = DateUtil.between(start, end, DateUnit.HOUR);
        if (betweenHours <= 24){
            return 5;
        }
        if (betweenHours <= 48){
            return 10;
        }
        if (betweenHours <= 168){
            return 60;
        }
        return 1440;
    }

    public static List<Long> allIntervalBetweenTime(String start, String end) {
        try {
            Date startDate = DateUtil.parse(start);
            Date endDate = DateUtil.parse(end);
            return allIntervalBetweenTime(startDate,endDate,getInterval(startDate,endDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static List<Long> allIntervalBetweenTime(String start, String end,String format,Integer interval) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return allIntervalBetweenTime(df.parse(start),df.parse(end),interval);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Long> allIntervalBetweenTime(Date start, Date end, Integer interval){
        List<Long> times = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        startCalendar.add(Calendar.MINUTE, interval);
        endCalendar.setTime(end);
        while (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
            times.add(startCalendar.getTimeInMillis());
            startCalendar.add(Calendar.MINUTE, interval);
        }
        if (startCalendar.getTimeInMillis() >= endCalendar.getTimeInMillis()){
            times.add(endCalendar.getTimeInMillis());
        }
        return times;
    }


}
