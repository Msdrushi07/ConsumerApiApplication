package com.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.dto.ProductResponseDto;
import com.consumer.service.WebClientService;

@RestController
@RequestMapping("/web")
public class WebClientController {
	
	@Autowired
	private WebClientService webClientService;
	
	@GetMapping("/1")
	public ResponseEntity<String> consumeGetApi(){
		String res=webClientService.consumeGetSApi();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/2")
	public ResponseEntity<List<ProductResponseDto>> consumeGetApiList(){
		List<ProductResponseDto> res=webClientService.consumeGetSApi2();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/3")
	public ResponseEntity<String> consumePostApi(){
		String res=webClientService.consumePostApi();
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	

}
