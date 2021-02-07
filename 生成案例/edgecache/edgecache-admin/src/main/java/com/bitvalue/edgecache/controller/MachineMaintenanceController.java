package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.MachineMaintenance;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineMaintenanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * machineMaintenance控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine_maintenance")
public class MachineMaintenanceController extends BaseController<MachineMaintenance,Integer,IMachineMaintenanceService>{



    @Override
    public Result save(@RequestBody MachineMaintenance m) {
        getService().save(m);
        AnsibleUtils.ansibleHosts(getService().findAll());
        return Result.success();
    }

    @Override
    public Result updateById(@RequestBody MachineMaintenance m) {
        super.updateById(m);
        AnsibleUtils.ansibleHosts(getService().findAll());
        return Result.success();

    }

    @Override
    public Result deleteById(@PathVariable Integer id) {
        super.deleteById(id);
        AnsibleUtils.ansibleHosts(getService().findAll());
        return Result.success();
    }

}
