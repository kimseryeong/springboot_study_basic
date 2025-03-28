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

	private int id;
	private String userId;
	private String uuid;
	private String title;
	private boolean done;

	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.uuid = entity.getUuid();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
	
	//dto 에서 받은 값을 이용해 TodoEntity 객체를 만들어 주는 역할
	public static TodoEntity todoEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.uuid(dto.getUuid())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
}
