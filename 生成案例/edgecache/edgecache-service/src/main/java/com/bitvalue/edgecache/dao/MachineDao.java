package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.Machine;
/**
 * machine数据访问接口
 * @author wubo
 */
@Repository
public interface MachineDao extends BaseDao<Machine,Integer>{

    Machine findByMachineId(Integer machineId);
}
