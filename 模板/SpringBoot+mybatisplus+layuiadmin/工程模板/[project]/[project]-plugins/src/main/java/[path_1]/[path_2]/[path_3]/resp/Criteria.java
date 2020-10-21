package [package].resp;

/**
 * @author wubo
 */
public class Criteria<T>{

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer limit;
    /**
     * 正序排序的字段
     */
    private String asc;
    /**
     * 反序排序的字段
     */
    private String desc;
    /**
     * 查询条件
     */
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

    public Criteria() {}

    public Criteria(T params) {
        this.params = params;
    }
}
