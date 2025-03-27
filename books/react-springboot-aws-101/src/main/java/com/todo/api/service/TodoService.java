package com.todo.api.service;

import org.springframework.stereotype.Service;

@Service //스테레오 타입 어노테이션 - 비즈니스 로직을 수행할 Service 파일임을 명시
public class TodoService {

	public String testService() {
		return "TEST SERVICE";
	}
}
