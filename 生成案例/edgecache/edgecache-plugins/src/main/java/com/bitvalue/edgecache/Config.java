package com.bitvalue.edgecache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class Config {

    public static transient volatile Boolean run_cdn = false;
    public static transient volatile String control_center = "";

    public static transient volatile Map<String, String> loginUserMap = new ConcurrentHashMap<>();//存储在线用户

    public static Integer custom_savetime_nginx;
    @Value("${custom.savetime.nginx}")
    public void setCustom_savetime_nginx(Integer custom_savetime_nginx) {
        Config.custom_savetime_nginx = custom_savetime_nginx;
    }
    public static Integer custom_savetime_safe;
    @Value("${custom.savetime.safe}")
    public void setCustom_savetime_safe(Integer custom_savetime_safe) {
        Config.custom_savetime_safe = custom_savetime_safe;
    }

    public static String custom_path_ansible;
    @Value("${custom.path.ansible}")
    public void setCustom_path_ansible(String custom_path_ansible) {
        this.custom_path_ansible = custom_path_ansible;
    }
    public static String custom_path_tpl;
    @Value("${custom.path.tpl}")
    public void setCustom_path_tpl(String custom_path_tpl) {
        Config.custom_path_tpl = custom_path_tpl;
    }

    public static String custom_path_install;
    public static String custom_path_dpi;
    public static String custom_path_bcache;
    public static String custom_path_bengine;
    public static String custom_path_webadmin;

    @Value("${custom.path.install}")
    public void setCustom_path_install(String custom_path_install) {
        this.custom_path_install = custom_path_install;
        this.custom_path_dpi = custom_path_install + "dpi/";
        this.custom_path_bcache = custom_path_install + "bcache/";
        this.custom_path_bengine = custom_path_install + "bengine/";
        this.custom_path_webadmin = custom_path_install + "webadmin/";
    }



}
