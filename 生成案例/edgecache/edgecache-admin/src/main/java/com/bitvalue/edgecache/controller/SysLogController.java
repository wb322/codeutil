package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.SysLog;
import com.bitvalue.edgecache.service.ISysLogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * sysLog控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/sys_log")
public class SysLogController extends BaseController<SysLog,Integer,ISysLogService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("sys_log/list");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("sys_log/form");
        return modelAndView;
    }
}
