package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.NginxLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.NginxLogDao;
import com.bitvalue.edgecache.service.INginxLogService;

import java.util.Date;
import java.util.List;

/**
 * nginxLog服务层实现类
 * @author wubo
 */
@Service("nginxLogService")
@Transactional
public class NginxLogServiceImpl extends ServiceImpl<NginxLog,Integer,NginxLogDao> implements INginxLogService{

    @Override
    public void deleteByMsecBefore(Date time) {
        getDao().deleteByMsecBefore(time);
    }

    @Override
    public List<NginxLog> findByServerAddrAndMsecBetween(String ip, Date start, Date end) {
        if (ip == null || "".equals(ip) ){
            return getDao().findByMsecBetweenOrderByMsecAsc(start,end);
        }else{
            return getDao().findByServerAddrAndMsecBetweenOrderByMsecAsc(ip,start,end);
        }
    }

    @Override
    public List<NginxLog> findByStatusAndMsecBetweenOrderByMsecAsc(String status, Date start, Date end) {
        return getDao().findByStatusAndMsecBetweenOrderByMsecAsc(status,start,end);
    }

}
