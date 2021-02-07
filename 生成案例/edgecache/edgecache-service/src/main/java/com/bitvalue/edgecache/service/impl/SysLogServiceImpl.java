package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SysLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.SysLogDao;
import com.bitvalue.edgecache.service.ISysLogService;

import java.util.Date;

/**
 * sysLog服务层实现类
 * @author wubo
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLog,Integer,SysLogDao> implements ISysLogService{

    @Override
    public void deleteByTimeBefore(Date time) {
        getDao().deleteByTimeBefore(time);
    }
}
