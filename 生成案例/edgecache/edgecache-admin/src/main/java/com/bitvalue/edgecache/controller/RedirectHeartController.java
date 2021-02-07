package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.RedirectHeart;
import com.bitvalue.edgecache.service.IRedirectHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * redirectHeart控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/redirect_heart")
public class RedirectHeartController extends BaseController<RedirectHeart,Integer,IRedirectHeartService>{


}
