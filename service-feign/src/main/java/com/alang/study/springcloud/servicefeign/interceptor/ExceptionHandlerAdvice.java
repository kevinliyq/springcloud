package com.alang.study.springcloud.servicefeign.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
	private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex){
		logger.error("",ex);
		return "There is exception:"+ ex.getMessage();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIOException(IllegalArgumentException ex){
		logger.error("",ex);
		return "There is exception:"+ ex.getMessage();
	}
}
