package com.todo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.dto.RequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {

	@GetMapping
	public String test() {
		return "Hello This is GetMapping without any parameter";
	}
	
	@GetMapping("/{email:.+@.+\\\\..+}")
	public String testWithPathVariable(@PathVariable("email") String email) {
		return "Hello This is Test testWithPathVariable, Welcome: " + email;
	}
	
	@GetMapping("/reqParam")
	public String testWithRequestParam(@RequestParam(name = "email", required = false) String email) {
		return "Hello This is Test testWithRequestParam, Welcome: " + email;
	}

	@GetMapping("/testRequestBody")
	public String testRequestBody(@RequestBody RequestBodyDTO requestBodyDTO) {
		return "Hello email: " + requestBodyDTO.getEmail() + ", Message: " + requestBodyDTO.getMessage();
	}
	
}
