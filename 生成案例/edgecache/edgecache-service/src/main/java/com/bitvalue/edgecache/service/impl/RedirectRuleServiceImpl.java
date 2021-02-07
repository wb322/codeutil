package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.RedirectRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.RedirectRuleDao;
import com.bitvalue.edgecache.service.IRedirectRuleService;
/**
 * redirectRule服务层实现类
 * @author wubo
 */
@Service("redirectRuleService")
@Transactional
public class RedirectRuleServiceImpl extends ServiceImpl<RedirectRule,Integer,RedirectRuleDao> implements IRedirectRuleService{

}
