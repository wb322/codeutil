package com.bitvalue.edgecache.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.bitvalue.edgecache.Config;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.tools.ToolUtils;
import com.bitvalue.edgecache.annotation.log.Log;
import com.bitvalue.edgecache.annotation.log.LogType;
import com.bitvalue.edgecache.entity.SysUser;
import com.bitvalue.edgecache.service.ISafeSystemService;
import com.bitvalue.edgecache.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * sysUser控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/sys_user")
public class SysUserController extends BaseController<SysUser,Integer,ISysUserService>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ISafeSystemService safeSystemService;

    @Override
    public Result save(SysUser sysUser) {
        if (sysUser.getPassword() != null){
            sysUser.setPassword(SecureUtil.md5(sysUser.getPassword()));
        }
        getService().save(sysUser);
        return Result.success();
    }

    @Override
    public Result updateById(@RequestBody SysUser sysUser) {
        if (sysUser.getPassword() != null){
            sysUser.setPassword(SecureUtil.md5(sysUser.getPassword()));
        }
        getService().update(sysUser);
        return Result.success();
    }

    @Log(module = "登录",type = LogType.VALIDATE)
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
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return Result.success();
    }

    @Log(module = "登录",type = LogType.VALIDATE)
    @GetMapping("/password/{password}")
    public Result logout(@SessionAttribute(name = "login_user") String username,@PathVariable String password){

        getService().updatePasswordByUsername(username,SecureUtil.md5(password));

        return Result.success("修改密码成功",null);
    }
}
