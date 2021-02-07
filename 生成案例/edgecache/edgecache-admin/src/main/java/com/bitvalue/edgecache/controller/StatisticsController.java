package com.bitvalue.edgecache.controller;

import cn.hutool.core.date.DateUtil;
import com.bitvalue.edgecache.entity.NginxLog;
import com.bitvalue.edgecache.entity.Statistics;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IStatisticsService;
import com.bitvalue.edgecache.tools.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * statistics控制器层
 * @author wb
 */
@RestController
@CrossOrigin
@RequestMapping("/statistics")
public class StatisticsController extends BaseController<Statistics,Integer,IStatisticsService>{
    
    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("statistics/list");
        return modelAndView;
    }
    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("statistics/form");
        return modelAndView;
    }


    @GetMapping("/redirect/{start}/{end}")
    public Result redirect(@PathVariable String start,@PathVariable String end){

        List<Long> times = ToolUtils.allIntervalBetweenTime(start, end);
        List<Statistics> list = getService().findByTypeAndTimeBetween(1,DateUtil.parse(start),DateUtil.parse(end));

        List http = new ArrayList();
        List dns = new ArrayList();
        int http_total = 0;
        int dns_total = 0;
        int index = list.size();
        int flag = 0;
        for (Long time : times) {
            int http_count = 0;
            int dns_count = 0;
            while (flag < index){
                Statistics statistics = list.get(flag);
                if (statistics.getTime().getTime() <= time){
                    http_count += statistics.getHttp();
                    dns_count += statistics.getDns();
                }else{
                    break;
                }
            }
            http.add(http_count);
            dns.add(dns_count);
            http_total += http_count;
            dns_total += dns_count;
        }

        Map map = new HashMap();
        map.put("time",times);
        map.put("http",http);
        map.put("dns",dns);
        map.put("http_total",http_total);
        map.put("dns_total",dns_total);

        return Result.success(map);
    }

}
