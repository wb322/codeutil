package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.SysUser;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.SysUserDao;
import com.bitvalue.edgecache.service.ISysUserService;

import java.util.List;

/**
 * sysUser服务层实现类
 * @author wubo
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUser,Integer,SysUserDao> implements ISysUserService{

    @Override
    public SysUser findOne(SysUser sysUser) {
        Example<SysUser> of = Example.of(sysUser);
        return getDao().findOne(of).get();
    }

    @Override
    public void updatePasswordByUsername(String username, String password) {
        getDao().updatePasswordByUsername(username,password);
    }
}
