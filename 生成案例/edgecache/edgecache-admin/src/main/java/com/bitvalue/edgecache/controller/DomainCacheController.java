package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.DomainCache;
import com.bitvalue.edgecache.service.IDomainCacheService;
import com.bitvalue.edgecache.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * domainCache控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/domain_cache")
public class DomainCacheController extends BaseController<DomainCache,Integer, IDomainCacheService>{


    @Autowired
    private IDomainService domainService;

    @GetMapping(value = {"/form","/form/{domainId}"})
    public ModelAndView formPage(@PathVariable(required = false) Integer domainId){
        ModelAndView modelAndView = new ModelAndView("domain_cache/form");
        if (domainId != null){
            List list = new ArrayList<>();
            list.add(domainService.findById(domainId));
            modelAndView.addObject("domains",list);
        }else{
            modelAndView.addObject("domains",domainService.findAll());
        }
        return modelAndView;
    }

}
