package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.MachineMaintenance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.MachineMaintenanceDao;
import com.bitvalue.edgecache.service.IMachineMaintenanceService;

import java.util.List;

/**
 * machineMaintenance服务层实现类
 * @author wubo
 */
@Service("machineMaintenanceService")
@Transactional
public class MachineMaintenanceServiceImpl extends ServiceImpl<MachineMaintenance,Integer,MachineMaintenanceDao> implements IMachineMaintenanceService{

    @Override
    public List<MachineMaintenance> findByRoleContains(String role) {
        return getDao().findByRoleContains(role);
    }
}
