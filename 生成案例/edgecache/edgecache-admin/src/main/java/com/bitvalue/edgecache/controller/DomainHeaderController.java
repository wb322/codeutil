package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.Domain;
import com.bitvalue.edgecache.entity.DomainHeader;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IDomainHeaderService;
import com.bitvalue.edgecache.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * domainHeader控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/domain_header")
public class DomainHeaderController extends BaseController<DomainHeader,Integer,IDomainHeaderService>{

    @Autowired
    private IDomainService domainService;

    @GetMapping(value = {"/form","/form/{domainId}"})
    public ModelAndView formPage(@PathVariable(required = false) Integer domainId){
        ModelAndView modelAndView = new ModelAndView("domain_header/form");
        if (domainId != null){
            List list = new ArrayList<>();
            list.add(domainService.findById(domainId));
            List<String> headerKeyByDomainId = getService().findHeaderKeyByDomainId(domainId);
            modelAndView.addObject("domains",list);
            modelAndView.addObject("headerKeys", String.join(";",headerKeyByDomainId));
        }else{
            List<Domain> all = domainService.findAll();
            modelAndView.addObject("domains",all);
            if (all != null && all.size() > 0){
                List<String> headerKeyByDomainId = getService().findHeaderKeyByDomainId(all.get(0).getId());
                modelAndView.addObject("headerKeys", String.join(";",headerKeyByDomainId));
            }
        }
        return modelAndView;
    }
    @GetMapping("/headerKeys/{domainId}")
    public Result findHeaderKeyByDomainId(@PathVariable Integer domainId){
        List<String> headerKeyByDomainId = getService().findHeaderKeyByDomainId(domainId);
        return Result.success(String.join(";",headerKeyByDomainId));
    }
}
