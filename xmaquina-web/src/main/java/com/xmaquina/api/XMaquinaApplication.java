package com.xmaquina.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class XMaquinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(XMaquinaApplication.class, args);
	}


}
