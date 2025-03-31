package com.todo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.dto.ResponseDTO;
import com.todo.api.dto.TodoDTO;
import com.todo.api.model.TodoEntity;
import com.todo.api.service.TodoService;

@RequestMapping("todo")
@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		
		String title = todoService.getTodoTitle();
		List<String> list = new ArrayList<>();
		list.add(title);
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();		
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * Todo 생성
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		//@RequestBody: 클라이언트가 JSON 형태로 보낸 데이터를 TodoDTO 객체로 변환해서 받음
		
		try {
			
			//TodoDTO를 TodoEntity 객체로 변환
			//DTO(Data Transfer Object): 클라이언트와 데이터 주고받을 때 사용
			//Entity: DB에 저장될 데이터 객체
			TodoEntity entity = TodoDTO.todoEntity(dto);
			
			//임시 userId 생성 (로그인 기능이 아직 없기 때문)
			String tempUserId = "tempUserId";
			entity.setUserId(tempUserId);

			//id를 null로 초기화. 생성 당시는 id가 없기 때문
			entity.setId(0);
			
			//서비스를 통해 Todo 엔티티 생성
			//변환한 데이터 DB 저장
			//새로운 할 일이 저장되면서 여러 개의 TodoEntity를 반환
			List<TodoEntity> entities = todoService.create(entity);
			
			//Entity를 DTO로 변환
			//1. ".stream().map(e -> new TodoDTO(e))": entity list stream()
			//	 각 TodoEntity 객체를 TodoDTO 생성자를 이용해 변환
			//2. ".collect(Collectors.toList())": 변환된 데이터를 리스트로 모음
			List<TodoDTO> dtos = entities.stream()
					.map(e -> new TodoDTO(e))
					.collect(Collectors.toList());
			
			//응답 데이터 형식을 통일하기 위한 DTO 클래스
			//".builder()": ResponseDTO를 쉽게 만들 수 있도록 도와주는 빌더 패턴
			//빌더 패턴(@Builder): 객체를 보다 쉽게 생성할 수 있도록 도와주는 패턴 (체이닝 방식으로 객체 생성)
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
				    .data(dtos)
				    .build();

			//성공 응답 반환 (200)
			return ResponseEntity.ok().body(response);
			
		}
		catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();

			
			//HTTP 상태 코드 400 반환
			return ResponseEntity.badRequest().body(response);
		}
	}
}
