package com.bitvalue.edgecache.dao;

import com.bitvalue.edgecache.entity.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * sysMenu数据访问接口
 * @author wubo
 */
@Repository
public interface SysMenuDao extends BaseDao<SysMenu,Integer>{

    @Query(value = "SELECT s.*,ss.name AS parentName FROM sys_menu s LEFT JOIN sys_menu ss ON s.pid = ss.id where s.id =?1",nativeQuery=true)
    SysMenu findSysMenuAndParentName(Integer id);
}
