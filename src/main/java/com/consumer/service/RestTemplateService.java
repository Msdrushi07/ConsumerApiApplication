package com.consumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consumer.dto.ProductResponseDto;

@Service
public class RestTemplateService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public String consumeApi() {
		ResponseEntity<String> res=restTemplate.exchange("http://localhost:8080/product", HttpMethod.GET, null,String.class);
	//	ResponseEntity<ProductResponseDto> res=restTemplate.exchange("http://localhost:8080/product", HttpMethod.GET, null,ProductResponseDto.class);
	// if the rest call is return any object ex Employee.class or .class then do above like this 	
		System.out.println(res.getStatusCodeValue());
		System.out.println(res.getBody()); // convert this this string data into JsonObject for data processing
		return "api consumed successfully";
	}
	
	public String consumeApi2() {
		ResponseEntity<List<ProductResponseDto>> res=restTemplate.exchange("http://localhost:8080/product", HttpMethod.GET,null,
				new ParameterizedTypeReference<List<ProductResponseDto>>() {
				});
// if the response is list the we can use new ParameterizedTypeReference<List<ProductResponseDto>>() { } also 
//		new ParameterizedTypeReference<ProductResponseDto>() { }
		System.out.println(res.getStatusCodeValue());
		System.out.println(res.getBody()); 
		return "api consumed successfully";
	
	}
	
	public void consumeGetWithUriVariables() {
		String url = "https://api.example.com/employees/{department}/active";

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("department", "IT");

		ResponseEntity<List<ProductResponseDto>> response = restTemplate.exchange(
		    url,
		    HttpMethod.GET,
		    null,
		    new ParameterizedTypeReference<List<ProductResponseDto>>() {},
		    uriVariables
		);

		List<ProductResponseDto> employees = response.getBody();

	}
	
	public String consumeApiPost() {
		ProductResponseDto product = new ProductResponseDto();
		product.setProductId("18");
		product.setName("bike");
		product.setPrice("475856");
		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type","application/json");
//		headers.set("Authorization", "Bearer <token>");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProductResponseDto> requestData = new HttpEntity<>(product,headers);
		ResponseEntity<String> res=restTemplate.exchange("http://localhost:8080/product", HttpMethod.POST, requestData, String.class);
		System.out.println(res.getStatusCodeValue());
		System.out.println(res.getBody()); // convert this this string data into JsonObject for data processing
		return "api consumed successfully";
		
	}
	

}
