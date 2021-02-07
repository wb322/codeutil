package com.bitvalue.edgecache.annotation.log;

import com.bitvalue.edgecache.entity.SysLog;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISysLogService;
import com.bitvalue.edgecache.tools.JsonUtil;
import com.bitvalue.edgecache.tools.ToolUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * AOP日志
 *
 * @author wubo
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private Log log;

    @Autowired
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
            SysLog optLog = new SysLog();
            //模块
            String module = log.module();
            optLog.setModule(module);
            //操作类型
            LogType type = log.type();
            optLog.setType(type.toString());
            //匹配的类.方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            optLog.setCodeMethod(className + "." + methodName + "()");
            //请求ip
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String username = (String) request.getSession().getAttribute("login_user");
            optLog.setUsername(username);
            String ip = ToolUtils.getRequestIp(request);
            optLog.setIp(ip);
            //请求地址
            String localAddr = request.getLocalAddr();
            String uri = request.getRequestURI();
            optLog.setUrl(localAddr + uri);
            //请求方法
            String requestMethod = request.getMethod();
            optLog.setHttpMethod(requestMethod);
            //请求参数
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length != 0){
                List params = new ArrayList();
                for (Object arg : args) {
                    if (!(arg instanceof ServletRequest) && !(arg instanceof ServletResponse) && !(arg instanceof MultipartFile)){
                        params.add(arg);
                    }
                }
                optLog.setParams(JsonUtil.objectToJson(params));
            }
            if (result != null && result instanceof Result){
                //返回参数
                Result r = (Result)result;
                String msgTag = Result.MSG_TAG;
                Object o = r.get(msgTag);
                optLog.setResult(o + "");
                optLog.setStatus(0);
            }
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
                    optLog.setResult(errorMsg);
                }
            }
            sysLogService.save(optLog);
        } catch (Exception exception) {
            // 记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息:{}", exception.getMessage());
            exception.printStackTrace();
        }
    }
}

