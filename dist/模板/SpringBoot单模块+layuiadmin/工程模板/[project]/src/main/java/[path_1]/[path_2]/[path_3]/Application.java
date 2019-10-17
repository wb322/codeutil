package [package];

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("[package].dao")
@EnableSwagger2
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
