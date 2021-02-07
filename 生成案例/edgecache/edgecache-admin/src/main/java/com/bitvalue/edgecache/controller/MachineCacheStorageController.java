package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.MachineCacheStorage;
import com.bitvalue.edgecache.service.IMachineCacheStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * machineCacheStorage控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine_cache_storage")
public class MachineCacheStorageController extends BaseController<MachineCacheStorage,Integer,IMachineCacheStorageService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("machine_cache_storage/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("machine_cache_storage/form");
        return modelAndView;
    }
}
