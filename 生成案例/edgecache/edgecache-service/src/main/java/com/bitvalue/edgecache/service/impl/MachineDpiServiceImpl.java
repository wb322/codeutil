package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.MachineDpi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.MachineDpiDao;
import com.bitvalue.edgecache.service.IMachineDpiService;
/**
 * machineDpi服务层实现类
 * @author wubo
 */
@Service("machineDpiService")
@Transactional
public class MachineDpiServiceImpl extends ServiceImpl<MachineDpi,Integer,MachineDpiDao> implements IMachineDpiService{

    @Override
    public MachineDpi findDefaultOrNotConfig(Integer machineId) {
        MachineDpi machineDpi = null;
        machineDpi = getDao().findDefaultOrNotConfig(machineId,0);
        if (machineDpi == null){
            machineDpi = getDao().findDefaultOrNotConfig(machineId,1);
        }
        return machineDpi;
    }

    @Override
    public MachineDpi findByMachineId(Integer machineId) {
        return getDao().findByMachineId(machineId);
    }
}
