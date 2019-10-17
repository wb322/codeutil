package [package].controller;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import [package].entity.[Table2];
import [package].service.[Table2]Service;
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
	private [Table2]Service [table2]Service;

	/**
	 * 查询全部数据
	 */
	@GetMapping()
	public Result getAll(){
		List<[Table2]> list = [table2]Service.list ();
		return Result.body (ResultEnum.SELECT_SUCCESS,list);
	}
	/**
	 * 根据ID查询
	 */
	@GetMapping(value = "/{[key2]}")
	public Result getById(@PathVariable [keyType] [key2]){
		[Table2] [table2] = [table2]Service.getById ([key2]);
		return Result.body (ResultEnum.SELECT_SUCCESS,[table2]);
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
		Page<[Table2]> page = criteria.getPage ();
		QueryWrapper<[Table2]> queryWrapper = criteria.getQueryWrapper ();
		IPage<[Table2]> iPage = [table2]Service.page (page,queryWrapper);
		return  Result.body (iPage);
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
	public Result update(@RequestBody [Table2] [table2]){
		return Result.body ([table2]Service.updateById ([table2]),ResultEnum.UPDATE_SUCCESS,ResultEnum.UPDATE_ERROR);
	}
	/**
	 * 根据条件修改
	 * @param criteria entity要修改的字段 params条件
	 */
	@PutMapping("/criteria")
	public Result updateList(@RequestBody Criteria<[Table2]> criteria){
		[Table2] [table2] = criteria.getEntity ();
		Map params = criteria.getParams ();
		QueryWrapper<[Table2]> queryWrapper = new QueryWrapper<> ();
		queryWrapper.allEq (params);
		return Result.body ([table2]Service.update ([table2],queryWrapper),ResultEnum.UPDATE_SUCCESS,ResultEnum.UPDATE_ERROR);
	}
	/**
	 * 根据主键批量删除
	 * @param [key2]s
	 */
	@DeleteMapping
	public Result deleteByIds([keyType][] ids){
		boolean b = [table2]Service.removeByIds (Arrays.asList (ids));
		return Result.body (b,ResultEnum.DELETE_SUCCESS,ResultEnum.DELETE_ERROR);
	}
	/**
	 * 根据主键删除
	 * @param [key2]
	 */
	@DeleteMapping(value = "/{[key2]}")
	public Result delete(@PathVariable [keyType] [key2]){
		return Result.body ([table2]Service.removeById ([key2]),ResultEnum.DELETE_SUCCESS,ResultEnum.DELETE_ERROR);
	}
}
