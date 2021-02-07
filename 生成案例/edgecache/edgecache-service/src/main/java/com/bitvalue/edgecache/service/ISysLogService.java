package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.SysLog;

import java.util.Date;

/**
 * sysLog服务层接口
 * @author wubo
 */
public interface ISysLogService extends IService<SysLog,Integer> {

    void deleteByTimeBefore(Date time);
}
