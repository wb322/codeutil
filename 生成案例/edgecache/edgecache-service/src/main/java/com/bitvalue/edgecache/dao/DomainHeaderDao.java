package com.bitvalue.edgecache.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.DomainHeader;

import java.util.List;

/**
 * domainHeader数据访问接口
 * @author wubo
 */
@Repository
public interface DomainHeaderDao extends BaseDao<DomainHeader,Integer>{

    @Query("select headerKey from DomainHeader where domainId = :domainId")
    List<String> findHeaderKeyByDomainId(Integer domainId);
}
