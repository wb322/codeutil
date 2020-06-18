package com.bitvalue.edgecache.resp;




import java.io.Serializable;
import java.util.HashMap;

/**
 * @author wubo
 */
public class Result extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    /** 状态码 */
    public static final String CODE_TAG = "code";
    /** 成功状态码 */
    public static final int CODE_SUCCESS = 0;
    /** 成功状态码 */
    public static final int CODE_ERROR = 1;
    /** 返回内容 */
    public static final String MSG_TAG = "msg";
    /** 数据对象 */
    public static final String DATA_TAG = "data";
    /** 分页总条数对象 */
    public static final String TOTAL_TAG = "count";

    public Result() { }
    public Result(Object code,String msg) {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }
    public Result(Object code,String msg,Object data) {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        super.put(DATA_TAG,data);
    }

    public static Result success(){
        return new Result(CODE_SUCCESS,"操作成功");
    }
    public static Result success(Object data){
        return new Result(CODE_SUCCESS,"操作成功",data);
    }
    public static Result error(){
        return new Result(CODE_ERROR,"操作失败");
    }
    public static Result error(Object data){
        return new Result(CODE_ERROR,"操作失败",data);
    }
    public static Result page(Object data,Number count){
        Result result = new Result(0, "操作成功",data);
        result.put(TOTAL_TAG,count);
        return result;
    }
}
