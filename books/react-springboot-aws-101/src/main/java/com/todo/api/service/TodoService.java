package com.todo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.api.model.TodoEntity;
import com.todo.api.persistence.TodoRepository;

@Service //스테레오 타입 어노테이션 - 비즈니스 로직을 수행할 Service 파일임을 명시
public class TodoService {

	@Autowired
	private TodoRepository todoRespository;
	
	public String testService() {
		
		
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		
		//TodoEntity 저장
		todoRespository.save(entity);
		
		//TodoEntity검색
		TodoEntity savedEntity = todoRespository.findById(entity.getUuid()).get();
		
		return savedEntity.getTitle();
	}
}
