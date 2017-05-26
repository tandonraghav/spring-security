package com.spring.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestRestController {
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public String create(){
		return "Spring is Woring !!!";
	}
}
