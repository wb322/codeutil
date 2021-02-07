package com.bitvalue.edgecache.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitvalue.edgecache.entity.SysUser;
/**
 * sysUser数据访问接口
 * @author wubo
 */
@Repository
public interface SysUserDao extends BaseDao<SysUser,Integer>{

    @Modifying
    @Query("update SysUser set password = ?2 where username = ?1")
    void updatePasswordByUsername(String username, String password);
}
