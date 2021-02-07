package com.bitvalue.edgecache.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.ContentRefreshTask;
import com.bitvalue.edgecache.entity.ContentRefreshTaskDetail;
import com.bitvalue.edgecache.entity.MachineMaintenance;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IContentRefreshTaskService;
import com.bitvalue.edgecache.service.IMachineMaintenanceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * contentRefreshTask控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/content_refresh_task")
public class ContentRefreshTaskController extends BaseController<ContentRefreshTask,Integer,IContentRefreshTaskService>{

    @GetMapping("/list")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView("content_refresh_task/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView formPage(){
        ModelAndView modelAndView = new ModelAndView("content_refresh_task/form");
        return modelAndView;
    }

    @Override
    public Result save(@RequestBody ContentRefreshTask contentRefreshTask) {
        List<ContentRefreshTaskDetail> details = contentRefreshTask.getDetails();
        if (details == null || details.size() <= 0){
            return Result.success();
        }
        String refreshType = contentRefreshTask.getRefreshType();
        String url;
        if ("目录刷新".equals(refreshType)) {
            url = "http://127.0.0.1:8080/cache/invalidate_path?url=";
        }else{
            url = "http://127.0.0.1:8080/cache/delete_url?url=";
        }
        for (ContentRefreshTaskDetail detail : details) {
            AnsibleUtils.curl("all",url + detail.getUrlOrDirectory());
        }
        contentRefreshTask.setStatus("已执行");
        getService().save(contentRefreshTask);
        return Result.success();
    }
}
