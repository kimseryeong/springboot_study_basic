package com.todo.api;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ApiModel {

	@NonNull
	private String id;
}
