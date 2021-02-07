package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.MachineRate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitvalue.edgecache.dao.MachineRateDao;
import com.bitvalue.edgecache.service.IMachineRateService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * machineRate服务层实现类
 * @author wubo
 */
@Service("machineRateService")
@Transactional
public class MachineRateServiceImpl extends ServiceImpl<MachineRate,Integer,MachineRateDao> implements IMachineRateService{

    @Override
    public void deleteByTimeBefore(Date time) {
        getDao().deleteByTimeBefore(time);
    }

    @Override
    public List<MachineRate> chart(String ip, Date start, Date end) {
        List<MachineRate> byIpAndTimeBetween = getDao().findByIpAndTimeBetween(ip, start, end);
        return byIpAndTimeBetween;
    }
}
