package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.SysLog;

import java.util.Date;

/**
 * sysLog数据访问接口
 * @author wubo
 */
@Repository
public interface SysLogDao extends BaseDao<SysLog,Integer>{

    void deleteByTimeBefore(Date time);
}
