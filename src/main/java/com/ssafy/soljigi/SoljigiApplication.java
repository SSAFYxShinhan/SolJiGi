package com.ssafy.soljigi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SoljigiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoljigiApplication.class, args);
	}
}