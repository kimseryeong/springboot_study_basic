package com.todo.api.dto;

import java.time.LocalDateTime;

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

	private String id;
	private String userId;
	private String title;
	private boolean done;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
	}
	
	//dto 에서 받은 값을 이용해 TodoEntity 객체를 만들어 주는 역할
	public static TodoEntity todoEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.userId(dto.getUserId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.startDate(dto.getStartDate())
				.endDate(dto.getEndDate())
				.build();
	}
}
