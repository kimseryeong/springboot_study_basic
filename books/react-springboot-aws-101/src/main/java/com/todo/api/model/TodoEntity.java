package com.todo.api.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //오브젝트 생성을 위한 디자인 패턴. 생성자를 사용해 오브젝트를 생성하는 것과 비슷
@NoArgsConstructor //매개변수가 없는 생성자 구현
@AllArgsConstructor //클래스의 모든 멤버 변수를 매개변수로 받는 생성자 구현
//@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA에서 권장
@Data //클래스 멤버 변수의 Getter/Setter 메서드를 구현
@Entity
@Table(name = "Todos") //Table 명 명시 (생략 시 엔티티 명을 테이블 명으로 간주)
public class TodoEntity {

	
	//기본키가 될 필드에 지정
	@Id @UuidGenerator private String id;
	//private String uuid;
	private String userId;
	private String email;
	private String title;
	private String content;
	private boolean done;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
