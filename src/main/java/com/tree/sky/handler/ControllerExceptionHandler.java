package com.tree.sky.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.tree.sky.handler.ex.CustomApiException;
import com.tree.sky.handler.ex.CustomException;
import com.tree.sky.handler.ex.CustomValidationApiException;
import com.tree.sky.handler.ex.CustomValidationException;
import com.tree.sky.util.Script;
import com.tree.sky.web.dto.CMRespDto;


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
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage());
	}

}
