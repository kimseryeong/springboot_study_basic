# 🛠 [Lambda expression's parameter redeclare]

### 🤬 error

⚠️ <mark>Lambda expression's parameter entity cannot redeclare another local variable defined in an enclosing scope.</mark>

[ 에러가 발생한 코드 ]
```
List<TodoDTO> dtos = entities.stream()
					.map(entity -> new TodoDTO(entity))
					.collect(Collectors.toList());
```


<br>

### 🔍 cause

람다식의 매개변수인 entity 가 이미 바깥에서 선언된 entity 와 중복되었음


<br>

### ✅ solution

스코프 내의 entity 변수명 변경하기
```
List<TodoDTO> dtos = entities.stream()
					.map(e -> new TodoDTO(e))
					.collect(Collectors.toList());
```