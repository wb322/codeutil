package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.DomainHeader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitvalue.edgecache.dao.DomainHeaderDao;
import com.bitvalue.edgecache.service.IDomainHeaderService;

import java.util.List;

/**
 * domainHeader服务层实现类
 * @author wubo
 */
@Service("domainHeaderService")
@Transactional
public class DomainHeaderServiceImpl extends ServiceImpl<DomainHeader,Integer,DomainHeaderDao> implements IDomainHeaderService{

    @Override
    public List<String> findHeaderKeyByDomainId(Integer domainId) {
        return getDao().findHeaderKeyByDomainId(domainId);
    }

}
