package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.MachineApp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.MachineAppDao;
import com.bitvalue.edgecache.service.IMachineAppService;
/**
 * machineApp服务层实现类
 * @author wubo
 */
@Service("machineAppService")
@Transactional
public class MachineAppServiceImpl extends ServiceImpl<MachineApp,Integer,MachineAppDao> implements IMachineAppService{

    @Override
    public MachineApp findByMachineId(Integer machineId) {
        return getDao().findByMachineId(machineId);
    }
}
