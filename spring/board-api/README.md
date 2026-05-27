# Board REST API

Spring Boot 3 + JPA + H2 로 구현한 회원·게시판 CRUD REST API입니다.

---

## 기술 스택

| 항목 | 버전 |
|------|------|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring Data JPA | 3.2.5 |
| H2 (인메모리) | Runtime |
| Validation | Jakarta |
| Lombok | Optional |

---

## 프로젝트 구조

```
src/main/java/com/example/board/
├── BoardApplication.java          ← @EnableJpaAuditing
├── entity/
│   ├── BaseTimeEntity.java        ← createdAt / updatedAt 자동 관리
│   ├── Member.java
│   └── Post.java                  ← @ManyToOne(member)
├── repository/
│   ├── MemberRepository.java
│   └── PostRepository.java        ← FETCH JOIN 쿼리 (N+1 방지)
├── service/
│   ├── MemberService.java
│   └── PostService.java
├── controller/
│   ├── MemberController.java      ← POST /api/members, GET /api/members/{id}
│   └── PostController.java        ← /api/posts CRUD
├── dto/
│   ├── MemberDto.java             ← RegisterRequest / Response
│   └── PostDto.java               ← CreateRequest / UpdateRequest / Response / SummaryResponse
├── exception/
│   ├── ResourceNotFoundException.java
│   └── DuplicateUsernameException.java
└── global/
    └── GlobalExceptionHandler.java   ← @RestControllerAdvice
```

---

## 실행

```bash
./mvnw spring-boot:run
# 또는
mvn spring-boot:run
```

H2 콘솔: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:boarddb`
- User: `sa` / Password: (빈 칸)

---

## API 명세

### 회원

| 메서드 | 경로 | 설명 | 상태 코드 |
|--------|------|------|-----------|
| POST | `/api/members` | 회원가입 | 201 Created |
| GET | `/api/members/{id}` | 회원 조회 | 200 OK / 404 |

**회원가입 요청 예시**
```json
POST /api/members
{
  "username": "alice",
  "password": "1234",
  "nickname": "앨리스"
}
```

**응답**
```json
{
  "id": 1,
  "username": "alice",
  "nickname": "앨리스",
  "createdAt": "2024-01-01T12:00:00"
}
```

---

### 게시글

| 메서드 | 경로 | 설명 | 상태 코드 |
|--------|------|------|-----------|
| GET | `/api/posts` | 글 목록 (페이징) | 200 OK |
| GET | `/api/posts/{id}` | 글 상세 | 200 OK / 404 |
| POST | `/api/posts` | 글 등록 | 201 Created |
| PUT | `/api/posts/{id}` | 글 수정 | 200 OK / 404 |
| DELETE | `/api/posts/{id}` | 글 삭제 | 204 No Content / 404 |

**페이징 쿼리 파라미터**
```
GET /api/posts?page=0&size=10&sort=id,desc
```

**글 등록 요청**
```json
POST /api/posts
{
  "title": "첫 번째 글",
  "content": "내용입니다.",
  "memberId": 1
}
```

**글 수정 요청**
```json
PUT /api/posts/1
{
  "title": "수정된 제목",
  "content": "수정된 내용"
}
```

---

## 오류 응답 형식

**404 Not Found**
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "게시글을 찾을 수 없습니다. id=999",
  "timestamp": "2024-01-01T12:00:00"
}
```

**400 Bad Request (@Valid 실패)**
```json
{
  "status": 400,
  "error": "Bad Request",
  "fieldErrors": {
    "title": "제목은 필수입니다."
  },
  "timestamp": "2024-01-01T12:00:00"
}
```

**409 Conflict (중복 username)**
```json
{
  "status": 409,
  "error": "Conflict",
  "message": "이미 사용 중인 아이디입니다: alice",
  "timestamp": "2024-01-01T12:00:00"
}
```

---

## curl 테스트

```bash
chmod +x test-api.sh
bash test-api.sh
```

---

## MySQL 전환

`application.properties` 에서 H2 설정을 아래로 교체:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/boarddb?serverTimezone=Asia/Seoul
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

`pom.xml` 에 MySQL 의존성 추가:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```
