package [package].resp;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

/**
 * @author [author]
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static Result body(ResultEnum resultEnum){
        return new Result (resultEnum.getCode (), resultEnum.getMsg ());
    }
    public static Result body(boolean b,ResultEnum successEnum,ResultEnum errorEnum){
        if (b){
            return new Result (successEnum.getCode (), successEnum.getMsg ());
        }else{
            return new Result (errorEnum.getCode (), errorEnum.getMsg ());
        }
    }
    public static Result body(ResultEnum resultEnum,Object data){
        return new Result (resultEnum.getCode (), resultEnum.getMsg (),data);
    }
    public static Result body(Integer code,String msg,Object data){
        return new Result (code,msg,data);
    }
    public static Result body(IPage page){
        return new PageResult (ResultEnum.SELECT_SUCCESS.getCode (),ResultEnum.SELECT_SUCCESS.getMsg (),page.getRecords (),page.getTotal ());
    }
}
