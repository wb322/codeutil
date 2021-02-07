package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.RedirectRoster;

import java.util.List;

/**
 * redirectRoster服务层接口
 * @author wubo
 */
public interface IRedirectRosterService extends IService<RedirectRoster,Integer> {

    List<String> findUrlByType(Integer type);
}
