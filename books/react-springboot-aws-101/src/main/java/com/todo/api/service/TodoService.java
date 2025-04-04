package com.todo.api.service;

import java.util.List;
import java.util.Optional;

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
		TodoEntity savedEntity = todoRespository.findById(entity.getUserId()).get();
		
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
		
		log.info("Entity id : {} is saved.", entity.getId());
		log.info("Entity : {}", entity);
		
		return todoRespository.findByUserId(entity.getUserId());
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
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
		
	}

	public List<TodoEntity> retrieve(final String userId){
		log.debug("retrieve userId : {}", userId);
		
		return todoRespository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity) {

		validate(entity);
		
		//수정할 todo 가져오기
		final Optional<TodoEntity> orgnlTodo = todoRespository.findById(entity.getId());
		
		log.debug("orgnlTodo: {}", orgnlTodo);
		
		//존재한다면
		orgnlTodo.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setContent(entity.getContent());
			todo.setDone(entity.isDone());
			todo.setStartDate(entity.getStartDate());
			todo.setEndDate(entity.getEndDate());
			
			//DB에 새 값 저장
			todoRespository.save(todo);
		});
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
		validate(entity);
		
		try {
			todoRespository.delete(entity);
		}
		catch(Exception e) {
			log.error("error while deleting entity: {}", entity.getId(), e);
			
			throw new RuntimeException("error while deleting entity" + entity.getId());
		}
		
		return retrieve(entity.getUserId());
	}
}
