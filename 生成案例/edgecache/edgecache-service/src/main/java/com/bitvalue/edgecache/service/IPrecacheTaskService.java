package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.PrecacheTask;

import java.util.Date;
import java.util.List;

/**
 * precacheTask服务层接口
 * @author wubo
 */
public interface IPrecacheTaskService extends IService<PrecacheTask,Integer> {
    List<PrecacheTask> findByStatusAndStartTimeLessThanAndEndTimeGreaterThan(String status, Date startTime, Date endTime);
}
