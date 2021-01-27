package com.fs.cta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fs.cta")
public class CtaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtaApplication.class, args);
	}

}
