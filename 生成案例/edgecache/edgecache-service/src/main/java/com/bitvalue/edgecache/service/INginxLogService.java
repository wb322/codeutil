package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.NginxLog;

import java.util.Date;
import java.util.List;

/**
 * nginxLog服务层接口
 * @author wubo
 */
public interface INginxLogService extends IService<NginxLog,Integer> {

    void deleteByMsecBefore(Date time);

    List<NginxLog> findByServerAddrAndMsecBetween(String ip, Date start, Date end);

    List<NginxLog> findByStatusAndMsecBetweenOrderByMsecAsc(String status,Date start, Date end);
    
}
