package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.MachineCacheStorage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.MachineCacheStorageDao;
import com.bitvalue.edgecache.service.IMachineCacheStorageService;
/**
 * machineCacheStorage服务层实现类
 * @author wubo
 */
@Service("machineCacheStorageService")
@Transactional
public class MachineCacheStorageServiceImpl extends ServiceImpl<MachineCacheStorage,Integer,MachineCacheStorageDao> implements IMachineCacheStorageService{

}
