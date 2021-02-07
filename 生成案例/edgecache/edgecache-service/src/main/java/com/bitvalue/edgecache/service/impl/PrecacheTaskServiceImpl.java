package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.dao.PrecacheTaskDetailDao;
import com.bitvalue.edgecache.entity.ContentRefreshTaskDetail;
import com.bitvalue.edgecache.entity.PrecacheTask;
import com.bitvalue.edgecache.entity.PrecacheTaskDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.PrecacheTaskDao;
import com.bitvalue.edgecache.service.IPrecacheTaskService;

import java.util.Date;
import java.util.List;

/**
 * precacheTask服务层实现类
 * @author wubo
 */
@Service("precacheTaskService")
@Transactional
public class PrecacheTaskServiceImpl extends ServiceImpl<PrecacheTask,Integer,PrecacheTaskDao> implements IPrecacheTaskService{

    @Autowired
    private PrecacheTaskDetailDao precacheTaskDetailDao;
    @Override
    public void save(PrecacheTask precacheTask) {
        getDao().save(precacheTask);
        List<PrecacheTaskDetail> details = precacheTask.getDetails();
        if (details != null && details.size() > 0){
            for (PrecacheTaskDetail detail : details) {
                detail.setPrecacheId(precacheTask.getId());
            }
            precacheTaskDetailDao.saveAll(details);
        }
    }

    @Override
    public List<PrecacheTask> findByStatusAndStartTimeLessThanAndEndTimeGreaterThan(String status, Date startTime, Date endTime) {
        return getDao().findByStatusAndStartTimeLessThanAndEndTimeGreaterThan(status,startTime,endTime);
    }
}
