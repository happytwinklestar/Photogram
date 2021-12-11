package com.tree.sky.web;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tree.sky.domain.user.User;
import com.tree.sky.handler.ex.CustomValidationException;
import com.tree.sky.service.AuthService;
import com.tree.sky.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

	private final AuthService authService;
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
	
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		}else {
		
		User user = signupDto.toEntity();
		authService.회원가입(user);
	
		return "auth/signin";

	   }
	}
}

