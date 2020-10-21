package [package].resp;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;

public class PageResult<T> extends Result{

    private long current;
    private long total;

    public PageResult(IPage<T> page) {
        super(HttpStatus.HTTP_OK, "结果已分页", page.getRecords());
        this.current = page.getCurrent();
        this.total = page.getTotal();
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
