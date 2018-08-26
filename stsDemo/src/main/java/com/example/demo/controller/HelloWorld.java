package com.example.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.exception.UserNotExistException;

@Controller
public class HelloWorld {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello(@RequestParam("user") String user) {
		if(user.equals("aaa")) {
			throw new UserNotExistException();
		}
		return "hello";
	}
	
	@GetMapping("/atguigu")
	public String success(Map<String,  Object> map) {
		
		map.put("hello", "你好");
		return "success";
	}
	
}
