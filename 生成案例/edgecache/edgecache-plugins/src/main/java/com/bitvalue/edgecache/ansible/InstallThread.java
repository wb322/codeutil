package com.bitvalue.edgecache.ansible;

import cn.hutool.core.util.RuntimeUtil;
import com.bitvalue.edgecache.entity.MachineApp;
import com.bitvalue.edgecache.service.IMachineAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InstallThread implements Runnable{


    private Integer machineId;
    private String type;
    private String command1;
    private String command2;

    @Autowired
    private IMachineAppService machineAppService;

    public InstallThread(Integer machineId,String type,String command1 , String command2){
        this.machineId = machineId;
        this.type = type;
        this.command1 = command1;
        this.command2 = command2;
    }

    @Override
    public void run() {
        try {
            RuntimeUtil.execForLines("sh","-c",command1);
            List<String> sh = RuntimeUtil.execForLines("sh", "-c", command2);
            if (sh != null && sh.size() >0){
                if (sh.get(0).contains("CHANGED") || sh.get(0).contains("SUCCESS")){
                    updateMachineApp(1);
                }
            }else{
                updateMachineApp(2);
            }
        } catch (Exception e) {
            updateMachineApp(2);
            e.printStackTrace();
        }


    }

    private void updateMachineApp(Integer f){
        MachineApp machineApp = machineAppService.findByMachineId(machineId);
        if ("dpi".equals(type)){
            machineApp.setDpi(f);
        }else if ("cache".equals(type)){
            machineApp.setCache(f);
        }else if ("web".equals(type)){
            machineApp.setWeb(f);
        }
        machineAppService.update(machineApp);
    }
}
