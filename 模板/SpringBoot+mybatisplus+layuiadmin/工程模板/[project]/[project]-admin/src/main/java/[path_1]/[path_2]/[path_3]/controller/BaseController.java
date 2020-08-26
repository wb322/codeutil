package [package].controller;

import [package].annotation.log.Log;
import [package].annotation.log.LogType;
import [package].resp.Criteria;
import [package].resp.Result;
import [package].service.IBaseService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.Arrays;


public class BaseController<T,ID extends Serializable,SERVICE extends IBaseService<T>> {

    @Autowired
    private SERVICE iService;

    public SERVICE getService(){
        return iService;
    }

    /**
     * 查询全部数据
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @GetMapping
    public Result findAll() {
        return Result.success(iService.list());
    }

    /**
     * 根据ID查询
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable ID id) {
        return Result.success(iService.getById(id));
    }

    /**
     * 根据条件查询
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @PostMapping(value = "/criteria")
    public Result findByCriteria(@RequestBody Criteria<T> criteria) {
        return Result.success(iService.list(queryWrapper(criteria)));
    }

    /**
     * 分页条件查询
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @PostMapping(value = "/page")
    public Result findPageByCriteria(@RequestBody Criteria<T> criteria) {
        IPage page = iService.page(queryPage(criteria), queryWrapper(criteria));
        return Result.page(page.getRecords(),page.getTotal());
    }
    /**
     * 增加
     */
    @Log(module = "基础功能",type = LogType.INSERT)
    @PostMapping
    public Result save(@RequestBody T t) {
        iService.save(t);
        return Result.success();
    }

    /**
     * 根据ID修改
     */
    @Log(module = "基础功能",type = LogType.UPDATE)
    @PutMapping
    public Result updateById(@RequestBody T t) {
        iService.updateById(t);
        return Result.success();
    }

    /**
     * 根据条件删除
     */
    @Log(module = "基础功能",type = LogType.DELETE)
    @DeleteMapping("/criteria")
    public Result deleteByCriteria(@RequestBody T t) {
        iService.removeByMap(BeanUtil.beanToMap(t));
        return Result.success();
    }

    /**
     * 根据主键删除
     */
    @Log(module = "基础功能",type = LogType.DELETE)
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable ID id) {
        iService.removeById(id);
        return Result.success();
    }

    /**
     * 根据主键批量删除
     */
    @Log(module = "基础功能",type = LogType.DELETE)
    @DeleteMapping
    public Result deleteByIds(@RequestBody ID[] ids) {
        iService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }
    /** 获取包装查询条件 */
    public QueryWrapper<T> queryWrapper(Criteria<T> criteria) {
        QueryWrapper<T> queryWrapper = new QueryWrapper ();
        if (criteria.getParams() != null){
            queryWrapper.setEntity (criteria.getParams());
        }
        if (!StrUtil.isNotEmpty (criteria.getAsc())){
            queryWrapper.orderByAsc (criteria.getAsc().split (","));
        }
        if (!StrUtil.isNotEmpty (criteria.getDesc())){
            queryWrapper.orderByDesc (criteria.getDesc().split (","));
        }
        return queryWrapper;
    }
    /** 获取包装分页条件 */
    public Page<T> queryPage(Criteria<T> criteria) {
        Page<T> page = new Page<> ();
        page.setCurrent (criteria.getPage());
        page.setSize (criteria.getLimit());
        return page;
    }
}
