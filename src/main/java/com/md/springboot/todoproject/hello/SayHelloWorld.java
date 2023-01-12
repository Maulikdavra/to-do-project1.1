package com.md.springboot.todoproject.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @Author Maulik Davra
 * 
 * Here we are creating a URL that will return a String 
 * URL -> "say-hello" -> "Hello! hope you are having fun"
 * 
 * FYI: @ResponseBody will return whatever methods have in "return" statement
 * 		Do not include @ResponseBody while dealing with jsp pages
 */
@RequestMapping("/say-hello")
@Controller
public class SayHelloWorld {
	
	@GetMapping("/hi")
	@ResponseBody 
	public String sayHello() {
		return "Hello! hope you are having fun";
	}
	
	@GetMapping("/hi-html")
	public String sayHelloHtml() {
		return "hello";
	}

}
