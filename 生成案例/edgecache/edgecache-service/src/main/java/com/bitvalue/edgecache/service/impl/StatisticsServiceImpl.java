package com.bitvalue.edgecache.service.impl;

import cn.hutool.core.date.DateTime;
import com.bitvalue.edgecache.entity.Statistics;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.StatisticsDao;
import com.bitvalue.edgecache.service.IStatisticsService;

import java.util.List;

/**
 * statistics服务层实现类
 * @author wb
 */
@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl extends ServiceImpl<Statistics,Integer,StatisticsDao> implements IStatisticsService{

    @Override
    public List<Statistics> findByTypeAndTimeBetween(int i, DateTime start, DateTime end) {
        return getDao().findByTypeAndTimeBetween(i,start,end);
    }
}
