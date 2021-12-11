package com.tree.sky.handler;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.tree.sky.handler.ex.CustomValidationException;
import com.tree.sky.util.Script;


@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	
	
	

	
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}
		
	}
	
	
	
	
}
