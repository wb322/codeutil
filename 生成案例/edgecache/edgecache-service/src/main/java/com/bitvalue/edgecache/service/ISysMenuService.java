package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.SysMenu;

/**
 * sysMenu服务层接口
 * @author wubo
 */
public interface ISysMenuService extends IService<SysMenu,Integer> {

    SysMenu findSysMenuAndParentName(Integer id);
}
