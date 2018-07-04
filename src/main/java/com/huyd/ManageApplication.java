package com.huyd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan(basePackages = "com.huyd.domain")
@SpringBootApplication
public class ManageApplication extends SpringBootServletInitializer{

//	public static void main(String[] args) {
//		SpringApplication.run(ManageApplication.class, args);
//	}
public static void main(String[] args) {
	SpringApplication.run(applicationClass, args);
}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<ManageApplication> applicationClass = ManageApplication.class;

}
