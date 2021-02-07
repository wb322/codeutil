package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.MachineRate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * machineRate服务层接口
 * @author wubo
 */
public interface IMachineRateService extends IService<MachineRate,Integer> {

    void deleteByTimeBefore(Date time);

    List<MachineRate> chart(String ip, Date start, Date end);
}
