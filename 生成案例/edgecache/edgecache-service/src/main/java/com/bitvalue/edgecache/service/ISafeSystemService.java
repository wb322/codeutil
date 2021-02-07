package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.SafeSystem;
/**
 * safeSystem服务层接口
 * @author wubo
 */
public interface ISafeSystemService extends IService<SafeSystem,Integer> {

    String findAllNetworkSegment();
}
