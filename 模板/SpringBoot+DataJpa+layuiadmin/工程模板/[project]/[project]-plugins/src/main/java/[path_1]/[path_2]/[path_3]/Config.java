package [package];

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

    public static String custom_path_tpl;
    @Value("${custom.path.tpl}")
    public void setCustom_path_tpl(String custom_path_tpl) {
        Config.custom_path_tpl = custom_path_tpl;
    }


}
