package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.MachineCache;
import com.bitvalue.edgecache.resp.Criteria;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * machineCache控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine_cache")
public class MachineCacheController extends BaseController<MachineCache,Integer,IMachineCacheService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("machine_cache/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("machine_cache/form");
        return modelAndView;
    }

    @Override
    public Result findByCriteria(@RequestBody Criteria<MachineCache> criteria) {
        // TODO: 2020/07/13 不按程序逻辑操作,此处可能报错
        List<MachineCache> list = getService().findByCriteria(criteria);
        int t  = -1;
        for (MachineCache machineCache : list) {
            ++ t;
            Integer defaultConfig = machineCache.getDefaultConfig();
            if (defaultConfig == 0){
                break;
            }
        }
        return Result.success(list.get(t));
    }
}
