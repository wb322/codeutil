package com.bitvalue.edgecache.dao;

import com.bitvalue.edgecache.entity.NginxLog;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * nginxLog数据访问接口
 * @author wubo
 */
@Repository
public interface NginxLogDao extends BaseDao<NginxLog,Integer>{

    void deleteByMsecBefore(Date time);


    List<NginxLog> findByServerAddrAndMsecBetweenOrderByMsecAsc(String ip,Date start,Date end);

    List<NginxLog> findByMsecBetweenOrderByMsecAsc(Date start, Date end);

    List<NginxLog> findByStatusAndMsecBetweenOrderByMsecAsc(String status,Date start, Date end);


}
