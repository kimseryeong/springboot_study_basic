# Java Stream API(Java 8 이상)

```
💡 Java Stream 란?
- 데이터를 처리하는 새로운 방식, 컬렉션이나 배열을 함수형 스타일로 처리할 수 있는 API
- 컬렉션에 저장되어 있는 엘리먼트들을 하나씩 순회하면서 처리할 수 있는 코드패턴
- 람다식과 함께 사용되어 매우 간결한 표현으로 작성할 수 있음
```

### 📌 Stream API 특징

1. 원본의 데이터를 변경하지 않음
2. 일회성 (재사용 X)
3. 내부 반복으로 작업을 처리함

### 📌 Stream 사용법

#### 1️⃣Stream 생성
- **컬렉션**  `List`, `Set`, `Map`, `Array` → `stream()` 메서드 지원

    ```
    //List에서 Stream 생성

    List<String> list = Arrays.asList("a", "b", "c");
    Stream<String> stream = list.stream();
    ```

    ```
    // 값을 가지는 Stream 생성
    Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
    ```

    <br>    
    
- **배열** → `Arrays.stream()`
    ```
    // 배열을 사용하여 Stream 생성

    String[] words = new String[]{"a", "b", "c"};
    Stream<String> stream1 = Arrays.stream(words);
    Stream<String> wordStream = Stream.of(words);

    int[] arr = {1, 2, 3, 4, 5};
    IntStream intStream = Arrays.stream(arr); 
    // 기본형 배열에 적합 (IntStream, LongStream, DoubleStream)
    ```

    <br>    
    
- **빌더** → `builder()`
    ```
    //동적으로 요소 추가하는 데 유용

    Stream<String> stream = Stream.<String>builder()
                          .add("Apple")
                          .add("Banana")
                          .build();
    ```

#### 2️⃣ Stream 가공
- 생성 후 데이터 가공할 수 있는 여러 중간 연산 적용
- 중간 연산은 새로운 Stream 으로 반환하고 **체이닝**하여 여러 연산을 동시에 할 수 있게 함

    |중간 연산|역할|
    |--------|---|
    |`filter()`|조건에 맞는 요소만 필터링|
    |`map()`|각 요소를 변환|
    |`distinct()`|중복 제거|
    |`sorted()`|정렬|

    ```
    //예시

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    numbers.stream()
        .filter(n -> n % 2 == 0)        // 짝수만 필터링
        .map(n -> n * 2)                // 각 값을 2배로 변환
        .forEach(System.out::println);  // 출력: 4, 8
    ```

#### 3️⃣ Stream 결과 만들기
- Stream을 소모하고 결과를 만들어내는 연산
- 최종 연산이 실행되어야 중간 연산들이 실제로 실행됨 
- 이 후 Stream은 재사용 X

    |최종 연산|역할|
    |--------|---|
    |`collect()`|결과를 컬렉션으로 변환|
    |`forEach()`|각 요소에 대해 작업 실행|
    |`reduce()`|요소들을 결합하여 하나의 값으로 만듦|
    |`count()`|요소 개수 세기|

    ```
    //예시
    
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    numbers.stream()
        .filter(n -> n % 2 == 0)        // 짝수만 필터링
        .map(n -> n * 2)                // 각 값을 2배로 변환
        .collect(Collectors.toList());  // 리스트로 변환
    ```