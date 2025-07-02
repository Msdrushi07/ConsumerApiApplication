package com.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.service.RestTemplateService;

@RestController
@RequestMapping("/consume")
public class RestTemplateController {
	
	@Autowired
	private RestTemplateService consumerService;
	
	@GetMapping("/1")
	public ResponseEntity<String> consumingGetMethod(){
		String res=consumerService.consumeApi();
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@GetMapping("/2")
	public ResponseEntity<String> consumingGetMethod2(){
		String res=consumerService.consumeApi2();
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<String> consumingPostMethod(){
		String res=consumerService.consumeApiPost();
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}

}
