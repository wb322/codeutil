package [package].annotation.log;

import [package].entity.SysLog;
import jx.bf.wb.resp.Result;
import [package].service.ISysLogService;
import [package].tools.JsonUtil;
import [package].tools.ToolUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Value;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
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

    @Value("${plugin.log.save}")
    private Boolean plugin_log_save;

    @Resource
    private ISysLogService sysLogService;

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
            SysLog sysLog = new SysLog();
            //模块
            String module = log.module();
            sysLog.setModule(module);
            //操作类型
            LogType type = log.type();
            sysLog.setType(type.toString());
            //匹配的类.方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            sysLog.setCodeMethod(className + "." + methodName + "()");
            //请求ip
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String ip = ToolUtils.getRequestIp(request);
            sysLog.setIp(ip);
            //请求url
            String url = request.getRequestURI();
            sysLog.setUrl(url);
            //请求方法
            String requestMethod = request.getMethod();
            sysLog.setHttpMethod(requestMethod);
            //请求参数
            String args = JsonUtil.objectToJson(joinPoint.getArgs());
            sysLog.setParams(args);
            //响应码
            if (result != null && result instanceof Result){
                Result r = (Result)result;
                sysLog.setResult(r.getCode() + "");
            }
            if (e != null) {
                //操作状态（0正常 1异常）
                sysLog.setStatus(1);
                //错误消息
                String message = e.getMessage();
                if (message != null && message.length() > 500){
                    message = message.substring(0, 500);
                }
                sysLog.setErrorMsg(message);
            }
            String json = JsonUtil.objectToJson(sysLog);
            logger.info(json);
            if (plugin_log_save){
                sysLogService.save(sysLog);
            }
        } catch (Exception exception) {
            // 记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息:{}", exception.getMessage());
            exception.printStackTrace();
        }
    }



}

