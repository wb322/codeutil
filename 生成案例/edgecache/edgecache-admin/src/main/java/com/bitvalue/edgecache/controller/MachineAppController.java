package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.*;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineAppService;
import com.bitvalue.edgecache.service.IMachineCacheService;
import com.bitvalue.edgecache.service.IMachineDpiService;
import com.bitvalue.edgecache.service.IMachineMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * machineApp控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine_app")
public class MachineAppController extends BaseController<MachineApp,Integer,IMachineAppService>{

    @Autowired
    private IMachineDpiService machineDpiService;
    @Autowired
    private IMachineCacheService machineCacheService;
    @Autowired
    private IMachineMaintenanceService machineMaintenanceService;
    @Autowired
    private IMachineAppService machineAppService;

    @Override
    public Result deleteByCriteria(@RequestBody MachineApp machineApp) {
        MachineDpi machineDpi = new MachineDpi(machineApp.getMachineId(), 0);
        machineDpiService.deleteByCriteria(machineDpi);
        MachineCache machineCache = new MachineCache(machineApp.getMachineId(), 0);
        machineCacheService.deleteByCriteria(machineCache);
        return Result.success();
    }

    /**
     * 上传程序
     * @param machineId
     * @return
     */
    @Log(module = "上传程序",type = LogType.UPDATE)
    @GetMapping("/install/{type}/{machineId}")
    public Result installApp(@PathVariable String type,@PathVariable Integer machineId) {
        /*MachineMaintenance machineMaintenance = machineMaintenanceService.findById(machineId);
        AnsibleUtils.installApp(machineId,machineMaintenance.getIp(),type);*/
        return Result.success(123);
    }

    @Log(module = "检测程序是否安装",type = LogType.UPDATE)
    @GetMapping("/examine/{id}")
    public Result examine(@PathVariable Integer id){
        MachineApp machineApp = getService().findById(id);
        MachineMaintenance maintenance = machineApp.getMachineMaintenance();
        try {
            Integer dpi = AnsibleUtils.getDirIsExist(maintenance.getIp(), Config.custom_path_dpi);
            Integer bcache = AnsibleUtils.getDirIsExist(maintenance.getIp(),Config.custom_path_bcache);
            Integer webadmin = AnsibleUtils.getDirIsExist(maintenance.getIp(),Config.custom_path_webadmin);
            machineApp.setCache(bcache);
            machineApp.setDpi(dpi);
            machineApp.setWeb(webadmin);
            machineApp.setStatus(1);
        } catch (Exception e) {
            machineApp.setStatus(2);
        }


        getService().update(machineApp);

        return Result.success();
    }
}
