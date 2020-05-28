package [package].commons.resp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author [author]
 */
public class Criteria<T>{
    /**
     * 当前页
     */
    private long page;
    /**
     * 每页条数
     */
    private long limit;
    /**
     * 正序排序的字段,逗号分隔
     */
    private String ascs;
    /**
     * 反序排序的字段,逗号分隔
     */
    private String descs;
    /**
     * 查询条件
     */
    private T params;

    /**
     * 获取查询条件
     */
    @JsonIgnore
    public QueryWrapper<T> getQueryWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper ();
        if (this.params != null || !StrUtil.hasEmpty (this.ascs) || !StrUtil.hasEmpty (this.descs)){
            if (this.params != null){
                queryWrapper.setEntity (this.params);
            }
            if (!StrUtil.hasEmpty (this.ascs)){
                queryWrapper.orderByAsc (this.ascs.split (","));
            }
            if (!StrUtil.hasEmpty (this.descs)){
                queryWrapper.orderByAsc (this.descs.split (","));
            }
        }else{
            queryWrapper = Wrappers.emptyWrapper ();
        }
        return queryWrapper;
    }
    /**
     * 获取分页条件
     */
    @JsonIgnore
    public Page<T> getQueryPage() {
        Page<T> page = new Page<> ();
        page.setCurrent (this.page);
        page.setSize (this.limit);
        return page;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public String getAscs() {
        return ascs;
    }

    public void setAscs(String ascs) {
        this.ascs = ascs;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
