package [package].handler;

import [package].commons.resp.Result;
import [package].commons.tools.HttpUtils;
import [package].controller.BaseController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * AOP日志
 * @author [author]
 */
@Component
@Aspect
public class LogAspect extends BaseController {

    /**
     * 切点
     */
    @Pointcut(value = "execution(public * [package].controller..*.*(..))")
    public void pointcut() {

    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Around (value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        Object object = null;
        try {
            // 获取当前的HttpServletRequest对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            Map<String,Object> map = new LinkedHashMap<> ();

            // 获取请求类名和方法名称
            Signature signature = joinPoint.getSignature();
            String methodName = signature.getName ();
            String className = signature.getDeclaringTypeName ();
            String simpleName = signature.getDeclaringType ().getSimpleName ();
            //获取代理类
            Class<?> aClass = Class.forName (className);
            // 请求IP地址
            String ip = HttpUtils.getRequestIp(request);
            map.put("ip",ip);
            // 请求全路径
            String url = request.getRequestURI();
            map.put("path",url);
            // 获取请求方式
            String requestMethod = request.getMethod();
            map.put("type",requestMethod);

            // 获取请求内容类型
            String contentType = request.getContentType();
            map.put("contentType",contentType);
            //获取请求参数
            Object[] args = joinPoint.getArgs();
            map.put("args",args);

            // 获取请求头token
            //String header = request.getHeader ("");
            map.put("token",null);

            //日志记录请求内容
            Logger logger = LoggerFactory.getLogger (aClass);
            logger.info ("请求 {} : {}" ,simpleName + "." +methodName,objectToJson (map));
            //执行织入的方法
            object = joinPoint.proceed ();
            if (object instanceof Result){
                Result result = (Result) object;
                Object data = result.getData ();
                result.setData (null);
                //日志记录响应结果
                logger.info ("响应 {} : {}" ,simpleName + "." +methodName,objectToJson (result));
                result.setData (data);
            }else if (object instanceof ModelAndView){
                ModelAndView modelAndView  = (ModelAndView) object;
                Map<String, Object> model = modelAndView.getModel ();
                logger.info ("跳转 {} : {}" ,simpleName + "." +methodName,objectToJson (modelAndView));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}

