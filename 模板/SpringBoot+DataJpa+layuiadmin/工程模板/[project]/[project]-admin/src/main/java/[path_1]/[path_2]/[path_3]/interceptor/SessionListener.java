package [package].interceptor;

import [package].Config;
import [package].entity.SysLog;
import [package].service.ISysLogService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Configuration
public class SessionListener implements HttpSessionListener {

    @Resource
    private ISysLogService sysLogService;

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String sessionId = session.getId();
        String username = (String) session.getAttribute("login_user");

        SysLog sysLog = new SysLog();
        sysLog.setUsername(username);
        sysLog.setModule("登录");
        sysLog.setType("VALIDATE");
        sysLog.setResult("退出登录");
        sysLog.setStatus(0);
        sysLogService.save(sysLog);

        if (username != null) {
            if(Config.loginUserMap.get(username).equals(sessionId)){
                Config.loginUserMap.remove(username);
            }
        }

    }

}
