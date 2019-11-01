package [package].controller;

import java.util.Arrays;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import [package].entity.[Table2];
import [package].service.I[Table2]Service;
import [package].commons.resp.*;
/**
 * [comment]控制器层
 * @author [author]
 */
@RestController
@CrossOrigin
@RequestMapping("/[table2]")
public class [Table2]Controller extends BaseController{

	@Autowired
	private I[Table2]Service [table2]Service;

	/**
	 * 查询全部数据
	 */
	@GetMapping
	public Result getAll(){
		return Result.body (ResultEnum.SELECT_SUCCESS,[table2]Service.list ());
	}
	/**
	 * 根据ID查询
	 */
	@GetMapping(value = "/{[key2]}")
	public Result getById(@PathVariable [keyType] [key2]){
		return Result.body (ResultEnum.SELECT_SUCCESS,[table2]Service.getById ([key2]));
	}
	/**
	 * 根据条件查询
	 */
	@PostMapping(value = "/criteria")
	public Result getByCriteria(@RequestBody Criteria<[Table2]> criteria){
		QueryWrapper<[Table2]> queryWrapper = criteria.getQueryWrapper ();
		return  Result.body (ResultEnum.SELECT_SUCCESS,[table2]Service.list (queryWrapper));
	}
	/**
	 * 分页条件查询
	 */
	@PostMapping(value = "/page")
	public Result getByPage(@RequestBody Criteria<[Table2]> criteria){
		Page<[Table2]> page = criteria.getQueryPage ();
		QueryWrapper<[Table2]> queryWrapper = criteria.getQueryWrapper ();
		return  Result.body ([table2]Service.page (page,queryWrapper));
	}
	/**
	 * 增加
	 * @param [table2]
	 */
	@PostMapping
	public Result add(@RequestBody [Table2] [table2]){
		return Result.body([table2]Service.save ([table2]),ResultEnum.INSERT_SUCCESS,ResultEnum.INSERT_ERROR);
	}
	/**
	 * 根据ID修改
	 * @param [table2]
	 */
	@PutMapping
	public Result updateById(@RequestBody [Table2] [table2]){
		return Result.body ([table2]Service.updateById ([table2]),ResultEnum.UPDATE_SUCCESS,ResultEnum.UPDATE_ERROR);
	}
	/**
	 * 根据条件修改
	 * @param before 条件
	 * @param after 修改
	 */
	@PutMapping("/criteria")
	public Result updateByCriteria(@RequestBody [Table2] before,[Table2] after){
		QueryWrapper<[Table2]> queryWrapper = new QueryWrapper<> ();
		queryWrapper.allEq (BeanUtil.beanToMap(before));
		return Result.body ([table2]Service.update (after,queryWrapper),ResultEnum.UPDATE_SUCCESS,ResultEnum.UPDATE_ERROR);
	}
	/**
	 * 根据条件删除
	 * @param criteria
	 */
	@DeleteMapping("/criteria")
	public Result deleteByCriteria(@RequestBody Criteria<[Table2]> criteria){
		QueryWrapper<[Table2]> queryWrapper = criteria.getQueryWrapper ();
		return Result.body ([table2]Service.remove(queryWrapper),ResultEnum.DELETE_SUCCESS,ResultEnum.DELETE_ERROR);
	}
	/**
	 * 根据主键删除
	 * @param [key2]
	 */
	@DeleteMapping(value = "/{[key2]}")
	public Result deleteById(@PathVariable [keyType] [key2]){
		return Result.body ([table2]Service.removeById ([key2]),ResultEnum.DELETE_SUCCESS,ResultEnum.DELETE_ERROR);
	}
	/**
	 * 根据主键批量删除
	 * @param [key2]s
	 */
	@DeleteMapping
	public Result deleteByIds(@RequestBody [keyType][] ids){
		return Result.body ([table2]Service.removeByIds (Arrays.asList (ids)),ResultEnum.DELETE_SUCCESS,ResultEnum.DELETE_ERROR);
	}
}
