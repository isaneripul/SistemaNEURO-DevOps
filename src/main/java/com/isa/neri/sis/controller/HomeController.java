package com.isa.neri.sis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class HomeController {
	
	@GetMapping(path = {"/inicio"})
	public String index(@RequestParam(name="name", required=false, defaultValue="word") String name, Model model ) {
		System.out.println("Si funciono...");
		return "home";
	}
}
