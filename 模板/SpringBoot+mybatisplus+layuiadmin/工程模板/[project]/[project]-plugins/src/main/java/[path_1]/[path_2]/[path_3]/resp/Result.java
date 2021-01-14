package [package].resp;

import cn.hutool.http.HttpStatus;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author wubo
 */
public class Result<T> implements Serializable {

    private static final Long serialVersionUID = -4505655308965878999L;

    /** 状态码 */
    private int code;
    /** 返回信息 */
    private String msg;
    /** 返回内容 */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {}
    public Result(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(int code,String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(HttpStatus.HTTP_OK,"操作成功");
    }
    public static <T> Result<T> success(T data){
        return new Result<T>(HttpStatus.HTTP_OK,"操作成功",data);
    }
    public static <T> Result<T> success(String msg,T data){
        return new Result<T>(HttpStatus.HTTP_OK,msg,data);
    }
    public static Result error(){
        return new Result(HttpStatus.HTTP_INTERNAL_ERROR,"操作失败");
    }
    public static Result error(String msg){
        return new Result(HttpStatus.HTTP_INTERNAL_ERROR,msg);
    }
    public static Result error(int code,String msg){
        return new Result(code,msg);
    }
    public static <T> PageResult<T> page(IPage page){
        return new PageResult<T>(page);
    }
}
