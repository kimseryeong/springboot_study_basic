package com.todo.api.dto;

import lombok.Data;

@Data
public class RequestBodyDTO {
	private String email;
	private String message;
}
