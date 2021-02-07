package com.bitvalue.edgecache.tpl;

import cn.hutool.core.io.FileUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.entity.Domain;
import com.bitvalue.edgecache.entity.DomainCache;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NginxTpl {

    public static Boolean config(List<Domain> domains) throws Exception {
        if (domains == null){
            return false;
        }
        Map<String,List<Domain>> domainMap = new HashMap();
        domainMap.put("domains",domains);
        TplUtils.renderFile("parent.config",Config.custom_path_bcache + "etc/trafficserver/parent.config",domainMap);
        TplUtils.renderFile("cache.config",Config.custom_path_bcache + "etc/trafficserver/cache.config",domainMap);

        String vhostDir = Config.custom_path_bengine + "conf/vhost/";
        FileUtil.clean(vhostDir);
        List<String> cacheList = new ArrayList<>();
        for (Domain domain : domains) {
            Map map = new HashMap<>();
            map.put("domain",domain);
            TplUtils.renderFile("vhost.conf",vhostDir + domain.getUrl() + ".conf",map);
            //addCacheLine(cacheList,domain.getUrl(),domain.getDomainCaches());
        }
        //FileUtil.writeUtf8Lines(cacheList,Config.custom_path_bcache + "etc/trafficserver/cache.config");
        return true;
    }

    private static void addCacheLine(List<String> cacheList,String url,List<DomainCache> domainCaches) {
        if (domainCaches != null && domainCaches.size() > 0){
            cacheList.add("#" + url);
            for (DomainCache cache : domainCaches) {
                String cacheContent = cache.getCacheContent();
                if (cacheContent != null){
                    String[] split = cacheContent.split(";");
                    String time = cache.getCacheTime() + cache.getLimitTime();
                    if (cache.getCacheType() == 1){
                        for (String s : split) {
                            cacheList.add("dest_domain=" + url + " suffix=" + s + " method=GET scheme=http ttl-in-cache=" + time);
                        }
                    }else if (cache.getCacheType() == 2){
                        for (String s : split) {
                            cacheList.add("url_regex=" + url + "/" + s + "$ scheme=http ttl-in-cache=" + time);
                        }
                    }

                }

            }
        }
    }
}

