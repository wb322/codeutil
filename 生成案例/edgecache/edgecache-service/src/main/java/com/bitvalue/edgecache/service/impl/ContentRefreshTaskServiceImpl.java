package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.dao.ContentRefreshTaskDetailDao;
import com.bitvalue.edgecache.entity.ContentRefreshTask;
import com.bitvalue.edgecache.entity.ContentRefreshTaskDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.ContentRefreshTaskDao;
import com.bitvalue.edgecache.service.IContentRefreshTaskService;

import java.util.List;

/**
 * contentRefreshTask服务层实现类
 * @author wubo
 */
@Service("contentRefreshTaskService")
@Transactional
public class ContentRefreshTaskServiceImpl extends ServiceImpl<ContentRefreshTask,Integer,ContentRefreshTaskDao> implements IContentRefreshTaskService{

    @Autowired
    private ContentRefreshTaskDetailDao contentRefreshTaskDetailDao;

    @Override
    public void save(ContentRefreshTask contentRefreshTask) {
        getDao().save(contentRefreshTask);
        List<ContentRefreshTaskDetail> details = contentRefreshTask.getDetails();
        if (details != null && details.size() > 0){
            for (ContentRefreshTaskDetail detail : details) {
                detail.setContentId(contentRefreshTask.getId());
            }
            contentRefreshTaskDetailDao.saveAll(details);
        }
    }
}
