package com.yunagile.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.yunagile.cloud" })
@ImportResource(locations = { "classpath:spring-context.xml" })
public class Demo1Starter {
	public static void main(String[] args) {
		SpringApplication.run(Demo1Starter.class, args);
	}
}
 