package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.MachineMaintenance;

import java.util.List;

/**
 * machineMaintenance数据访问接口
 * @author wubo
 */
@Repository
public interface MachineMaintenanceDao extends BaseDao<MachineMaintenance,Integer>{
	List<MachineMaintenance> findByRoleContains(String role);
}
