package com.bitvalue.edgecache.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.RedirectRoster;

import java.util.List;
import java.util.Map;

/**
 * redirectRoster数据访问接口
 * @author wubo
 */
@Repository
public interface RedirectRosterDao extends BaseDao<RedirectRoster,Integer>{

    @Query("select url from RedirectRoster where type = :type")
    List<String> findUrlByType(Integer type);
}
