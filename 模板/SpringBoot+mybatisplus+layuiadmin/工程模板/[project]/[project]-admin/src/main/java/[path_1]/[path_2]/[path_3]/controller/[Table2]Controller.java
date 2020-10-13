package [package].controller;

import [package].annotation.log.Log;
import [package].annotation.log.LogType;
import [package].resp.*;
import [package].entity.[Table2];
import [package].service.I[Table2]Service;
import java.util.Arrays;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * [comment]控制器
 * @author [author]
 */
@RestController
@CrossOrigin
@RequestMapping("/[table]")
public class [Table2]Controller extends BaseController{
    @Autowired
    private I[Table2]Service [table2]Service;

    @Log(module = "查询所有",type = LogType.SELECT)
    @GetMapping
    public Result<[Table2]> findAll() {
        return Result.success([table2]Service.list());
    }

    @Log(module = "根据ID查询",type = LogType.SELECT)
    @GetMapping(value = "/{id}")
    public Result<[Table2]> findById(@PathVariable [keyType] id) {
        return Result.success([table2]Service.getById(id));
    }

    @Log(module = "分页条件查询",type = LogType.SELECT)
    @PostMapping(value = "/page")
    public Result<[Table2]> findPageByCriteria(@RequestBody Criteria<[Table2]> criteria){
        IPage page=[table2]Service.page(queryPage(criteria),queryWrapper(criteria));
        return Result.page(page.getRecords(),page.getTotal());
    }

    @Log(module = "添加",type = LogType.INSERT)
    @PostMapping
    public Result<[Table2]> save(@RequestBody [Table2] [table2]) {
        [table2]Service.save([table2]);
        return Result.success();
    }

    @Log(module = "根据ID修改",type = LogType.UPDATE)
    @PutMapping
    public Result<[Table2]> updateById(@RequestBody [Table2] [table2]) {
        [table2]Service.updateById([table2]);
        return Result.success();
    }

    @Log(module = "根据条件删除",type = LogType.DELETE)
    @DeleteMapping("/criteria")
    public Result<[Table2]> deleteByCriteria(@RequestBody [Table2] [table2]) {
        [table2]Service.removeByMap(BeanUtil.beanToMap([table2]));
        return Result.success();
    }

    @Log(module = "根据主键删除",type = LogType.DELETE)
    @DeleteMapping(value = "/{id}")
    public Result<[Table2]> deleteById(@PathVariable [keyType] id) {
        [table2]Service.removeById(id);
        return Result.success();
    }

    @Log(module = "根据主键批量删除",type = LogType.DELETE)
    @DeleteMapping
    public Result<[Table2]> deleteByIds(@RequestBody [keyType][] ids) {
        [table2]Service.removeByIds(Arrays.asList(ids));
        return Result.success();
    }

}
