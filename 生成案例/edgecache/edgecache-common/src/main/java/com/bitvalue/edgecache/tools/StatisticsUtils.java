package com.bitvalue.edgecache.tools;

import java.util.*;

/**
 * @author wubo
 */
public class StatisticsUtils {

    private Map<String,Integer> map = new HashMap();
    private TreeMap<String,Integer> tree =new TreeMap(new ValueComparator(map));

    public void add(String field){
        int i = 1;
        if (map.containsKey(field)){
            i = map.get(field) + 1;
        }
        map.put(field,i);
    }

    public List<Map> get(Integer num){
        tree.putAll(map);
        List<Map> list = new ArrayList();
        Set<Map.Entry<String, Integer>> entries = tree.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (list.size() <num ){
                HashMap hashMap = new HashMap();
                hashMap.put(entry.getKey(),entry.getValue());
                list.add(hashMap);
            }else{
                break;
            }
        }
        return list;
    }
    public List<Object[]> getChart(Integer num){
        tree.putAll(map);
        List<Object[]> list = new ArrayList();
        Set<Map.Entry<String, Integer>> entries = tree.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (list.size() <num ){
                HashMap hashMap = new HashMap();
                hashMap.put(entry.getKey(),entry.getValue());
                Object[] objects = {entry.getKey(), entry.getValue()};
                list.add(objects);
            }else{
                break;
            }
        }
        return list;
    }
}
class ValueComparator implements Comparator<String> {

    private Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}