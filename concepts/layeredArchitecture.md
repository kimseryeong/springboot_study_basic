# Layered Architecture 레이어드 아키텍처

```
💡 Layered Architecture 란?

- 소프트웨어 시스템을 관심사 별 여러 개의 계층으로 분리한 아키텍처
```

### 📌 관심사 분리하는 이유?

> 🔹한 계층에 여러 관심사가 존재
> → 해당 계층의 응집도 ↓, 결합도 ↑

> 🔹각 계층을 관심사 기준으로 분리
> → 해당 계층의 응집도 ↑, 결합도 ↓

→ 재사용성 & 유지보수성 ↑

> 요청은 **상위 -> 하위** (하위 계층은 상위 계층에 대한 정보가 없어야 함)

---

![Alt text](/image/layeredArchitecture.png)

**✔️ Presentation Layer**

사용자 혹은 클라이언트 시스템과 직접적으로 연결된 부분.
사용자 요청을 처리.

이 외의 비즈니스 로직은 이 계층의 관심사 X

→ `UI` `Controller`

**✔️ Business Layer**

비즈니스 로직을 구현하는 부분. 시스템이 구현해야 하는 핵심 로직을 담당.

Presentation Layer로 부터 사용자의 요청을 전달받고 해당 요청을 실질적으로 처리하는 부분.

→ `Service` `Business Logic`

**✔️ Persistence Layer**

데이터의 영구 저장과 관리를 담당하는 부분.

웹 애플리케이션의 데이터베이스와의 상호작용을 처리하며 데이터베이스와의 상호작용을 추상화.

→ `Repository`

**✔️ Database Layer**

실제 데이터베이스를 의미

→ `DB`
