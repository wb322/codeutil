package com.bitvalue.edgecache.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.SafeSystem;
/**
 * safeSystem数据访问接口
 * @author wubo
 */
@Repository
public interface SafeSystemDao extends BaseDao<SafeSystem,Integer>{

    @Query(value = "SELECT group_concat(s.network_segment SEPARATOR ';') As ips FROM safe_system As s",nativeQuery = true)
    String findAllNetworkSegment();

}
