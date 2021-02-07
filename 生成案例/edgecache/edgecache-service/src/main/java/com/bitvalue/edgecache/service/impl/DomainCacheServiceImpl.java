package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.DomainCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.DomainCacheDao;
import com.bitvalue.edgecache.service.IDomainCacheService;
/**
 * domainCache服务层实现类
 * @author wubo
 */
@Service("domainCacheService")
@Transactional
public class DomainCacheServiceImpl extends ServiceImpl<DomainCache,Integer,DomainCacheDao> implements IDomainCacheService{

}
