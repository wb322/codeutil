package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.MachineMaintenance;

import java.util.List;

/**
 * machineMaintenance服务层接口
 * @author wubo
 */
public interface IMachineMaintenanceService extends IService<MachineMaintenance,Integer> {
    List<MachineMaintenance> findByRoleContains(String role);
}
