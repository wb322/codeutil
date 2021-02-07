package com.bitvalue.edgecache.dao;

import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.WafRule;
/**
 * wafRule数据访问接口
 * @author wubo
 */
@Repository
public interface WafRuleDao extends BaseDao<WafRule,Integer>{
	
}
