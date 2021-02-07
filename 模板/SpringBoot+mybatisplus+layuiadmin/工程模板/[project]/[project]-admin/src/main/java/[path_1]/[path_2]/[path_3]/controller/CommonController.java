package [package].controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import [package].Config;
import [package].resp.Result;
import [package].tools.ToolUtils;
import [package].annotation.log.Log;
import [package].annotation.log.LogType;
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author [author]
 */
@RestController
public class CommonController {
    /**
     * 跳转到主页
     */
    @GetMapping(value = {"/","/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView ("index");
        return modelAndView;
    }
    /**
     * 通用跳转方法
     */
    @GetMapping(value = {"/menu/{module}/{view}","/menu/{module}/{view}/{key}"})
    public ModelAndView redirect(@PathVariable String module, @PathVariable String view, @PathVariable(required = false) Object key){
        ModelAndView modelAndView = new ModelAndView (module + "/" + view);
        if (key != null){
            modelAndView.addObject ("key",key);
        }
        return modelAndView;
    }
    /**
     * 接口文档
     */
    @GetMapping("/swagger")
    public void swagger2(HttpServletResponse response)throws Exception{
        response.sendRedirect ("swagger-ui.html#/");
    }
    @GetMapping("/console")
    public ModelAndView console(){
        ModelAndView modelAndView = new ModelAndView ("console");
        return modelAndView;
    }
    /**
     * 注册
     */
    @GetMapping(value = {"/login","/login/{type}"})
    public ModelAndView login(@PathVariable(required = false) Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView("login");
        String msg = "";
        if (type != null){
            switch (type){
                case 1: msg = "该用户已在其它地方登录，请确认是否为本人登录！";break;
                default:msg = "参数错误";
            }
            modelAndView.addObject("msg",msg);
        }
        return modelAndView;
    }
    /*@Log(module = "登录",type = LogType.VALIDATE)
    @PostMapping("/login")
    public Result login(HttpServletRequest request, String username, String password){
        HttpSession session = request.getSession();
        session.setAttribute("login_user",username);
        try {
            boolean no_contain = true;
            String requestIp = ToolUtils.getRequestIp(request);
            if (Config.control_center.contains(requestIp)){
                no_contain = false;
            }else{
                String networkSegments =safeSystemService.findAllNetworkSegment();
                List<String> patterns = StrUtil.splitTrim(networkSegments, ";");
                for (String pattern : patterns) {
                    if (ReUtil.isMatch(pattern, requestIp)){
                        no_contain = false;
                        break;
                    }
                }
            }
            if(no_contain){
                int i = 1/0;
            }
        } catch (Exception e) {
            return Result.error("当前IP无法访问，请联系管理员！");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(SecureUtil.md5(password));
        sysUser.setEnabled(true);
        if (StrUtil.isBlank(username) || StrUtil.isBlank(username) || !getService().exist(sysUser)){
            return Result.error("账号或密码错误");
        }
        String sessionId = session.getId();
        if (Config.loginUserMap.containsKey(username) && !sessionId.equals(Config.loginUserMap.get(username))){
            logger.info("用户：" + sysUser.getUsername() + "重新登录，清除之前的session");
            Config.loginUserMap.remove(username);
        }
        Config.loginUserMap.put(username, sessionId);
        return Result.success("登录成功",null);
    }*/
    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return Result.success();
    }

    @Log(module = "登录",type = LogType.VALIDATE)
    @GetMapping("/password/{password}")
    public Result logout(@SessionAttribute(name = "login_user") String username,@PathVariable String password){

        //getService().updatePasswordByUsername(username,SecureUtil.md5(password));

        return Result.success("修改密码成功",null);
    }
}
