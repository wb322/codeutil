package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.entity.SysSetting;
import com.bitvalue.edgecache.handler.InitType;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISysSettingService;
import org.springframework.web.bind.annotation.*;

/**
 * sysSetting控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/sys_setting")
public class SysSettingController extends BaseController<SysSetting,Integer,ISysSettingService>{

    @PutMapping("/cdn/{cdn}")
    public Result cdn(@PathVariable Boolean cdn){
        SysSetting sysSetting = getService().findById(1);
        if (sysSetting == null || sysSetting.getCdn() == null){
            return Result.error("获取运行模式失败");
        }
        if (sysSetting.getCdn() == cdn){
            return Result.error("系统运行模式以被修改");
        }
        SysSetting newSetting = new SysSetting();
        newSetting.setId(1);
        newSetting.setCdn(cdn);
        getService().update(newSetting);
        Config.run_cdn = cdn;
        return Result.success();
    }

    @Override
    public Result updateById(SysSetting sysSetting) {
        super.updateById(sysSetting);
        Config.control_center = sysSetting.getControl();
        return Result.success();
    }
}
