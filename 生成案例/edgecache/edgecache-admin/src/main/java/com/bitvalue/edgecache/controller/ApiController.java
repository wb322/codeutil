package com.bitvalue.edgecache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公开的接口
 */
@RestController
@CrossOrigin
public class ApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping(value = {"/login","/login/{type}"})
    public ModelAndView login(@PathVariable(required = false) Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView("login");
        String msg = "";
        if (type != null){
            switch (type){
                case 1: msg = "该用户已在其它地方登录，请确认是否为本人登录！";break;
                default:msg = "参数错误";
            }
            modelAndView.addObject("msg",msg);
        }
        return modelAndView;
    }

}
