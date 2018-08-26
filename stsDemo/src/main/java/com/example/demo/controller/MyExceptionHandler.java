package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.UserNotExistException;

/**
 * 异常处理器
 * @author 汪进银
 *@ControllerAdvice 在springBoot表示定制异常处理器
 */
@ControllerAdvice
public class MyExceptionHandler {
  
//	@ResponseBody  //返回json数据
//	@ExceptionHandler(UserNotExistException.class)
//	 public Map<String, Object> handleException(Exception e) {
//		Map<String, Object> map = new HashMap<>() ;
//		map.put("code","userNotExist");
//		map.put("message",e.getMessage());
//		return map;
//	 }
	
	
	/**
	 * 转发到/error进行自适应响应效果处理
	 * @param e
	 * @param request
	 * @return
	 */
	 @ExceptionHandler(UserNotExistException.class)
	 public String handleException(Exception e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>() ;
		
		//传入自己的错误状态码4xx或5xx 不然就会默认错误状态码为200
		request.setAttribute("javax.servlet.error.status_code",500);
		
		map.put("code","userNotExist");
		map.put("message", "用户出错啦");
		
		request.setAttribute("ext", map);
		return "forward:/error";
	 }
}
