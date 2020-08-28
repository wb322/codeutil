package [package].controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import [package].resp.Criteria;


public class BaseController<T>{

    /** 获取包装查询条件 */
    public QueryWrapper<T> queryWrapper(Criteria<T> criteria) {
        QueryWrapper<T> queryWrapper = new QueryWrapper ();
        if (criteria.getParams() != null){
            queryWrapper.setEntity (criteria.getParams());
        }
        if (StrUtil.isNotEmpty (criteria.getAsc())){
            queryWrapper.orderByAsc (criteria.getAsc().split (","));
        }
        if (StrUtil.isNotEmpty (criteria.getDesc())){
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
