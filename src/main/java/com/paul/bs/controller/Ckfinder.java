package com.paul.bs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ckf")
public class Ckfinder {
	
	@RequestMapping
	public String ckfinder(){
		return "ckfinder";
	}
}
