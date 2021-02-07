package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SafeLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.SafeLogDao;
import com.bitvalue.edgecache.service.ISafeLogService;

import java.util.List;

/**
 * safeLog服务层实现类
 * @author wb
 */
@Service("safeLogService")
@Transactional
public class SafeLogServiceImpl extends ServiceImpl<SafeLog,Integer,SafeLogDao> implements ISafeLogService{

    @Override
    public void batchSave(List<SafeLog> safeLogs) {
        getDao().saveAll(safeLogs);
    }
}
