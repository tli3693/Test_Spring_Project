package com.troy.spring.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	String s = "/world";
	@RequestMapping(value = "/world")
	public String welcome1(Model model) {
		
		model.addAttribute("welcomeMessage", "Welcome Spring Message!");
		return "world";
	}
	
	
}
