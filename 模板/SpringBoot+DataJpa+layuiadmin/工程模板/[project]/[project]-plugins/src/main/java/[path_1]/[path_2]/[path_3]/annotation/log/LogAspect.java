package [package].annotation.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * AOP日志
 *
 * @author [author]
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private Log log;

    /**
     * 正常执行后通知
     */
    @AfterReturning(value = "@annotation(log)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Log log, Object result) {
        handleLog(joinPoint, log, null, result);
    }

    /**
     * 拦截异常操作
     */
    @AfterThrowing(value = "@annotation(log)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log log, Exception e) {
        handleLog(joinPoint, log, e, null);
    }

    /**
     * 执行
     */
    private void handleLog(JoinPoint joinPoint, Log log, Exception e, Object result) {
        try {
            //设置日志对象
            OptLog optLog = new OptLog();
            //模块
            String module = log.module();
            optLog.setModule(module);
            //操作类型
            LogType type = log.type();
            optLog.setType(type);
            //匹配的类.方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            optLog.setCodeMethod(className + "." + methodName + "()");
            //请求ip
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String ip = getRequestIp(request);
            optLog.setIp(ip);
            //请求url
            String url = request.getRequestURI();
            optLog.setUrl(url);
            //请求方法
            String requestMethod = request.getMethod();
            optLog.setHttpMethod(requestMethod);
            //请求参数
            String args = joinPoint.getArgs().toString();
            optLog.setParams(args);
            //返回参数
            optLog.setResult(result.toString());
            if (e != null) {
                //操作状态（0正常 1异常）
                optLog.setStatus(1);
                //错误消息
                String errorMsg = e.getMessage();
                if (errorMsg != null){
                    try {
                        errorMsg = errorMsg.substring(0, 255);
                    } catch (Exception exception) {
                    }
                    optLog.setErrorMsg(errorMsg);
                }
            }
            System.out.println(optLog);
        } catch (Exception exception) {
            // 记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息:{}", exception.getMessage());
            exception.printStackTrace();
        }
    }


    /*** 获取用户的真实IP,防止代理 */
    public String getRequestIp(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}

