package com.bitvalue.edgecache.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.service.IMachineMaintenanceService;
import com.bitvalue.edgecache.service.IMachineRateService;
import com.bitvalue.edgecache.service.INginxLogService;
import com.bitvalue.edgecache.service.ISysLogService;
import com.bitvalue.edgecache.tools.ToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class LogTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMachineRateService machineRateService;
    @Autowired
    private INginxLogService nginxLogService;
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 每天定时删除n天前Nginx和资源使用率记录
     */
    @Scheduled(cron = "00 00 01 * * ?")
    public void deleteNginxRecodeBefore(){
        Date dateBefore = ToolUtils.getDateBefore(Config.custom_savetime_nginx);

        logger.info("删除七天前的Nginx记录");
        nginxLogService.deleteByMsecBefore(dateBefore);
        logger.info("删除七天前的机器资源使用率");
        machineRateService.deleteByTimeBefore(dateBefore);
    }

    /**
     * 每天定时删除n天前的日志记录
     */
    @Scheduled(cron = "00 00 01 * * ?")
    public void deleteSaveRecodeBefore(){
        Date dateBefore = ToolUtils.getDateBefore(Config.custom_savetime_safe);
        sysLogService.deleteByTimeBefore(dateBefore);
    }

}
