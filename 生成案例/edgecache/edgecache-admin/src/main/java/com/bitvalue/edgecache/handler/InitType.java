package com.bitvalue.edgecache.handler;

import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.entity.SysSetting;
import com.bitvalue.edgecache.service.ISafeSystemService;
import com.bitvalue.edgecache.service.ISysSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitType implements CommandLineRunner{

    @Autowired
    private ISysSettingService sysSettingService;


    private Logger logger = LoggerFactory.getLogger(InitType.class);

    @Override
    public void run(String... args) throws Exception {
        try {
            SysSetting sysSetting = sysSettingService.findById(1);
            Config.control_center = sysSetting.getControl();
            if (sysSetting.getCdn()){
                Config.run_cdn = true;
                logger.info("当前启动模式:CDN模式");
            }else{
                logger.info("当前启动模式:边缘缓存模式");
            }
        } catch (Exception e) {
            logger.error("查询业务系统启动模式失败,当前模式:边缘缓存模式");
        }
    }

}
