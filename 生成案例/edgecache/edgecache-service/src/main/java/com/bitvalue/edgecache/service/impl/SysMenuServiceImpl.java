package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SysMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitvalue.edgecache.dao.SysMenuDao;
import com.bitvalue.edgecache.service.ISysMenuService;

import java.util.List;

/**
 * sysMenu服务层实现类
 * @author wubo
 */
@Service("sysMenuService")
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenu,Integer,SysMenuDao> implements ISysMenuService{

    public SysMenu findSysMenuAndParentName(Integer id){
        return getDao().findSysMenuAndParentName(id);
    }

}
