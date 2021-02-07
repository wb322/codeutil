package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.ContentRefreshTaskDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.ContentRefreshTaskDetailDao;
import com.bitvalue.edgecache.service.IContentRefreshTaskDetailService;
/**
 * contentRefreshTaskDetail服务层实现类
 * @author wubo
 */
@Service("contentRefreshTaskDetailService")
@Transactional
public class ContentRefreshTaskDetailServiceImpl extends ServiceImpl<ContentRefreshTaskDetail,Integer,ContentRefreshTaskDetailDao> implements IContentRefreshTaskDetailService{

}
