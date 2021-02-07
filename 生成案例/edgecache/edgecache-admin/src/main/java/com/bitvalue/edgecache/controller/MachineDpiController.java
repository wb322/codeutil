package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.entity.MachineDpi;
import com.bitvalue.edgecache.resp.Criteria;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineDpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * machineDpi控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/machine_dpi")
public class MachineDpiController extends BaseController<MachineDpi,Integer,IMachineDpiService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("machine_dpi/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("machine_dpi/form");
        return modelAndView;
    }

    @Log(module = "机器DPI配置",type = LogType.SELECT)
    @GetMapping("/config/{machineId}")
    public Result findDefaultOrNotConfig(@PathVariable Integer machineId) {
        MachineDpi machineDpi = getService().findDefaultOrNotConfig(machineId);
        return Result.success(machineDpi);
    }
}
