package com.bitvalue.edgecache.ansible;

import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.tools.JsonUtil;

import java.util.List;
import java.util.Map;

public class AnsibleResult {

    /**
     * 是否成功
     */
    private Boolean flag;

    /**
     * 结果
     */
    private Map facts;

    /**
     * 是否变化
     */
    private Boolean changed;

    /**
     * ping 命令结果
     */
    private String ping;


    public AnsibleResult(String str) {
        if (StrUtil.isNotBlank(str)){
            List<String> split = StrUtil.split(str, '{', 2);
            if (split.get(0).contains("SUCCESS")){
                this.flag = true;
                String s = "{\n" + split.get(1);
                Map map = (Map)JsonUtil.jsonToObject(s,Map.class);

                this.facts = (Map)map.get("ansible_facts");
                this.changed = (Boolean) map.get("changed");
                this.ping = (String) map.get("ping");
            }else{
                this.flag = false;
            }
        }else{
            this.flag = false;
        }
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }


    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public Map getFacts() {
        return facts;
    }

    public void setFacts(Map facts) {
        this.facts = facts;
    }

    @Override
    public String toString() {
        return "AnsibleResult{" +
                "flag=" + flag +
                ", facts=" + facts +
                ", changed=" + changed +
                ", ping='" + ping + '\'' +
                '}';
    }
}
