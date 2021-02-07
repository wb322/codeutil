package com.bitvalue.edgecache.interceptor;

import cn.hutool.core.util.StrUtil;
import com.bitvalue.edgecache.Config;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {

    //该方法最先调用，在controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean is_logined = false;
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("login_user");
            if (StrUtil.isBlank(username)) {
                int i = 1/0;
            }

            if (!Config.loginUserMap.containsKey(username)){
                int i = 1/0;
            }
            if (!session.getId().equals(Config.loginUserMap.get(username))){
                is_logined = true;
                int i = 1/0;
            }
            return true;
        } catch (Exception e) {
            String login = "/login";
            if (is_logined){
                login += "/1";
            }
            if (!StringUtils.isEmpty (request.getHeader ("x-requested-with"))){
                response.setStatus (302);
                response.setHeader ("redirect",login);
            }else{
                response.sendRedirect(login);
            }
            return false;
        }
    }

    /**
     * 该方法在controller方法执行之后调用，可以对modelAndView进行处理，比如添加属性什么的
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * 最后执行，在controller渲染视图完成之后，做清理工作,可以根据ex是否为null判断是否发生了异常，进行日志记录。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
