package [package];

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("[package].dao")
@EnableSwagger2
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		PaginationInnerInterceptor pagination = new PaginationInnerInterceptor(DbType.MYSQL);
		// 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
		// pagination.setOverflow(false);
		// 设置最大单页限制数量，默认 500 条，-1 不受限制
		// pagination.setLimit(500);
		interceptor.addInnerInterceptor(pagination);
		return interceptor;
	}

}
