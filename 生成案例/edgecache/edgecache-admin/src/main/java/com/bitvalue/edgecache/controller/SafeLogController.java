package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.SafeLog;
import com.bitvalue.edgecache.service.ISafeLogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * safeLog控制器层
 * @author wb
 */
@RestController
@CrossOrigin
@RequestMapping("/safe_log")
public class SafeLogController extends BaseController<SafeLog,Integer,ISafeLogService>{
    
    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("safe_log/list");
        return modelAndView;
    }
    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("safe_log/form");
        return modelAndView;
    }

}
