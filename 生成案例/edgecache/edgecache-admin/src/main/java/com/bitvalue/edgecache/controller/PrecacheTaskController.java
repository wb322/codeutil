package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.PrecacheTask;
import com.bitvalue.edgecache.service.IPrecacheTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * precacheTask控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/precache_task")
public class PrecacheTaskController extends BaseController<PrecacheTask,Integer,IPrecacheTaskService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("precache_task/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("precache_task/form");
        return modelAndView;
    }

}
