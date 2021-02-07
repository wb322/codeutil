package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.dao.MachineCacheStorageDao;
import com.bitvalue.edgecache.entity.MachineCache;
import com.bitvalue.edgecache.entity.MachineCacheStorage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.MachineCacheDao;
import com.bitvalue.edgecache.service.IMachineCacheService;

import java.util.List;

/**
 * machineCache服务层实现类
 * @author wubo
 */
@Service("machineCacheService")
@Transactional
public class MachineCacheServiceImpl extends ServiceImpl<MachineCache,Integer,MachineCacheDao> implements IMachineCacheService{

    @Autowired
    private MachineCacheStorageDao machineCacheStorageDao;

    @Override
    public void save(MachineCache machineCache) {
        getDao().save(machineCache);
        List<MachineCacheStorage> machineCacheStorageList = machineCache.getMachineCacheStorageList();
        machineCacheStorageDao.deleteAllByCacheId(machineCache.getId());
        machineCache.setMachineCacheStorageList(null);
        if (machineCacheStorageList != null){
            for (MachineCacheStorage machineCacheStorage : machineCacheStorageList) {
                machineCacheStorage.setMachineCache(machineCache);
            }
            machineCacheStorageDao.saveAll(machineCacheStorageList);
        }
    }

    @Override
    public MachineCache findByMachineId(Integer machineId) {
        return getDao().findByMachineId(machineId);
    }
}
