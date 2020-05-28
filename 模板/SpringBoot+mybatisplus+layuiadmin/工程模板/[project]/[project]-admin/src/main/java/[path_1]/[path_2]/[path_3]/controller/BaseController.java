package

[package].controller;

import java.util.Arrays;
import java.io.Serializable;

import cn.hutool.core.bean.BeanUtil;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import [package].resp.*;

public class BaseController<T, ID extends Serializable> {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IService<T> iService;

    /**
     * 查询全部数据
     */
    @GetMapping
    public Result getAll() {
        return Result.body(ResultEnum.SELECT_SUCCESS, iService.list());
    }

    /**
     * 根据ID查询
     */
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable ID id) {
        return Result.body(ResultEnum.SELECT_SUCCESS, iService.getById(id));
    }

    /**
     * 根据条件查询
     */
    @PostMapping(value = "/criteria")
    public Result getByCriteria(@RequestBody Criteria<T> criteria) {
        QueryWrapper<T> queryWrapper = criteria.getQueryWrapper();
        return Result.body(ResultEnum.SELECT_SUCCESS, iService.list(queryWrapper));
    }

    /**
     * 分页条件查询
     */
    @PostMapping(value = "/page")
    public Result getByPage(@RequestBody Criteria<T> criteria) {
        Page<T> page = criteria.getQueryPage();
        QueryWrapper<T> queryWrapper = criteria.getQueryWrapper();
        return Result.body(iService.page(page, queryWrapper));
    }

    /**
     * 增加
     */
    @PostMapping
    public Result add(@RequestBody T T) {
        return Result.body(iService.save(T), ResultEnum.INSERT_SUCCESS, ResultEnum.INSERT_ERROR);
    }

    /**
     * 根据ID修改
     */
    @PutMapping
    public Result updateById(@RequestBody T T) {
        return Result.body(iService.updateById(T), ResultEnum.UPDATE_SUCCESS, ResultEnum.UPDATE_ERROR);
    }

    /**
     * 根据条件修改
     *
     * @param before 条件
     * @param after  修改
     */
    @PutMapping("/criteria")
    public Result updateByCriteria(@RequestBody T before, T after) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(BeanUtil.beanToMap(before));
        return Result.body(iService.update(after, queryWrapper), ResultEnum.UPDATE_SUCCESS, ResultEnum.UPDATE_ERROR);
    }

    /**
     * 根据条件删除
     */
    @DeleteMapping("/criteria")
    public Result deleteByCriteria(@RequestBody Criteria<T> criteria) {
        QueryWrapper<T> queryWrapper = criteria.getQueryWrapper();
        return Result.body(iService.remove(queryWrapper), ResultEnum.DELETE_SUCCESS, ResultEnum.DELETE_ERROR);
    }

    /**
     * 根据主键删除
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable ID id) {
        return Result.body(iService.removeById(id), ResultEnum.DELETE_SUCCESS, ResultEnum.DELETE_ERROR);
    }

    /**
     * 根据主键批量删除
     */
    @DeleteMapping
    public Result deleteByIds(@RequestBody ID[] ids) {
        return Result.body(iService.removeByIds(Arrays.asList(ids)), ResultEnum.DELETE_SUCCESS, ResultEnum.DELETE_ERROR);
    }


    /**
     * 获取用户的真实IP,防止代理
     *
     * @param request
     */
    public String getIpAddr(HttpServletRequest request) {
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

    /**
     * 对象转成json
     *
     * @param object
     * @return
     */
    public String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    /**
     * json转对象
     *
     * @param c
     * @return
     */
    public Object jsonToObject(String json, Class c) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object obj = objectMapper.readValue(json, c);
            return obj;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }
}
