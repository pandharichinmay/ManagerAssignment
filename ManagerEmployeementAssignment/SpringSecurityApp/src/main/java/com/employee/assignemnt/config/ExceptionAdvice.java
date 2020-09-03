package com.employee.assignemnt.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.assignemnt.model.ApiResponse;

@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(RuntimeException.class)
	public ApiResponse handleNotFoundException(RuntimeException ex) {
		ApiResponse apiResponse = new ApiResponse(400, "Bad request", null);
		return apiResponse;
	}

}
