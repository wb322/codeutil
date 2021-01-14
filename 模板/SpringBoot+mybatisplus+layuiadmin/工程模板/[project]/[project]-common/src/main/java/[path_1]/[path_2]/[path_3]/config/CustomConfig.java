package [package].config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    public static Boolean save_log;
    public static byte[] security_key;


    public void setSave_log(Boolean save_log) {
        PluginsConfig.save_log = save_log;
    }

    public void setSecurity_key(byte[] security_key) {
        PluginsConfig.security_key = security_key;
    }
}
