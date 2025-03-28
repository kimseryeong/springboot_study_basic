package com.todo.api.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.api.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{

	//Select * from TodoRepository where userId = userId;
	List<TodoEntity> findByUserId(String uuid);
	
	//복잡한 쿼리 사용법
	//@Query("select * from Todo t where t.userId = ?1")
	//List<TodoEntity> findByUserIdQuery(String userId);
	
}
