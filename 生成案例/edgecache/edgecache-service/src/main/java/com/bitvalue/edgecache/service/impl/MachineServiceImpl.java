package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.Machine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.MachineDao;
import com.bitvalue.edgecache.service.IMachineService;
/**
 * machine服务层实现类
 * @author wubo
 */
@Service("machineService")
@Transactional
public class MachineServiceImpl extends ServiceImpl<Machine,Integer,MachineDao> implements IMachineService{

    @Override
    public Machine findByMachineId(Integer machineId) {
        return getDao().findByMachineId(machineId);
    }
}
