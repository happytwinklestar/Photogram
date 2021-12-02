package com.tree.sky;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {

	
	@GetMapping("/")
		public String start() {
		return "auth/signin";
	}
}
