package com.todo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.api.model.TodoEntity;
import com.todo.api.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //스테레오 타입 어노테이션 - 비즈니스 로직을 수행할 Service 파일임을 명시
public class TodoService {

	@Autowired
	private TodoRepository todoRespository;
	
	public String getTodoTitle() {
		
		
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		
		//TodoEntity 저장
		todoRespository.save(entity);
		
		//TodoEntity검색
		TodoEntity savedEntity = todoRespository.findById(entity.getUuid()).get();
		
		return savedEntity.getTitle();
	}
	
	/**
	 * create Todo 
	 * @param entity
	 * @return
	 */
	public List<TodoEntity> create(final TodoEntity entity){
		validate(entity);
		
		todoRespository.save(entity);
		
		log.info("Entity id : {} is saved.", entity.getUuid());
		
		return todoRespository.findByUserId(entity.getUuid());
	}
	
	/**
	 * Validation method
	 * @param entity
	 */
	public void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUuid() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
		
	}
}
