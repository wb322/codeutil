package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.SafeLog;

import java.util.List;

/**
 * safeLog服务层接口
 * @author wb
 */
public interface ISafeLogService extends IService<SafeLog,Integer> {

    void batchSave(List<SafeLog> safeLogs);
}
