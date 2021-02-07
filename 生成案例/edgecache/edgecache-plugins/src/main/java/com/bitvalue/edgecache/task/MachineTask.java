package com.bitvalue.edgecache.task;

import cn.hutool.core.io.FileUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.*;
import com.bitvalue.edgecache.service.*;
import com.bitvalue.edgecache.thread.MachineRateThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class MachineTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMachineMaintenanceService machineMaintenanceService;
    @Autowired
    private IPrecacheTaskService precacheTaskService;
    @Autowired
    private IMachineRateService machineRateService;
    @Autowired
    private ISafeLogService safeLogService;
    @Autowired
    private IStatisticsService statisticsService;
    /**
     * 每五分钟获取一次所有机器的资源使用率
     */
    @Scheduled(cron = "00 0/5 * * * ?")
    public void saveMachineRate(){
        logger.info("获取机器资源使用率");
        Date date = new Date();
        List<MachineMaintenance> all = machineMaintenanceService.findAll();
        if (all != null){
            for (MachineMaintenance machineMaintenance : all) {
                String ip = machineMaintenance.getIp();
                MachineRateThread machineRateThread = new MachineRateThread(date, ip, machineRateService);
                new Thread(machineRateThread).start();
            }
        }
    }
    /**
     * 每五分钟获取一次机器登录信息
     */
    @Scheduled(cron = "00 0/5 * * * ?")
    public void saveNewLoginLog(){
        logger.info("获取机器访问日志");
        Date date = new Date();
        Map<String, List<String>> map = AnsibleUtils.newLoginLog();
        List<String> success = map.get("success");
        List<String> error = map.get("error");
        List<SafeLog> safeLogs = new ArrayList<>();
        if (success != null && success.size() > 0){
            for (int i = success.size() - 1; i >= 0; i--) {
                String s = success.get(i);
                SafeLog safeLog = new SafeLog();
                safeLog.setUsername(s.substring(0,9));
                safeLog.setTerminal(s.substring(9,22));
                safeLog.setConnected(s.substring(22,39));
                safeLog.setLine(s.substring(39,s.length()));
                safeLog.setStatus("成功");

                safeLogs.add(safeLog);
            }
        }
        if (error != null && error.size() > 0){
            for (int i = error.size() - 1; i >= 0; i--) {
                String s = success.get(i);
                SafeLog safeLog = new SafeLog();
                safeLog.setUsername(s.substring(0,9));
                safeLog.setTerminal(s.substring(9,22));
                safeLog.setConnected(s.substring(22,39));
                safeLog.setLine(s.substring(39,s.length()));
                safeLog.setStatus("失败");
                safeLogs.add(safeLog);
            }
        }
        safeLogService.batchSave(safeLogs);
    }

    /**
     * 预缓存任务执行
     */
    @Scheduled(cron = "00 0/1 * * * ?")
    public void pre_cache_task(){
        logger.info("检测预缓存任务");
        Date date = new Date();
        List<PrecacheTask> precacheTasks = precacheTaskService.findByStatusAndStartTimeLessThanAndEndTimeGreaterThan("等待执行", date, date);
        if (precacheTasks == null || precacheTasks.size() <= 0){
            return;
        }
        for (PrecacheTask precacheTask : precacheTasks) {
            try {
                List<PrecacheTaskDetail> details = precacheTask.getDetails();
                if (details == null || details.size() == 0){
                    continue;
                }
                for (PrecacheTaskDetail detail : details) {
                    AnsibleUtils.precache(detail.getUrl());
                }
                PrecacheTask precacheTask1 = new PrecacheTask();
                precacheTask1.setId(precacheTask.getId());
                precacheTask1.setStatus("已执行");
                precacheTaskService.update(precacheTask1);
            } catch (Exception e) {
                logger.info("预缓存任务失败,id: " + precacheTask.getId());
            }
        }

    }

    /**
     * 每五分钟获取一次dpi重定向数据
     */
    @Scheduled(cron = "00 0/1 * * * ?")
    public void saveRedirect(){
        logger.info("获取DPI重定向数据");
        List<String> sh = FileUtil.readUtf8Lines(Config.custom_path_dpi + "log/total_count.log");
        if (sh != null){
            try {
                Statistics statistics = new Statistics();
                statistics.setType(1);
                String[] split1 = sh.get(0).split(":");
                statistics.setDns(Integer.parseInt(split1[1]));
                String[] split2 = sh.get(1).split(":");
                statistics.setHttp(Integer.parseInt(split2[1]));
                statisticsService.save(statistics);
            } catch (Exception e) {
                logger.error("获取DPI重定向数据出错");
            }
        }
    }
}
