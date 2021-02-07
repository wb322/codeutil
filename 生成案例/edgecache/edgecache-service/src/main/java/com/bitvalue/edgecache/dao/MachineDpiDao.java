package com.bitvalue.edgecache.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.MachineDpi;
/**
 * machineDpi数据访问接口
 * @author wubo
 */
@Repository
public interface MachineDpiDao extends BaseDao<MachineDpi,Integer>{


    @Query("from MachineDpi where machineId = :machineId and defaultConfig = :i")
    MachineDpi findDefaultOrNotConfig(Integer machineId, int i);

    MachineDpi findByMachineId(Integer machineId);
}
