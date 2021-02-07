package com.bitvalue.edgecache.controller;

import com.bitvalue.edgecache.entity.SysMenu;
import com.bitvalue.edgecache.resp.Criteria;
import com.bitvalue.edgecache.resp.Result;
import com.bitvalue.edgecache.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * sysMenu控制器层
 * @author wubo
 */
@RestController
@CrossOrigin
@RequestMapping("/sys_menu")
public class SysMenuController extends BaseController<SysMenu,Integer,ISysMenuService>{

}
