package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SysSetting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.SysSettingDao;
import com.bitvalue.edgecache.service.ISysSettingService;
/**
 * sysSetting服务层实现类
 * @author wubo
 */
@Service("sysSettingService")
@Transactional
public class SysSettingServiceImpl extends ServiceImpl<SysSetting,Integer,SysSettingDao> implements ISysSettingService{

}
