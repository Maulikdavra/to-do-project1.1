package com.md.springboot.todoproject.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Service
public class ToDoService {

	private static List<ToDo> todos = new ArrayList();
	
	private static int todoCount = 0;
	
	static {
		todos.add(new ToDo(++todoCount, "Md06", "spring-boot", LocalDate.now().plusYears(1), false));
		todos.add(new ToDo(++todoCount, "Md06", "full-stack", LocalDate.now().plusYears(1), false));
		todos.add(new ToDo(++todoCount, "Md06", "AWS", LocalDate.now().plusYears(2), false));	
		todos.add(new ToDo(++todoCount, "Md07", "React", LocalDate.now().plusMonths(11), false));
		todos.add(new ToDo(++todoCount, "Md07", "Angular", LocalDate.now().plusMonths(14), false));	
		todos.add(new ToDo(++todoCount, "Md08", "Java-script", LocalDate.now().plusMonths(3), false));	
		todos.add(new ToDo(++todoCount, "Md08", "Python", LocalDate.now().plusMonths(10), false));	
		todos.add(new ToDo(++todoCount, "Md08", "Data Science", LocalDate.now().plusMonths(11), false));	


	}
	
	/*
	 * Below method basically compares the username coming from ToDoController and the username defined in ToDoService
	 */
	public List<ToDo> findByUsername(String username) {
		Predicate<? super ToDo> predicate = toDo -> toDo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}

	public void addToDo(String username, String description, LocalDate targetDate, boolean done) {
		ToDo todo1 = new ToDo(++todoCount, username, description, targetDate, done);
		todos.add(todo1);
	}
	
	public void deleteById(int id) {
		
		// if the todo.get() == id 
		// todo -> todo.get() == id  (todo is bean)
		
		Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public ToDo findById(int id) {
		Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
		ToDo toDo = todos.stream()
			 .filter(predicate).findFirst().get();
		return toDo;
	}

	/*
	 * The reason for first deleting the existing ToDo and adding a new ToDo
	 * is that we don't want spring to create a new ToDo will updating an existing one
	 */
	public void updateTodo(@Valid ToDo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
