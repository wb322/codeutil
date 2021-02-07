package com.bitvalue.edgecache.controller;

import cn.hutool.core.util.RuntimeUtil;
import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.Domain;
import com.bitvalue.edgecache.entity.DomainCache;
import com.bitvalue.edgecache.entity.DomainHeader;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IDomainService;
import com.bitvalue.edgecache.tpl.DpiTpl;
import com.bitvalue.edgecache.tpl.NginxTpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * domain控制器层
 * @author wubo
 */
@Api(tags={"域名接口"})
@RestController
@CrossOrigin
@RequestMapping("/domain")
public class DomainController extends BaseController<Domain,Integer,IDomainService>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result save(@RequestBody Domain domain) {
        return super.save(domain);
        /*Boolean f = DpiTpl.addLine("black_dns_list",domain.getUrl());
        if (f){

        }else{
            return new Result(1,"配置更新失败");
        }*/
    }

    @ApiOperation(value="同步域名")
    @Log(module = "域名",type = LogType.UPDATE_CONFIG)
    @GetMapping("/sync/{id}")
    public Result sync(@PathVariable Integer id){
        List<Domain> domains = getService().findAll();
        try {
            Map map = new HashMap();
            //NginxTpl.config("/parent/parent.config",map);

            //NginxTpl.cache("/cache/domainremap.config",domains);

            /*NginxTpl.config("/302/upstream.conf",map);
            NginxTpl.config("/302/zone.conf",map);

            NginxTpl.config("/dns/acl.zone",map);
            NginxTpl.config("/dns/domain.zones",map);*/
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        return Result.success();
    }

    @ApiOperation(value="判断域名是否存在")
    @Log(module = "域名",type = LogType.VALIDATE)
    @GetMapping("/exist/{url}")
    public Boolean exist(@PathVariable String url){
        Domain domain = new Domain();
        domain.setUrl(url);
        return getService().exist(domain);
    }

    @ApiOperation(value="生成nginx配置")
    @Log(module = "域名",type = LogType.UPDATE_CONFIG)
    @GetMapping("/config")
    public Result config(){
        List<Domain> all = getService().findAll();
        Boolean config = false;
        String msg = "生成配置成功";
        try {
            NginxTpl.config(all);
            try {
                AnsibleUtils.reloadApp("");
                config = true;
            } catch (Exception e) {
                msg = "重启服务失败,请手动重启";
            }
        } catch (Exception e) {
            msg = "生成nginx配置失败";
        }
        if (config){
            return Result.success(msg,null);
        }else{
            return new Result(1,msg);
        }
    }
}
