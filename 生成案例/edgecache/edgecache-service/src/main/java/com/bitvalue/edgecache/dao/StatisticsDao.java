package com.bitvalue.edgecache.dao;

import cn.hutool.core.date.DateTime;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.Statistics;

import java.util.List;

/**
 * statistics数据访问接口
 * @author wb
 */
@Repository
public interface StatisticsDao extends BaseDao<Statistics,Integer>{

    List<Statistics> findByTypeAndTimeBetween(int i, DateTime start, DateTime end);
}
