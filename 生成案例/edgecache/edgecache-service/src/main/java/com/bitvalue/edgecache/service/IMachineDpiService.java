package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.MachineDpi;
/**
 * machineDpi服务层接口
 * @author wubo
 */
public interface IMachineDpiService extends IService<MachineDpi,Integer> {

    MachineDpi findDefaultOrNotConfig(Integer machineId);

    MachineDpi findByMachineId(Integer machineId);
}
