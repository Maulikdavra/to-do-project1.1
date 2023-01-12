package com.md.springboot.todoproject.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/*
 * @Author: Maulik Davra
 * 
 * Here we will be adding URl that will direct user to login.jsp page
 * 
 * If we want to pass any information from our controller to JSP pages, 
 * the way we can do is by including a "Model" in the method.
 * Later in the method we will have to add those @RequestParam,  
 * path variable to the model via addAttribute("modelName", "parameter);
 */

@Controller
@SessionAttributes("name")
public class WelcomeController {
	

	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@GetMapping("/")
	public String gotToWelcomePageRP(ModelMap theModel) {

		theModel.put("name", getLoggedinUsername());

		return "Welcome";
	} 
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
