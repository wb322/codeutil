package com.task.poc.commons.resp;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author [author]
 */
public class PageResult{

    /**
     * 当前页
     */
    private Long current;
    /**
     * 每页条数
     */
    private Long limit;
    /**
     * 总数
     */
    private Long total;
    /**
     * 数据
     */
    private List list;

    public PageResult() {
    }

    public PageResult(IPage page){
        this.current = page.getCurrent ();
        this.limit = page.getSize ();
        this.total = page.getTotal ();
        this.list = page.getRecords ();
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
