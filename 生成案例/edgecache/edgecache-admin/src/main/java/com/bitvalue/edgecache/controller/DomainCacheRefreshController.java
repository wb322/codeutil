package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.DomainCacheRefresh;
import com.bitvalue.edgecache.service.IDomainCacheRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
/**
 * domainCacheRefresh控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/domain_cache_refresh")
public class DomainCacheRefreshController extends BaseController<DomainCacheRefresh,Integer,IDomainCacheRefreshService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("domain_cache_refresh/form");
        return modelAndView;
    }

    @GetMapping("/form" )
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("domain_cache_refresh/form");
        return modelAndView;
    }

    @GetMapping("/sync" )
    public void sync(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DomainCacheRefresh> responseEntity = restTemplate.getForEntity("", DomainCacheRefresh.class);

    }

}
