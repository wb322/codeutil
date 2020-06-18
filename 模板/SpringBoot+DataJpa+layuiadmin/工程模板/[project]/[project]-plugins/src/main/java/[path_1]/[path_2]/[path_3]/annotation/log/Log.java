package [package].annotation.log;

import java.lang.annotation.*;

/**
 * @author [author]
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /** 模块 */
    public String module() default "";

    /** 操作类型 */
    public LogType type() default LogType.OTHER;

}
