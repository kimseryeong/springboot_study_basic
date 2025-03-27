# Gradle - Groovy

```
💡 Gradle 란?
    프로젝트의 빌드, 테스트, 배포 과정을 자동화해주는 도구
```

### 📌 Gradle 특징

1. 빠른 속도 → Incremental Build(변경된 부분만 빌드) 덕분에 Maven보다 빠름
2. DSL 기반의 간결한 문법 → Groovy 또는 Kotlin DSL 사용
3. 플러그인 시스템 → 다양한 기능을 쉽게 추가 가능
4. 의존성 관리 편리 → Maven Central, JCenter 같은 저장소에서 라이브러리 관리 가능

### 📌 Gradle 프로젝트 구조

📂 my-spring-boot-app <br>
├── 📂 src # 소스 코드 (Java/Kotlin) <br>
├── 📂 build # 빌드된 결과 (Gradle이 자동 생성) <br>
├── 📂 gradle # Gradle 관련 파일 <br>
├── 📄 build.gradle # 빌드 스크립트 (Groovy DSL) <br>
├── 📄 settings.gradle # 프로젝트 설정 <br>
├── 📄 gradlew # Gradle Wrapper (리눅스/맥) <br>
├── 📄 gradlew.bat # Gradle Wrapper (윈도우) <br>

### 📌 build.gradle (Gradle 설정 파일)

```
plugins {
    id 'org.springframework.boot' version '3.2.2'   // 스프링 부트 플러그인
    id 'io.spring.dependency-management' version '1.1.4'  // 의존성 자동 관리
    id 'java'  // Java 프로젝트 사용
}

//프로젝트의 메타데이터
group = 'com.example'  // 그룹 ID (패키지 네임과 비슷한 개념)
version = '1.0.0'  // 프로젝트 버전

java {
    sourceCompatibility = '17'  // Java 버전 설정
}

//의존성이 사용되는 scope를 configuration을 통해 명시
//(의존성 종류에 따라 컴파일, 런타임 등 해당 의존성이 사용되어지는 특정 scope 가 있음)
configuration {
	compileOnly {
		extendsFrom annotationProcessor //컴파일 시 사용할 어노테이션 프로세서 명시
	}
}

//gradle이 라이브러리를 다운받는 곳 지정
// 라이브러리 저장소 (Maven Central)
repositories {
    mavenCentral()
}

//dependencies 정의
//lombok: 어노테이션 기반으로 코드를 자동완성 해주는 라이브러리 (개발 시간 단축을 위해 사용)
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'  // 웹 관련 기본 라이브러리
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'  // JPA 관련 라이브러리
    runtimeOnly 'org.postgresql:postgresql'  // PostgreSQL 드라이버
    testImplementation 'org.springframework.boot:spring-boot-starter-test'  // 테스트 라이브러리
    annotationProcessor 'org.projectlombok:lombok' //어노테이션 프로세서로 롬복 사용 명시


    ... add dependencies
}

//빌드 뿐 아니라 유닛 테스트를 실행시킬 수 있음
tasks.named('test') {
    useJUnitPlatform()  // JUnit5 사용
}

```

### 📌 Gradle 주요 명령어

| 명령어                 | 설명                            |
| ---------------------- | ------------------------------- |
| ./gradlew build        | 프로젝트 빌드                   |
| ./gradlew clean        | 빌드 파일 삭제 (초기화)         |
| ./gradlew bootRun      | 애플리케이션 실행 (Spring Boot) |
| ./gradlew test         | 테스트 실행                     |
| ./gradlew dependencies | 프로젝트 의존성 확인            |

> 윈도우에서는 ./gradlew 대신 gradlew.bat 사용

### 📌 Gradle vs Maven

| 비교 항목   | Gradle                   | Maven         |
| ----------- | ------------------------ | ------------- |
| 속도        | 빠름 (Incremental Build) | 느림          |
| DSL 문법    | Groovy / Kotlin          | XML           |
| 유연성      | 높음                     | 낮음          |
| 의존성 관리 | 강력한 캐싱              | 정형화된 관리 |
