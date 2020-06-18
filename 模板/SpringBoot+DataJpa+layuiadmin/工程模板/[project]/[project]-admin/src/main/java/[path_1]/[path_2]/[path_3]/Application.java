package [package];

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
