package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.MachineApp;
/**
 * machineApp服务层接口
 * @author wubo
 */
public interface IMachineAppService extends IService<MachineApp,Integer> {

    MachineApp findByMachineId(Integer machineId);
}
