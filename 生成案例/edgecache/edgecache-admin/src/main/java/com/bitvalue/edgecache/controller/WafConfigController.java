package com.bitvalue.edgecache.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.bitvalue.edgecache.entity.SysSetting;
import com.bitvalue.edgecache.entity.WafConfig;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISysSettingService;
import com.bitvalue.edgecache.service.IWafConfigService;
import com.bitvalue.edgecache.tools.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * waf配置控制器
 *
 * @author wubo
 * @date 2020/08/26
 */
@RestController
@CrossOrigin
@RequestMapping("/waf_config")
public class WafConfigController extends BaseController<WafConfig,Integer,IWafConfigService>{


    @Autowired
    private ISysSettingService sysSettingService;

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("waf_config/list");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("waf_config/form");
        return modelAndView;
    }

    @Override
    public Result findById(@PathVariable Integer id) {
        List result = new ArrayList();
        WafConfig wafConfig = getService().findById(id);
        Map<String, Object> map = BeanUtil.beanToMap(wafConfig,true,false);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if ("id".equals(entry.getKey()) || "sync_status".equals(entry.getKey()) || "version".equals(entry.getKey()) ){
                continue;
            }else{
                Map row = new HashMap();
                row.put("key",entry.getKey());
                row.put("value",entry.getValue());
                result.add(row);
            }

        }
        return Result.success(result);
    }



    @PutMapping("/field")
    public Result updateById(@RequestBody String params) {
        Map map = (Map)JsonUtil.jsonToObject(params, Map.class);
        WafConfig wafConfig = new WafConfig();
        BeanUtil.fillBeanWithMap(map,wafConfig,true,true );
        SysSetting setting = sysSettingService.findById(1);
        if (setting.getCdn()){
            wafConfig.setId(2);
        }else{
            wafConfig.setId(1);
        }
        getService().update(wafConfig);
        return Result.success();
    }
}
