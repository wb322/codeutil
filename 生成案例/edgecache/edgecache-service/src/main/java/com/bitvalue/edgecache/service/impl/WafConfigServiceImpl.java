package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.WafConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.WafConfigDao;
import com.bitvalue.edgecache.service.IWafConfigService;
/**
 * wafConfig服务层实现类
 * @author wubo
 */
@Service("wafConfigService")
@Transactional
public class WafConfigServiceImpl extends ServiceImpl<WafConfig,Integer,WafConfigDao> implements IWafConfigService{

}
