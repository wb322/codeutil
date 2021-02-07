package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SafeSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.SafeSystemDao;
import com.bitvalue.edgecache.service.ISafeSystemService;
/**
 * safeSystem服务层实现类
 * @author wubo
 */
@Service("safeSystemService")
@Transactional
public class SafeSystemServiceImpl extends ServiceImpl<SafeSystem,Integer,SafeSystemDao> implements ISafeSystemService{

    @Override
    public String findAllNetworkSegment() {
        return getDao().findAllNetworkSegment();
    }
}
