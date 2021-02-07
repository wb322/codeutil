package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.Machine;
/**
 * machine服务层接口
 * @author wubo
 */
public interface IMachineService extends IService<Machine,Integer> {

    Machine findByMachineId(Integer machineId);
}
