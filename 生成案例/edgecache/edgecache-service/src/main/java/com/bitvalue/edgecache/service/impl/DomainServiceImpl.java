package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.Domain;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.DomainDao;
import com.bitvalue.edgecache.service.IDomainService;
/**
 * domain服务层实现类
 * @author wubo
 */
@Service("domainService")
@Transactional
public class DomainServiceImpl extends ServiceImpl<Domain,Integer,DomainDao> implements IDomainService{

}
