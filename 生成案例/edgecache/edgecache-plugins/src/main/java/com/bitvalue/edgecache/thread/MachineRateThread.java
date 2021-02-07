package com.bitvalue.edgecache.thread;

import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.MachineRate;
import com.bitvalue.edgecache.service.IMachineRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MachineRateThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(MachineRateThread.class);

    private Date date;
    private String ip;
    private IMachineRateService machineRateService;

    public MachineRateThread(Date date, String ip, IMachineRateService machineRateService) {
        this.date = date;
        this.ip = ip;
        this.machineRateService = machineRateService;
    }

    @Override
    public void run() {
        try {
            MachineRate machineRate = AnsibleUtils.getMachineRate(ip);
            if (machineRate != null){
                machineRateService.save(machineRate);
            }
        } catch (Exception e) {
            logger.error("获取机器资源使用率失败,ip = " +ip + "错误信息: " + e.getMessage());
        }
    }
}
