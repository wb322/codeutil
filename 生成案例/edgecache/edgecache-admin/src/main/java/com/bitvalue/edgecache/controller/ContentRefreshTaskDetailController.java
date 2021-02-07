package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.ContentRefreshTaskDetail;
import com.bitvalue.edgecache.service.IContentRefreshTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * contentRefreshTaskDetail控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/content_refresh_task_detail")
public class ContentRefreshTaskDetailController extends BaseController<ContentRefreshTaskDetail,Integer,IContentRefreshTaskDetailService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("content_refresh_task_detail/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("content_refresh_task_detail/form");
        return modelAndView;
    }
}
