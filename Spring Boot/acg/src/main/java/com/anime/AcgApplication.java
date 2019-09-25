package com.anime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * pringBootApplication：springboot的核心注解，开启自动配置
 */
@SpringBootApplication
public class AcgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcgApplication.class, args);
		System.out.println("app start...");
	}
}
