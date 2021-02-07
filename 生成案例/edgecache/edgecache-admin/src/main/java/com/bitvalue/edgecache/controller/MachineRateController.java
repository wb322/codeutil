package com.bitvalue.edgecache.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.SystemUtil;
import com.bitvalue.edgecache.entity.MachineRate;
import com.bitvalue.edgecache.entity.NginxLog;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineRateService;
import com.bitvalue.edgecache.tools.ToolUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * machineRate控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine_rate")
public class MachineRateController extends BaseController<MachineRate,Integer,IMachineRateService>{


    @PostMapping("/chart")
    public Result getData(String ip, String start, String end){
        if (ip == null){
            HostInfo hostInfo = SystemUtil.getHostInfo();
            ip = hostInfo.getAddress();
        }
        List<Long> times = ToolUtils.allIntervalBetweenTime(start, end);
        List<MachineRate> rates = getService().chart(ip,DateUtil.parse(start),DateUtil.parse(end));

        Map map = new HashMap();
        List cpu = new ArrayList();
        List memory = new ArrayList();
        List disk = new ArrayList();
        if (rates != null){
            int index = rates.size();
            int flag = 0;
            for (Long time : times) {
                int sub = 0;
                Double cpuTotal = 0.0;
                Double memoryTotal = 0.0;
                Double diskTotal = 0.0;
                while (flag < index){
                    MachineRate rate = rates.get(flag);
                    if (rate.getTime().getTime() <= time){
                        cpuTotal += rate.getCpu();
                        memoryTotal += rate.getMemoryRate();
                        diskTotal += rate.getDiskRate();
                        flag++;
                        sub++;
                    }else{
                        break;
                    }
                }
                sub = sub == 0 ? 1 : sub;
                cpu.add(String.format("%.2f", cpuTotal/sub));
                memory.add(String.format("%.2f", memoryTotal/sub));
                disk.add(String.format("%.2f", diskTotal/sub));
            }
        }
        map.put("time",times);
        map.put("cpu",cpu);
        map.put("memory",memory);
        map.put("disk",disk);
        return Result.success(map);
    }
}
