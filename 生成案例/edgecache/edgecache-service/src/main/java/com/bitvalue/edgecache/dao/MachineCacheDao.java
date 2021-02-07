package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.MachineCache;
/**
 * machineCache数据访问接口
 * @author wubo
 */
@Repository
public interface MachineCacheDao extends BaseDao<MachineCache,Integer>{

    MachineCache findByMachineId(Integer machineId);
}
