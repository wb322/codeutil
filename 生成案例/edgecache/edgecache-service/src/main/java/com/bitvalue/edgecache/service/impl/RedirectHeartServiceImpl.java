package com.bitvalue.edgecache.service.impl;

import com.bitvalue.edgecache.entity.RedirectHeart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bitvalue.edgecache.dao.RedirectHeartDao;
import com.bitvalue.edgecache.service.IRedirectHeartService;
/**
 * redirectHeart服务层实现类
 * @author wubo
 */
@Service("redirectHeartService")
@Transactional
public class RedirectHeartServiceImpl extends ServiceImpl<RedirectHeart,Integer,RedirectHeartDao> implements IRedirectHeartService{

}
