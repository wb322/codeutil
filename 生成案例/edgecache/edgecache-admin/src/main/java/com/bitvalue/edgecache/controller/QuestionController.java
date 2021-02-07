package com.bitvalue.edgecache.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.ansible.AnsibleUtils;
import com.bitvalue.edgecache.entity.Machine;
import com.bitvalue.edgecache.entity.MachineCache;
import com.bitvalue.edgecache.entity.MachineDpi;
import com.bitvalue.edgecache.entity.MachineMaintenance;
import com.bitvalue.edgecache.resp.Criteria;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.IMachineCacheService;
import com.bitvalue.edgecache.service.IMachineDpiService;
import com.bitvalue.edgecache.service.IMachineMaintenanceService;
import com.bitvalue.edgecache.service.IMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IMachineService machineService;
    @Resource
    private IMachineMaintenanceService machineMaintenanceService;
    @Resource
    private IMachineDpiService machineDpiService;
    @Resource
    private IMachineCacheService machineCacheService;

    @GetMapping("/ping/{ip}")
    public Result ping(@PathVariable String ip){
        List<String> strings = null;
        try {
            strings = RuntimeUtil.execForLines("ping -w 5 " + ip);
        } catch (Exception e) {
            strings = new ArrayList<>();
            strings.add(e.getMessage());
        }
        return Result.success(strings);
    }

    @GetMapping("/route/{ip}")
    public Result route(@PathVariable String ip){
        List<String> strings = null;
        try {
            strings = RuntimeUtil.execForLines("traceroute " + ip);
        } catch (Exception e) {
            strings = new ArrayList<>();
            strings.add(e.getMessage());
        }
        return Result.success(strings);
    }

    @GetMapping("/curl/{ip}")
    public Result curl(@PathVariable String ip){
        List<String> strings = null;
        try {
            strings = RuntimeUtil.execForLines(Charset.forName("UTF-8"), "curl " + ip);
        } catch (Exception e) {
            strings = new ArrayList<>();
            strings.add(e.getMessage());
        }
        return Result.success(strings);
    }

    @GetMapping(value = {"/analysis","/analysis/{id}/{key}"})
    public ModelAndView analysis(@PathVariable(required = false) Integer id,@PathVariable(required = false) Integer key){
        ModelAndView modelAndView = new ModelAndView ("question_analysis/list");
        List<MachineMaintenance> all = machineMaintenanceService.findAll();
        modelAndView.addObject ("machines",all);
        if (id != null){
            modelAndView.addObject ("id",id);
        }
        if (key != null){
            modelAndView.addObject ("key",key);
        }
        return modelAndView;
    }

    @GetMapping(value = {"/machineStatus/{machineId}"})
    public Result getMachineStatus(@PathVariable Integer machineId){
        logger.info("查询机器状态");
        List data = new ArrayList();
        RestTemplate restTemplate = new RestTemplate();

        Machine machine = machineService.findByMachineId(machineId);
        MachineMaintenance machineMaintenance = machine.getMachineMaintenance();

        MachineDpi dpi = machineDpiService.findByMachineId(machineId);
        if (dpi == null || StrUtil.isBlank(dpi.getNetwork())){
            data.add(MapUtil.of(new Object[][] {
                {"code", 0},
                {"msg", "该机器未填写DPI接口"}
            }));
        }else{
            try {
                Map map = restTemplate.getForObject(dpi.getNetwork(), Map.class);
                if (map.get("status") == "up"){
                    data.add(MapUtil.of(new Object[][] {
                        {"code", 1}
                    }));
                }else{
                    int f = 1/0;
                }
            } catch (Exception e) {
                data.add(MapUtil.of(new Object[][] {
                        {"code", 0},{"msg", "调用DPI接口异常"}
                }));
            }
        }

        MachineCache cache = machineCacheService.findByMachineId(machineId);
        if (cache == null || StrUtil.isBlank(cache.getServerIp()) || StrUtil.isBlank(cache.getServerPort())){
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},
                    {"msg", "该机器未填写缓存接口"}
            }));
        }else{
            try {
                Map map = restTemplate.getForObject(cache.getServerIp() + ":" + cache.getServerPort(), Map.class);
                if (map.get("status") == "up"){
                    data.add(MapUtil.of(new Object[][] {
                            {"code", 1}
                    }));
                }else{
                    int f = 1/0;
                }
            } catch (Exception e) {
                data.add(MapUtil.of(new Object[][] {
                        {"code", 0},{"msg", "调用缓存接口异常"}
                }));
            }
        }


        try {
            double gslb = AnsibleUtils.getGSLB(machineMaintenance.getIp(), machine.getCpu());
            if (gslb >= 5){
                int i = 1/0;
            }
            data.add(MapUtil.of(new Object[][] {
                    {"code", 1}
            }));
        } catch (Exception e) {
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},
                    {"msg", "注意：该机器无法检测此项或负载过高！"}
            }));
        }


        return Result.success(data);

    }


    @GetMapping(value = {"/appStatus/{machineId}"})
    public Result getAppStatus(@PathVariable Integer machineId){
        logger.info("查询机器状态");
        List data = new ArrayList();
        MachineMaintenance machineMaintenance = machineMaintenanceService.findById(machineId);

        try {
            String[] dpi = StrUtil.splitToArray(Config.custom_path_dpi, '/');
            Boolean b = AnsibleUtils.getAppStatus(machineMaintenance.getIp(),dpi[dpi.length-1]);
            if (!b){
                int i = 1/0;
            }
            data.add(MapUtil.of(new Object[][] {
                    {"code", 1}
            }));
        } catch (Exception e) {
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},{"msg","注意：DPI程序检测异常"}
            }));
        }


        try {
            String[] bcache = StrUtil.splitToArray(Config.custom_path_bcache, '/');
            Boolean b = AnsibleUtils.getAppStatus(machineMaintenance.getIp(),bcache[bcache.length-1]);
            if (!b){
                int i = 1/0;
            }
            data.add(MapUtil.of(new Object[][] {
                    {"code", 1}
            }));
        } catch (Exception e) {
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},{"msg","注意：缓存服务程序检测异常"}
            }));
        }

        try {
            String[] bengine = StrUtil.splitToArray(Config.custom_path_bengine, '/');
            Boolean b = AnsibleUtils.getAppStatus(machineMaintenance.getIp(),bengine[bengine.length-1]);
            if (!b){
                int i = 1/0;
            }
            data.add(MapUtil.of(new Object[][] {
                    {"code", 1}
            }));
        } catch (Exception e) {
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},{"msg","注意：负载均衡程序检测异常"}
            }));
        }


        return Result.success(data);
    }


    @GetMapping(value = {"/network/{machineId}"})
    public Result getNetwork(@PathVariable Integer machineId){
        logger.info("查询机器联通状态");
        List data = new ArrayList();
        MachineMaintenance machineMaintenance = machineMaintenanceService.findById(machineId);

        try {
            Boolean b = AnsibleUtils.curl(machineMaintenance.getIp(),Config.control_center);
            if (!b){
                int i = 1/0;
            }
            data.add(MapUtil.of(new Object[][] {
                    {"code", 1}
            }));
        } catch (Exception e) {
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},{"msg","注意：控制中心连接异常"}
            }));
        }


        try {
            Boolean b = AnsibleUtils.curl(machineMaintenance.getIp(),"www.mooc.cn");
            if (!b){
                int i = 1/0;
            }
            data.add(MapUtil.of(new Object[][] {
                    {"code", 1}
            }));
        } catch (Exception e) {
            data.add(MapUtil.of(new Object[][] {
                    {"code", 0},{"msg","注意：MOOC源站连接异常"}
            }));
        }

        return Result.success(data);
    }

}
