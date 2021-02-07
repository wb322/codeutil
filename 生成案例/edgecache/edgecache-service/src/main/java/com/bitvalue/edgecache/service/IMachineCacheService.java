package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.MachineCache;
/**
 * machineCache服务层接口
 * @author wubo
 */
public interface IMachineCacheService extends IService<MachineCache,Integer> {

    MachineCache findByMachineId(Integer machineId);
}
