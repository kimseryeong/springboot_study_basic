package com.todo.api.dto;

import com.todo.api.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

	private String email;
	private String title;
	private boolean done;

	public TodoDTO(final TodoEntity entity) {
		this.email = entity.getEmail();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
}
