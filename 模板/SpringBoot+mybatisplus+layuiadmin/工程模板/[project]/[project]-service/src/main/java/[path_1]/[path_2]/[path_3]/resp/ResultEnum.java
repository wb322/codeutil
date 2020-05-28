package [package].resp;

/**
 * @author [author]
 */
public enum ResultEnum {
    //code值
    BASE_SUCCESS(0,"请求成功"),
    SELECT_SUCCESS(0,"查询成功"),
    UPDATE_SUCCESS(0,"更新成功"),
    INSERT_SUCCESS(0,"添加成功"),
    DELETE_SUCCESS(0,"删除成功"),
    BASE_ERROR(1,"请求失败"),
    SELECT_ERROR(1,"查询失败"),
    UPDATE_ERROR(1,"更新失败"),
    INSERT_ERROR(1,"添加失败"),
    DELETE_ERROR(1,"删除失败"),

    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
