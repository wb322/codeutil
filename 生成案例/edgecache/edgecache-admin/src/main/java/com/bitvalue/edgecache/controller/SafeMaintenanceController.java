package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.SafeMaintenance;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISafeMaintenanceService;
import com.bitvalue.edgecache.tpl.TplUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * safeMaintenance控制器层
 * @author wb
 */
@RestController
@CrossOrigin
@RequestMapping("/safe_maintenance")
public class SafeMaintenanceController extends BaseController<SafeMaintenance,Integer,ISafeMaintenanceService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("safe_maintenance/list");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("safe_maintenance/form");
        return modelAndView;
    }

    @Override
    public Result save(@RequestBody SafeMaintenance safeMaintenance) {
        super.save(safeMaintenance);
        TplUtils.renderHosts(getService().findAll());
        return Result.success();
    }

    @Override
    public Result updateById(@RequestBody SafeMaintenance safeMaintenance) {
        super.updateById(safeMaintenance);
        TplUtils.renderHosts(getService().findAll());
        return Result.success();
    }

    @Override
    public Result deleteById(@PathVariable Integer id) {
        super.deleteById(id);
        TplUtils.renderHosts(getService().findAll());
        return Result.success();
    }

    @Override
    public Result deleteByIds(@RequestBody Integer[] ids) {
        super.deleteByIds(ids);
        TplUtils.renderHosts(getService().findAll());
        return Result.success();
    }
}
