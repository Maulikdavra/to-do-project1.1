package com.md.springboot.todoproject.todo;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

public class ToDo {

	private int id;
	private String username;
	
	@Size(min=5, message="Enter atleast five characters")
	private String description;
	private LocalDate targetDate;
	private boolean Done;

	public ToDo(int id, String username, String description, LocalDate targetDate, boolean done) {
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		Done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return Done;
	}

	public void setDone(boolean done) {
		Done = done;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", Done=" + Done + "]";
	}

}
