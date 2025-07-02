package com.consumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.consumer.dto.ProductResponseDto;

@Service
public class WebClientService {
	
	@Autowired
	private WebClient webClient;
	
//	WebClient webClient = WebClient.builder()
//		    .baseUrl("https://api.example.com")
//		    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer token123")
//		    .build();   we can create bean or we can specify like this
	
	public String consumeGetSApi() {
		return webClient.get()
		         .uri("/product")
//		         .header("Authorization", "Bearer your-token-here")     // 🔑 Add headers here
//		         .header("Content-Type", "application/json")    
		         .retrieve()
		         .bodyToMono(String.class)  // if we are expecting response is single object
		         .block(); //block() makes it synchronous (optional if using reactive)
	}
	
	public List<ProductResponseDto> consumeGetSApi2() {
		return webClient.get()
		         .uri("/product")
		         .retrieve()
		         .bodyToFlux(ProductResponseDto.class)  // if we are expecting response is list/stream, object
		         .collectList()
		         .block(); //block() makes it synchronous (optional if using reactive)
	}
	
	public String consumeGetSApiWithPathVeriables() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
				        .path("/employees/{department}/active")
				        .build("IT"))
//		          .header(null, null)
	//			.path("/employees/{department}/{location}/{status}")
	//	        .build("IT", "Pune", "active")) 
		         .retrieve()
		         .bodyToMono(String.class)  //convert this  string data into JsonObject using Gson() for data processing
		         .block(); //block() makes it synchronous (optional if using reactive)
	}
	
	public List<String> consumeGetSApiWithQueryParameters() {
		return  webClient.get()
			    .uri(uriBuilder -> uriBuilder
			        .path("/employees")
			        .queryParam("page", 1)
			        .queryParam("size", 10)
			        .build())
			    .retrieve()
			    .bodyToFlux(String.class)
			    .collectList()
			    .block();

	}
	
	public String consumePostApi() {
		ProductResponseDto product = new ProductResponseDto();
		product.setProductId("25");
		product.setName("car");
		product.setPrice("9754896");
		return webClient.post()
				 .uri("/product")
//				 .header("Authorization", "Bearer your-token-here")     // 🔑 Add headers here
//				  .header("Content-Type", "application/json")    
				 .bodyValue(product)
				 .retrieve()
				 .bodyToMono(String.class)
				 .block();
		}
	
	

}
