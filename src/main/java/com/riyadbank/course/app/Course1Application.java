package com.riyadbank.course.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Course1Application {

	public static void main(String[] args) {
		SpringApplication.run(Course1Application.class, args);
	}

}
