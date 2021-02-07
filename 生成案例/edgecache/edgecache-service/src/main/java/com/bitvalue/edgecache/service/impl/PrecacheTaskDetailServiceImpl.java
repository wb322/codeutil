package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.PrecacheTaskDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.PrecacheTaskDetailDao;
import com.bitvalue.edgecache.service.IPrecacheTaskDetailService;
/**
 * precacheTaskDetail服务层实现类
 * @author wubo
 */
@Service("precacheTaskDetailService")
@Transactional
public class PrecacheTaskDetailServiceImpl extends ServiceImpl<PrecacheTaskDetail,Integer,PrecacheTaskDetailDao> implements IPrecacheTaskDetailService{

}
