package com.bitvalue.edgecache.tpl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.entity.RedirectRoster;
import com.bitvalue.edgecache.entity.RedirectRule;

import java.util.ArrayList;
import java.util.List;

public class DpiTpl {

    public static Boolean DPI(List<RedirectRule> rules, List<RedirectRoster> rosters){
        try {
            List dpi = new ArrayList();

            List white_dns = new ArrayList();
            List white_http = new ArrayList();
            List black_dns = new ArrayList();
            List black_http = new ArrayList();

            List white_ip_range = new ArrayList();
            List black_ip_range = new ArrayList();
            dpi.add("[base]");
            dpi.add("max_list_items=102400");
            dpi.add("threshold=1");
            dpi.add("use_ip_range=1");

            dpi.add("[device]");
            dpi.add("devname=em1");
            dpi.add("sendname=em1");
            dpi.add("bufsize=1024");
            dpi.add("[pps]");
            ruleData(rules, white_dns, white_http, black_dns, black_http);
            rosterData(rosters,black_ip_range,white_ip_range);
            FileUtil.writeUtf8Lines(dpi,Config.custom_path_dpi + "/config/dpi.conf");
            FileUtil.writeUtf8Lines(white_dns,Config.custom_path_dpi + "/config/white_dns_list");
            FileUtil.writeUtf8Lines(white_http,Config.custom_path_dpi + "/config/white_http_list");
            FileUtil.writeUtf8Lines(black_dns,Config.custom_path_dpi + "/config/black_dns_list");
            FileUtil.writeUtf8Lines(black_http,Config.custom_path_dpi + "/config/black_http_list");
            FileUtil.writeUtf8Lines(white_ip_range,Config.custom_path_dpi + "/config/white_ip_range_list");
            FileUtil.writeUtf8Lines(black_ip_range,Config.custom_path_dpi + "/config/black_ip_range_list");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void rosterData(List<RedirectRoster> rosters, List black_ip_range, List white_ip_range) {
        if (rosters == null || rosters.size() <= 0){
            return;
        }
        for (RedirectRoster roster : rosters) {
            if (roster.getType() == 1){
                white_ip_range.add(roster.getUrl());
            }else{
                black_ip_range.add(roster.getUrl());
            }
        }
    }

    private static void ruleData(List<RedirectRule> rules, List white_dns, List white_http, List black_dns, List black_http) {
        if (rules == null || rules.size() <= 0){
            return;
        }
        for (RedirectRule rule : rules) {
            String line = "";
            if ("DNS".equals(rule.getType())){
                line = StrUtil.format("domain={}\tip_list={}", rule.getRule(), rule.getIps());
                if (rule.getRosterType() == 1){
                    white_dns.add(line);
                }else{
                    black_dns.add(line);
                }
            }else{
                if (rule.getVhost() != null){
                    line = StrUtil.format("url={}\tvhost={}\tip_list={}", rule.getRule(),rule.getVhost(),rule.getIps());
                }else{
                    line = StrUtil.format("url={}\tip_list={}", rule.getRule(), rule.getIps());
                }
                if (rule.getRosterType() == 1){
                    white_http.add(line);
                }else{
                    black_http.add(line);
                }
            }
        }
    }
}
