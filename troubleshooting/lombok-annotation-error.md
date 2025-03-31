# 🛠 [Lombok Annotation 인식 Error]

### 🤬 error

⚠️ <mark>The method builder() is undefined for the type ResponseDTO</mark>

[ 에러가 발생한 코드 ]
```
ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos)
                    .build();
```

<br>

### 🔍 cause

github 에서 프로젝트 clone 후 lombok 설치 및 세팅 누락으로 
<br> lombok 이 제공하는 몇몇 어노테이션을 인식하지 못해 발생한 오류
ex) @Builder ...


<br>

### ✅ solution

lombok 설치 및 세팅

1. maven repository 사이트에서 lombok.jar 파일 다운로드
2. lombok.jar 파일 적절한 경로로 이동
3. cmd 창에서 lombok.jar 파일의 경로로 이동 후 아래 명령어 입력
    ```
    java -jar lombok-1.18.36.jar
    ```
4. lombok 설치
5. gradle clean
6. project clean
