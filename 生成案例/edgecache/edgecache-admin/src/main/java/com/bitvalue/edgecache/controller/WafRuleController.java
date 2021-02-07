package com.bitvalue.edgecache.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.bitvalue.edgecache.entity.WafRule;
import com.bitvalue.edgecache.service.IWafRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * wafRule控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/waf_rule")
public class WafRuleController extends BaseController<WafRule,Integer,IWafRuleService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("waf_rule/list");
        return modelAndView;
    }
    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("waf_rule/form");
        return modelAndView;
    }

}
