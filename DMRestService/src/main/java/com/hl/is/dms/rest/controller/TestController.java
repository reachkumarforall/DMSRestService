package com.hl.is.dms.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping("/")
	public String getHelloMessage(){
		return "Hello greeting from Test Controller";
	}
}
