package com.paul.bs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class Login {

	
	@RequestMapping(value="/login.shtml")
	public String Login(){
		
		return "login";
	}
}
