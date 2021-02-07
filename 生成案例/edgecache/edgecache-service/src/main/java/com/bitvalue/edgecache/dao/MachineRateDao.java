package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.MachineRate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * machineRate数据访问接口
 * @author wubo
 */
@Repository
public interface MachineRateDao extends BaseDao<MachineRate,Integer>{

    void deleteByTimeBefore(Date time);

    List<MachineRate> findByIpAndTimeBetween(String ip, Date start, Date end);
}
