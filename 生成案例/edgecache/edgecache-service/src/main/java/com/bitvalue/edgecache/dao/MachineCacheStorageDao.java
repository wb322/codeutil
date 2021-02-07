package com.bitvalue.edgecache.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.MachineCacheStorage;
/**
 * machineCacheStorage数据访问接口
 * @author wubo
 */
@Repository
public interface MachineCacheStorageDao extends BaseDao<MachineCacheStorage,Integer>{

    @Modifying
    @Query("delete from MachineCacheStorage s where s.machineCache.id = :id")
    void deleteAllByCacheId(Integer id);
	
}
