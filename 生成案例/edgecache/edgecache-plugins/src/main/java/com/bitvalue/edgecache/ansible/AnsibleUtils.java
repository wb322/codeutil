package com.bitvalue.edgecache.ansible;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.entity.MachineMaintenance;
import com.bitvalue.edgecache.entity.MachineRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnsibleUtils {

    private static Logger logger = LoggerFactory.getLogger(AnsibleUtils.class);

    /**
     * 获取多条ansible结果
     * @param str
     * @return
     */
    public static List<AnsibleResult> getAnsibleResults(String str){
        List<String> list = StrUtil.split(str, "\n}\n", -1, false, true);
        List<AnsibleResult> ansibleResults = new ArrayList<>();
        if (list != null){
            for (String s : list) {
                s += "\n}";
                ansibleResults.add(new AnsibleResult(s));
            }
        }
        return ansibleResults;
    }

    /**
     * 获取机器基本信息
     * @param ip
     * @return
     */
    public static List<String> getMachineInfo(String ip){
        String hostname = "hostname;";
        String system = "cat /etc/redhat-release;";
        String cpu = "cat /proc/cpuinfo| grep 'processor'| wc -l;";
        String memory = "/usr/bin/sudo /usr/sbin/dmidecode -t memory | grep Size | awk '{print \\$2}' | grep -v 'No' | awk '{sum +=\\$1};END{print sum/1024}';";
        String disk = "df -P | grep -v 'Filesystem' | awk '{sum +=\\$2};END{print sum/1024/1024}';";
        String command = "ansible "+ ip +" -m shell -a \""+ hostname + system + cpu + memory + disk +"\"";
        String ipsCommand = "ansible "+ ip +" -m setup -a \"filter="+ AnsibleEnum.IP +"\"";
        List<String> info = RuntimeUtil.execForLines("sh","-c",command);
        String s = RuntimeUtil.execForStr("sh","-c",ipsCommand);
        AnsibleResult ansibleResult = new AnsibleResult(s);
        Map facts = ansibleResult.getFacts();
        List<String> ips = (List<String>)facts.get(AnsibleEnum.IP);

        if (info != null && info.size()>0){
            info.remove(0);
        }
        if (ips != null && ips.size()>0){
            info.add(StrUtil.join(",",ips));
        }
        return info;
    }

    /**
     * 检测目录是否存在
     * @param ip
     * @param dir
     * @return
     */
    public static Integer getDirIsExist(String ip,String dir){
        Integer i = 5;
        String command = "ansible "+ ip +" -m shell -a \"ls " + dir +"\"";
        List<String> info = RuntimeUtil.execForLines("sh","-c",command);
        if (info != null && info.size() > 0){
            if (info.get(0).contains("CHANGED")){
                i = 1;
            }
            if (info.get(0).contains("FAILED")){
                i = 0;
            }
        }
        return i;
    }

    /**
     * 修改ansible的hosts文件
     * @return
     */
    public static Boolean ansibleHosts(List<MachineMaintenance> list){
        File file = new File(Config.custom_path_ansible);
        List<String> lines = new ArrayList<>();
        if (list != null){
            for (MachineMaintenance m : list) {
                String format = StrUtil.format("{}\tansible_ssh_port={}\tansible_ssh_user={}\tansible_ssh_pass='{}'",
                                                m.getIp(), m.getPort(), m.getUsername(), m.getPassword());
                lines.add(format);
            }
        }
        FileUtil.writeUtf8Lines(lines,file);
        return true;
    }

    /**
     * 获取cpu,内存,磁盘等使用率
     * @param ip
     * @return
     */
    public static MachineRate getMachineRate(String ip){
        MachineRate rate = null;
        try {
            String cpu = "top -bn1 | grep load | awk '{printf \\\"%.2f\\n\\\", \\$(NF-2)}';";
            String memory = "free -h -m | awk 'NR==2{printf \\\"%s|%s|%.2f\\n\\\", \\$3,\\$2,\\$3*100/\\$2 }';";
            String disk = "df -P | grep -v 'Filesystem' | awk '{total +=\\$2;used +=\\$3};END{printf \\\"%.2fG|%.2fG|%.2f\\n\\\",used/1024/1024,total/1024/1024,used*100/total}';";
            String command = "ansible "+ ip +" -m shell -a \" "+ cpu + memory + disk +" \"";
            List<String> info = RuntimeUtil.execForLines("sh","-c",command);
            String line1 = ip + " | CHANGED | rc=0 >>";
            if (CollectionUtil.isNotEmpty(info) && line1.equals(info.get(0))){
                rate = new MachineRate();
                rate.setIp(ip);
                rate.setCpu(Double.parseDouble(info.get(1)));
                String memoryR = info.get(2);
                List<String> split = StrSpliter.split(memoryR, "|",3,true,true);
                if (CollectionUtil.isNotEmpty(split)){
                    rate.setMemoryUsed(split.get(0));
                    rate.setMemoryTotal(split.get(1));
                    rate.setMemoryRate(Double.parseDouble(split.get(2)));
                }
                String diskR = info.get(3);
                List<String> split2 = StrSpliter.split(diskR, "|",3,true,true);
                if (CollectionUtil.isNotEmpty(split)){
                    rate.setDiskUsed(split2.get(0));
                    rate.setDiskTotal(split2.get(1));
                    rate.setDiskRate(Double.parseDouble(split2.get(2)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }

    public static void installApp(Integer machineId,String ip,String type){
        String command1 = "ansible "+ ip +" -m shell -a \"mkdir "+ Config.custom_path_install +"\"";
        String src = "";
        if ("dpi".equals(type)){
            src = Config.custom_path_dpi;
        }else if ("cache".equals(type)){
            src = Config.custom_path_bcache;
        }else if ("web".equals(type)){
            src = Config.custom_path_webadmin;
        }
        String command2 = StrUtil.format("ansible "+ ip +" -m copy -a \"src={} dest={}\"",src,Config.custom_path_install);
        new Thread(new InstallThread(machineId,type,command1, command2)).start();
    }


    public static void reloadApp(String ip){
        RuntimeUtil.exec("sh","-c",Config.custom_path_bcache + "bin/traffic_ctl config reload");
        RuntimeUtil.exec("sh","-c",Config.custom_path_bengine + "sbin/nginxx -s reload");
    }


    public static Boolean curl(String ip,String url){
        String curl = "curl -s -o /dev/null "+ url;
        String cmd = "ansible "+ ip +"  -m shell -a \""+ curl +"\"";
        logger.info("机器执行curl任务：" + cmd);
        String sh = RuntimeUtil.execForStr("sh", "-c", cmd);

        if (sh == null || sh.contains("FAILED")){
            return false;
        }
        return true;
    }
    public static void precache(String url){
        String wget = "wget -S  "+ url +" -e http-proxy=127.0.0.1:80 -O /dev/null";
        logger.info("机器执行预缓存任务：" + wget);
        String cmd = "ansible all  -m shell -a\""+ wget +"\"";
        RuntimeUtil.exec("sh","-c",cmd);
    }

    public static Map<String,List<String>> newLoginLog(){
        logger.info("查询服务器登陆情况");
        Map<String,List<String>> map = new HashMap<>();
        List<String> success = RuntimeUtil.execForLines("sh", "-c", "last  -i|grep -v '^$'|grep -v '^wtmp begins'");
        List<String> error = RuntimeUtil.execForLines("sh", "-c", "lastb  -i|grep -v '^$'|grep -v '^btmp begins'");
        map.put("success",success);
        map.put("error",error);
        RuntimeUtil.exec("sh", "-c", "echo > /var/log/wtmp;echo > /var/log/btmp");
        return map;
    }

    public static void restartSSHD(){
        logger.info("查询重启ssh服务和telnet服务");
        String wget = "service sshd  restart;service  xinetd  restart";
        String cmd = "ansible all  -m shell -a \""+ wget +"\"";
        RuntimeUtil.exec("sh","-c",cmd);
    }

    public static double getGSLB(String ip,String cpu) throws Exception{
        logger.info("查询机器负载");
        String uptime = "uptime | awk '{printf \\\"%.2f\\n\\\", \\$(NF-2)}';";
        String cmd = "ansible "+ ip +"  -m shell -a \""+ uptime +"\"";
        String sh = RuntimeUtil.execForStr("sh", "-c", cmd);

        return Double.parseDouble(sh) * Double.parseDouble(cpu);
    }


    public static Boolean getAppStatus(String ip,String app) throws Exception{
        logger.info("查询机器程序状态");
        String shell = "ps -ef | grep "+ app +" |grep -v 'grep';";
        String cmd = "ansible "+ ip +"  -m shell -a \""+ shell +"\"";
        List<String> sh = RuntimeUtil.execForLines("sh", "-c", cmd);
        if (sh == null || sh.size() <= 0){
            return false;
        }
        return true;
    }

}
