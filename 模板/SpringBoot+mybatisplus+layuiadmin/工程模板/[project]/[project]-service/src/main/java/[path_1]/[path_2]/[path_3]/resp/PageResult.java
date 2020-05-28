package [package].resp;

/**
 * @author [author]
 */
public class PageResult extends Result{


    /**
     * 总数
     */
    private Long total;

    public PageResult(Integer code, String msg, Object data, Long total) {
        super (code, msg, data);
        this.total = total;
    }
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}