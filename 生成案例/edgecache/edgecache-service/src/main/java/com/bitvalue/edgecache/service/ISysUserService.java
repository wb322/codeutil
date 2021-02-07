package com.bitvalue.edgecache.service;

import com.bitvalue.edgecache.entity.SysUser;
/**
 * sysUser服务层接口
 * @author wubo
 */
public interface ISysUserService extends IService<SysUser,Integer> {

    SysUser findOne(SysUser sysUser);

    void updatePasswordByUsername(String username, String password);
}
