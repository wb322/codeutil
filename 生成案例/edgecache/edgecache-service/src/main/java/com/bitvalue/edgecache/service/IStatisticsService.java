package com.bitvalue.edgecache.service;

import cn.hutool.core.date.DateTime;
import com.bitvalue.edgecache.entity.Statistics;

import java.util.List;

/**
 * statistics服务层接口
 * @author wb
 */
public interface IStatisticsService extends IService<Statistics,Integer> {

    List<Statistics> findByTypeAndTimeBetween(int i, DateTime start, DateTime end);
}
