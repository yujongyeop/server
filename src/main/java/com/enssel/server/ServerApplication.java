package com.enssel.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {
	@Bean
	public WebSocketHandler myWebSocketHandler() {
		return new WebSocketHandler();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
