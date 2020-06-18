package [package].annotation.log;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;


public class OptLog {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** 模块 */
    private String module;

    /** 操作类型 */
    private LogType type;

    /** 匹配的类.方法 */
    private String codeMethod;

    /** 请求ip */
    private String ip;

    /** 请求url */
    private String url;

    /** 请求方法 */
    private String httpMethod;

    /** 请求参数 */
    private String params;

    /** 返回参数 */
    private String result;

    /** 操作状态（0正常 1异常） */
    private Integer status = 0;

    /** 错误消息 */
    private String errorMsg;

    /** 操作时间 */
    private Date time = new Date();

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public String getCodeMethod() {
        return codeMethod;
    }

    public void setCodeMethod(String codeMethod) {
        this.codeMethod = codeMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OptLog{" +
                "module='" + module + '\'' +
                ", type=" + type +
                ", codeMethod='" + codeMethod + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", params='" + params + '\'' +
                ", result='" + result + '\'' +
                ", status=" + status +
                ", errorMsg='" + errorMsg + '\'' +
                ", time=" + time +
                '}';
    }
}
