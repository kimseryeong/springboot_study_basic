package com.todo.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.dto.ResponseDTO;
import com.todo.api.service.TodoService;

@RequestMapping("todo")
@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		
		String title = todoService.testService();
		List<String> list = new ArrayList<>();
		list.add(title);
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();		
		return ResponseEntity.ok().body(response);
	}
}
