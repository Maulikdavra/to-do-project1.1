package com.md.springboot.todoproject.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/todos")
@SessionAttributes("name")
public class ToDoController {
	
	private ToDoService toDoService;

	@Autowired
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}
	
	/*
	 * There are two ways to get the username
	 * 1. we can get username from ModelMap as we have included @SessionAttributes("name")
	 * 2. we can use spring security code to get the username directly from login page (implemented in WelcomeController class)
	 * 
	 * Here we are using second approach to get username
	 */
	/*
	 * private String getLoggedInUsername() { Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); return
	 * authentication.getName(); }
	 */
	
	private String getLoggedInUsername(ModelMap theModel) {
		return (String) theModel.get("name");
	}

	/*
	 * In the below method we are getting username from WelcomeController class
	 * The way it works is by using the same ModelMap Mapping we used in WelcomeController
	 * So here we use ModeMap to retrieve model "name" which contains username
	 */
	@GetMapping(value="/list") 
	public String listOfAllToDos(ModelMap theModel) {
		String username = getLoggedInUsername(theModel);
		List<ToDo> todos = toDoService.findByUsername(username);
		theModel.put("todos",todos);
		return "ToDoList";
	}
	
	/*
	 * In the below method we are getting username from WelcomeController class
	 * The way it works is by using the same ModelMap Mapping we used in WelcomeController
	 * So here we use ModeMap to retrieve model "name" which contains username
	 */
	@GetMapping(value="/showToDoPage")
	public String showToDoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		ToDo todo = new ToDo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "ToDo";
	}
	
	/*
	 * Here we are using single method to update an existing ToDo as well as adding new one
	 */
	@PostMapping(value="/addingToDo")
	public String addingToDo(ModelMap model, @Valid ToDo todo, BindingResult result) {
		
		model.put("todo", todo);
		String username = getLoggedInUsername(model);
		
		if(result.hasErrors()) {
			return "ToDo";
		}
		
		int theId = todo.getId();
		
		//System.out.println("the id is" + theId);
		
		// if the user's ToDo exist then we will simply update the ToDo
		if (theId != 0) {
			toDoService.updateTodo(todo);
			return "redirect:list";
		
		// if the user's ToDo does not exist then we will simply add new ToDo
		} else {
			toDoService.addToDo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
			return "redirect:list";
		}
	}
	
	@GetMapping(value="/deletingToDo")
	public String deletingToDo(@RequestParam int id) {
		toDoService.deleteById(id);
		return "redirect:list";
	}
	
	@GetMapping(value="/updatingToDo")
	public String updatingToDo(@RequestParam int id, ModelMap model) {
		ToDo todo = toDoService.findById(id);
		model.put("todo", todo);
		return "ToDo";
	}

}
