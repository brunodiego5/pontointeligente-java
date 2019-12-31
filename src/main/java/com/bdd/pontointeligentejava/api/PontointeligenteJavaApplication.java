package com.bdd.pontointeligentejava.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PontointeligenteJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontointeligenteJavaApplication.class, args);
	}

}
