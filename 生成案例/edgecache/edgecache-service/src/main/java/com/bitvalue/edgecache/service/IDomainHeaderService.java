package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.DomainHeader;

import java.util.List;

/**
 * domainHeader服务层接口
 * @author wubo
 */
public interface IDomainHeaderService extends IService<DomainHeader,Integer> {

    List<String> findHeaderKeyByDomainId(Integer domainId);
}
