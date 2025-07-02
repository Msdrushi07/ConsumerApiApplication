package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ConsunmerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsunmerAppApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public WebClient webClient() {
		return  WebClient.builder()
							.baseUrl("http://localhost:8080")
				//			.defaultHeader(null, null)
				//			.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer token123")
							.build();
	}
				

}
