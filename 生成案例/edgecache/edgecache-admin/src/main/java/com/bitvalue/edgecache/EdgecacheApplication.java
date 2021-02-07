package com.bitvalue.edgecache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
public class EdgecacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgecacheApplication.class, args);
	}


}
