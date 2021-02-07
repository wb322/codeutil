package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.Machine;
import com.bitvalue.edgecache.entity.MachineMaintenance;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * machine控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine")
public class MachineController extends BaseController<Machine,Integer,IMachineService>{


    @Log(module = "机器检测",type = LogType.UPDATE)
    @GetMapping("/examine/{id}")
    public Result examine(@PathVariable Integer id){
        Machine machine = getService().findById(id);
        MachineMaintenance maintenance = machine.getMachineMaintenance();

        List<String> machineInfo = AnsibleUtils.getMachineInfo(maintenance.getIp());
        machine.setName(machineInfo.get(0));
        machine.setSystem(machineInfo.get(1));
        machine.setCpu(machineInfo.get(2));
        machine.setMemory(machineInfo.get(3));
        machine.setDisk(machineInfo.get(4));
        machine.setNetwork(machineInfo.get(5));

        getService().update(machine);

        return Result.success();
    }
}
