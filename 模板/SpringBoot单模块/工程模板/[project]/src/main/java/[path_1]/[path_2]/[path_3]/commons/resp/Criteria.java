package [package].commons.resp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;

/**
 * @author [author]
 */
public class Criteria<T>{
    /**
     * 分页条件
     */
    private Page<T> page;
    /**
     * 查询条件(实体类和map可选)
     */
    private T entity;
    private Map params;
    /**
     * 获取查询条件
     * @return
     */
    @JsonIgnore
    public QueryWrapper<T> getQueryWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper ();
        if (entity != null){
            queryWrapper.setEntity (entity);
        }else if (params != null && params.keySet ().size () >0){
            queryWrapper.allEq (params);
        }else{
            queryWrapper = Wrappers.emptyWrapper ();
        }
        return queryWrapper;
    }
    /**
     * 获取分页条件
     * @return
     */
    public Page<T> getPage(){
        return page;
    }

    public Criteria() { }



    public void setPage(Page<T> page) {
        this.page = page;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
