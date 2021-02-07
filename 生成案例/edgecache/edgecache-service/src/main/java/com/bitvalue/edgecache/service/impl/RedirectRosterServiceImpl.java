package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.RedirectRoster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.RedirectRosterDao;
import com.bitvalue.edgecache.service.IRedirectRosterService;

import java.util.List;
import java.util.Map;

/**
 * redirectRoster服务层实现类
 * @author wubo
 */
@Service("redirectRosterService")
@Transactional
public class RedirectRosterServiceImpl extends ServiceImpl<RedirectRoster,Integer,RedirectRosterDao> implements IRedirectRosterService{

    @Override
    public List<String> findUrlByType(Integer type) {
        List<String> urlByType = getDao().findUrlByType(type);
        return urlByType;
    }
}
