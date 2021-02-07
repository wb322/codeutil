package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.PrecacheTaskDetail;
import com.bitvalue.edgecache.service.IPrecacheTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * precacheTaskDetail控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/precache_task_detail")
public class PrecacheTaskDetailController extends BaseController<PrecacheTaskDetail,Integer,IPrecacheTaskDetailService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("precache_task_detail/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("precache_task_detail/form");
        return modelAndView;
    }
}
