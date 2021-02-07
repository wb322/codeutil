package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.RedirectRule;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IRedirectRosterService;
import com.bitvalue.edgecache.service.IRedirectRuleService;
import com.bitvalue.edgecache.tpl.DpiTpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * redirectRule控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/redirect_rule")
public class RedirectRuleController extends BaseController<RedirectRule,Integer,IRedirectRuleService>{

    @Resource
    private IRedirectRosterService redirectRosterService;

    @Override
    public Result save(@RequestBody RedirectRule redirectRule) {
        Result save = super.save(redirectRule);
        Boolean f = DpiTpl.DPI(getService().findAll(),redirectRosterService.findAll());
        if (f){
            return save;
        }else{
            return new Result(1,"配置更新失败");
        }
    }

    @Override
    public Result updateById(@RequestBody RedirectRule redirectRule) {
        Boolean f = DpiTpl.DPI(getService().findAll(),redirectRosterService.findAll());
        return super.updateById(redirectRule);
    }

    @Override
    public Result deleteById(@PathVariable Integer id) {
        Result result = super.deleteById(id);
        Boolean f = DpiTpl.DPI(getService().findAll(),redirectRosterService.findAll());
        if (f){
            return result;
        }else{
            return new Result(1,"配置更新失败");
        }
    }
}
