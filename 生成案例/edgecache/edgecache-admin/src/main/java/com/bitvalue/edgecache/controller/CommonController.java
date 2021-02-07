package com.bitvalue.edgecache.controller;


import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.entity.SysUser;
import com.bitvalue.edgecache.handler.InitType;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISysMenuService;
import com.bitvalue.edgecache.service.ISysSettingService;
import com.bitvalue.edgecache.service.ISysUserService;
import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wubo
 */
@RestController
@CrossOrigin
public class CommonController {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysSettingService sysSettingService;

    /**
     * 跳转到主页
     */
    @GetMapping(value = {"/","/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView ("index");
        modelAndView.addObject("menus",sysMenuService.findAll());
        modelAndView.addObject("setting",sysSettingService.findById(1));
        modelAndView.addObject("cdn", Config.run_cdn);

        return modelAndView;
    }

    /**
     * 接口文档
     */
    @GetMapping("/doc")
    public void swagger2(HttpServletResponse response)throws Exception{
        response.sendRedirect ("swagger-ui.html#/");
    }

    @GetMapping("/console")
    public ModelAndView console(){
        ModelAndView modelAndView = new ModelAndView ("console");
        return modelAndView;
    }


    @GetMapping(value = {"/menu/{module}/{view}","/menu/{module}/{view}/{key}"})
    public ModelAndView redirect(@PathVariable String module, @PathVariable String view, @PathVariable(required = false) Object key){
        ModelAndView modelAndView = new ModelAndView (module + "/" + view);
        if (key != null){
            modelAndView.addObject ("key",key);
        }
        return modelAndView;
    }


}
