package [package].interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Jackson全局转化Long类型为String，解决jackson序列化时传入前端Long类型缺失精度问题
 */
@Configuration
public class JsonTypeConverter implements InitializingBean {
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        // 暂时放弃对小long的转换，约定与前端交互数据时，大Long全部转换成字符串
        //simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        objectMapper.setSerializationInclusion(NON_NULL);
        // 指定json转换时间类型的时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 指定返回的时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }


    /** 若只需要转换类型,不对objectMapper进行其它配置,可在任意配置类中添加此方法 */
	/*
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		Jackson2ObjectMapperBuilderCustomizer cunstomizer = new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
				jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
				//jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
			}
		};
		return cunstomizer;
	}*/
}
