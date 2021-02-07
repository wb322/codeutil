package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.entity.SafeSystem;
import com.bitvalue.edgecache.resp.Criteria;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISafeSystemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * safeSystem控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/safe_system")
public class SafeSystemController extends BaseController<SafeSystem,Integer,ISafeSystemService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("safe_system/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("safe_system/form");
        return modelAndView;
    }
}
