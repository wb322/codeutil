package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.MachineApp;
/**
 * machineApp数据访问接口
 * @author wubo
 */
@Repository
public interface MachineAppDao extends BaseDao<MachineApp,Integer>{

    MachineApp findByMachineId(Integer machineId);
}
