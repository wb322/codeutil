package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.DomainCacheRefresh;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.DomainCacheRefreshDao;
import com.bitvalue.edgecache.service.IDomainCacheRefreshService;
/**
 * domainCacheRefresh服务层实现类
 * @author wubo
 */
@Service("domainCacheRefreshService")
@Transactional
public class DomainCacheRefreshServiceImpl extends ServiceImpl<DomainCacheRefresh,Integer,DomainCacheRefreshDao> implements IDomainCacheRefreshService{

}
