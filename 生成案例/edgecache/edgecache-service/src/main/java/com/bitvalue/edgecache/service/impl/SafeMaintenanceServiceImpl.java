package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SafeMaintenance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.SafeMaintenanceDao;
import com.bitvalue.edgecache.service.ISafeMaintenanceService;
/**
 * safeMaintenance服务层实现类
 * @author wb
 */
@Service("safeMaintenanceService")
@Transactional
public class SafeMaintenanceServiceImpl extends ServiceImpl<SafeMaintenance,Integer,SafeMaintenanceDao> implements ISafeMaintenanceService{

}
