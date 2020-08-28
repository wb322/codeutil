package jx.bf.wb.resp;

import java.io.Serializable;

/**
 * @author wubo
 */
public class Result<T> implements Serializable {

    private static final Long serialVersionUID = -4505655308965878999L;

    /** 状态码 */
    private Long code;
    /** 返回信息 */
    private String msg;
    /** 返回内容 */
    private T data;
    private Long count;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Result() { }
    public Result(Long code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(Long code,String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(0L,"操作成功");
    }
    public static Result success(Object data){
        return new Result(0L,"操作成功",data);
    }
    public static Result success(String msg,Object data){
        return new Result(0L,msg,data);
    }
    public static Result error(){
        return new Result(1L,"操作失败");
    }
    public static Result error(String msg){
        return new Result(1L,msg);
    }
    public static Result page(Object data,Long count){
        Result result = new Result(0L, "操作成功",data);
        result.setCount(count);
        return result;
    }
}
