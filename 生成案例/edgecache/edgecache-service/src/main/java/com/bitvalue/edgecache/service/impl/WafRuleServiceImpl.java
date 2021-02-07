package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.WafRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.WafRuleDao;
import com.bitvalue.edgecache.service.IWafRuleService;
/**
 * wafRule服务层实现类
 * @author wubo
 */
@Service("wafRuleService")
@Transactional
public class WafRuleServiceImpl extends ServiceImpl<WafRule,Integer,WafRuleDao> implements IWafRuleService{

}
