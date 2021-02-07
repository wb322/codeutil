package com.bitvalue.edgecache.resp;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author wubo
 */
public class Criteria<T>{

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页",allowEmptyValue = true)
    private Integer page;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数",allowEmptyValue = true)
    private Integer limit;
    /**
     * 正序排序的字段
     */
    @ApiModelProperty(value = "正序排序的字段",allowEmptyValue = true)
    private String asc;
    /**
     * 反序排序的字段
     */
    @ApiModelProperty(value = "反序排序的字段",allowEmptyValue = true)
    private String desc;
    /**
     * 查询条件
     */
    @ApiModelProperty(value = "查询条件",allowEmptyValue = true)
    private T params;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getAsc() {
        return asc;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
