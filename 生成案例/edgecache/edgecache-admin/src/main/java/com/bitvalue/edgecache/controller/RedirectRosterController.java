package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.RedirectRoster;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IRedirectRosterService;
import com.bitvalue.edgecache.service.IRedirectRuleService;
import com.bitvalue.edgecache.tpl.DpiTpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * redirectRoster控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/redirect_roster")
public class RedirectRosterController extends BaseController<RedirectRoster,Integer,IRedirectRosterService>{

    @Resource
    private IRedirectRuleService redirectRuleService;

    @Override
    public Result save(@RequestBody RedirectRoster redirectRoster) {
        Result save = super.save(redirectRoster);
        Boolean f = DpiTpl.DPI(redirectRuleService.findAll(),getService().findAll());
        if (f){
            return save;
        }else{
            return new Result(1,"配置更新失败");
        }
    }

    @Override
    public Result deleteById(@PathVariable Integer id) {
        Result result = super.deleteById(id);
        Boolean f = DpiTpl.DPI(redirectRuleService.findAll(),getService().findAll());
        if (f){
            return result;
        }else{
            return new Result(1,"配置更新失败");
        }

    }
}
