package [package].controller;

import [package].resp.Criteria;
import [package].resp.Result;
import [package].service.IService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;


public class BaseController<T, ID extends Serializable,SERVICE extends IService<T,ID>> {

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
        return Result.success(iService.findAll());
    }

    /**
     * 根据ID查询
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable ID id) {
        return Result.success(iService.findById(id));
    }

    /**
     * 根据条件查询
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @PostMapping(value = "/criteria")
    public Result findByCriteria(Criteria<T> criteria) {
        return Result.success(iService.findByCriteria(criteria));
    }

    /**
     * 分页条件查询
     */
    @Log(module = "基础功能",type = LogType.SELECT)
    @PostMapping(value = "/page")
    public Result findPageByCriteria(@RequestBody Criteria<T> criteria) {
        Page<T> page = iService.findPageByCriteria(criteria);
        return Result.page(page.getContent(),page.getTotalElements());
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
        iService.update(t);
        return Result.success();
    }

    /**
     * 根据条件删除
     */
    @Log(module = "基础功能",type = LogType.DELETE)
    @DeleteMapping("/criteria")
    public Result deleteByCriteria(@RequestBody T t) {
        iService.deleteByCriteria(t);
        return Result.success();
    }

    /**
     * 根据主键删除
     */
    @Log(module = "基础功能",type = LogType.DELETE)
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable ID id) {
        iService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据主键批量删除
     */
    @Log(module = "基础功能",type = LogType.DELETE)
    @DeleteMapping
    public Result deleteByIds(@RequestBody ID[] ids) {
        iService.deleteByIds(Arrays.asList(ids));
        return Result.success();
    }
}
