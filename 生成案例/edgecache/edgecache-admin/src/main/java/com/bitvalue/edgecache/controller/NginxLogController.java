package com.bitvalue.edgecache.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.entity.NginxLog;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.INginxLogService;
import com.bitvalue.edgecache.tools.StatisticsUtils;
import com.bitvalue.edgecache.tools.ToolUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * nginxLog控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/nginx_log")
public class NginxLogController extends BaseController<NginxLog,Integer,INginxLogService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("nginx_log/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("nginx_log/form");
        return modelAndView;
    }

    @PostMapping(value = {"/chart"})
    public Result chart(String ip,String start,String end){

        Map map = new HashMap();

        List<Long> times = ToolUtils.allIntervalBetweenTime(start, end);
        List<NginxLog> list = getService().findByServerAddrAndMsecBetween(ip, DateUtil.parse(start),DateUtil.parse(end));
        //时间
        StatisticsUtils statisticsHost = new StatisticsUtils();
        StatisticsUtils statisticsStatusCode = new StatisticsUtils();

        List server = new ArrayList();
        List source = new ArrayList();
        Integer server_max = 0;
        Integer source_max = 0;
        int index = list.size();
        int flag = 0;
        for (Long time : times) {
            int server_count = 0;
            int source_count = 0;
            while (flag < index){
                NginxLog nginxLog = list.get(flag);
                if (nginxLog.getMsec().getTime() <= time){
                    String status = nginxLog.getStatus();
                    statisticsStatusCode.add(status.charAt(0) + "XX");
                    statisticsHost.add(nginxLog.getHost());

                    Integer bytesSent = Integer.parseInt(nginxLog.getBytesSent());
                    server_count += bytesSent;
                    server_max = bytesSent > server_max ? bytesSent : server_max;
                    if (nginxLog.getCacheCode().contains("MISS")){
                        source_count += bytesSent;
                        source_max = bytesSent > source_max ? bytesSent : source_max;
                    }
                    flag++;
                }else{
                    break;
                }
            }
            server.add(server_count);
            source.add(source_count);

        }
        //重定向域名
        List<Object[]> statistics_302_domain = statisticsHost.getChart(10);
        //状态码分布
        List<Object[]> statistics_http_code = statisticsStatusCode.getChart(10);
        map.put("time",times);
        map.put("domain",statistics_302_domain);
        map.put("code",statistics_http_code);
        map.put("server",server);
        map.put("source",source);
        map.put("server_max",server_max);
        map.put("source_max",source_max);


        return Result.success(map);
    }

    @PostMapping("/waf")
    public Result waf(String ip,String start,String end){

        Map map = new HashMap();
        List<Long> times = ToolUtils.allIntervalBetweenTime(start, end);
        List<NginxLog> list = getService().findByServerAddrAndMsecBetween(ip, DateUtil.parse(start),DateUtil.parse(end));
        //时间
        List deny1 = new ArrayList<>();
        List deny2 = new ArrayList<>();
        List deny3 = new ArrayList<>();
        List deny4 = new ArrayList<>();
        List deny5 = new ArrayList<>();
        List deny6 = new ArrayList<>();
        List deny7 = new ArrayList<>();
        List deny8 = new ArrayList<>();
        Integer count_deny1 = 0;
        Integer count_deny2 = 0;
        Integer count_deny3 = 0;
        Integer count_deny4 = 0;
        Integer count_deny5 = 0;
        Integer count_deny6 = 0;
        Integer count_deny7 = 0;
        Integer count_deny8 = 0;
        Integer total1 = 0;
        Integer total2 = 0;
        Integer total3 = 0;
        Integer total4 = 0;
        Integer total5 = 0;
        Integer total6 = 0;
        Integer total7 = 0;
        Integer total8 = 0;
        int index = list.size();
        int flag = 0;
        for (Long time : times) {
            while (flag < index){
                NginxLog nginxLog = list.get(flag);
                if (nginxLog.getMsec().getTime() <= time){
                    Integer denyType = nginxLog.getDenyType();
                    if (denyType != 0){
                        switch (denyType){
                            case 1:count_deny1++;total1++;break;
                            case 2:count_deny2++;total2++;break;
                            case 3:count_deny3++;total3++;break;
                            case 4:count_deny4++;total4++;break;
                            case 5:count_deny5++;total5++;break;
                            case 6:count_deny6++;total6++;break;
                            case 7:count_deny7++;total7++;break;
                            case 8:count_deny8++;total8++;break;
                        }
                    }
                    flag++;
                }else{
                    break;
                }
            }
            deny1.add(count_deny1);
            deny2.add(count_deny2);
            deny3.add(count_deny3);
            deny4.add(count_deny4);
            deny5.add(count_deny5);
            deny6.add(count_deny6);
            deny7.add(count_deny7);
            deny8.add(count_deny8);
            count_deny1 = 0;
            count_deny2 = 0;
            count_deny3 = 0;
            count_deny4 = 0;
            count_deny5 = 0;
            count_deny6 = 0;
            count_deny7 = 0;
            count_deny8 = 0;
        }

        List chartList = new ArrayList();
        chartList.add(deny1);
        chartList.add(deny2);
        chartList.add(deny3);
        chartList.add(deny4);
        chartList.add(deny5);
        chartList.add(deny6);
        chartList.add(deny7);
        chartList.add(deny8);

        List totalList = new ArrayList();
        totalList.add(new Object[]{"IP黑名单",total1});
        totalList.add(new Object[]{"CC攻击",total7});
        totalList.add(new Object[]{"ARG拦截",total3});
        totalList.add(new Object[]{"URL拦截",total2});
        totalList.add(new Object[]{"POST拦截",total5});
        totalList.add(new Object[]{"COOKIE拦截",total4});
        totalList.add(new Object[]{"USERAGENT拦截",total6});
        totalList.add(new Object[]{"其他",total8});

        map.put("time",times);
        map.put("chart",chartList);
        map.put("total",totalList);


        return Result.success(map);
    }


}
