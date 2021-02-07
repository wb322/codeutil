package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.PrecacheTask;

import java.util.Date;
import java.util.List;

/**
 * precacheTask数据访问接口
 * @author wubo
 */
@Repository
public interface PrecacheTaskDao extends BaseDao<PrecacheTask,Integer>{

    List<PrecacheTask> findByStatusAndStartTimeLessThanAndEndTimeGreaterThan(String status, Date startTime,Date endTime);
	
}
