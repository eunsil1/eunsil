# Board 프로젝트 — 작업형 1~4 완성본

Spring Boot 4 + React(Vite) 풀스택 게시판 프로젝트입니다.

---

## 작업형별 구현 내용

| 작업형 | 내용 | 주요 파일 |
|--------|------|-----------|
| 1 | Member·Post CRUD REST API | `MemberController`, `PostController`, `PostService` |
| 2 | Spring Security 세션 로그인 + 본인 글 수정·삭제 | `SecurityConfig`, `AuthController`, `LoginUserDetails` |
| 3 | 이미지 업로드 + 썸네일 | `ImageService`, `PostImage`, `WebConfig` |
| 4 | Google OAuth2 + React SPA | `CustomOAuth2UserService`, `SecurityConfig`, `LoginPage` |

---

## 실행 방법

### 1. MySQL DB 생성
```sql
CREATE DATABASE boarddb;
```

### 2. 백엔드 실행
```bash
cd backend
./gradlew bootRun
# → http://localhost:8080
```

**OAuth2 사용 시** 환경변수 설정:
```bash
# Windows
set GOOGLE_CLIENT_ID=your-client-id
set GOOGLE_CLIENT_SECRET=your-client-secret

# Mac/Linux
export GOOGLE_CLIENT_ID=your-client-id
export GOOGLE_CLIENT_SECRET=your-client-secret
```

### 3. 프론트엔드 실행
```bash
cd frontend
npm install
npm run dev
# → http://localhost:5173
```

---

## 프로젝트 구조

### Backend
```
src/main/java/com/example/workshop1/
├── config/
│   ├── SecurityConfig.java      ← 인증·인가, OAuth2 설정
│   └── WebConfig.java           ← CORS, 정적 파일(/uploads/**)
├── controller/
│   ├── AuthController.java      ← /api/auth/login·me·logout
│   ├── MemberController.java    ← /api/members
│   └── PostController.java      ← /api/posts (multipart 지원)
├── entity/
│   ├── BaseTimeEntity.java      ← createdAt / updatedAt
│   ├── Member.java              ← 폼 로그인 + OAuth2 필드
│   ├── Post.java
│   └── PostImage.java           ← 이미지 첨부 (작업형 3)
├── service/
│   ├── MemberService.java
│   ├── PostService.java
│   ├── ImageService.java        ← 업로드 + 썸네일
│   ├── CustomUserDetailsService.java
│   └── CustomOAuth2UserService.java ← Google OIDC
├── security/
│   └── LoginUserDetails.java
├── dto/
│   ├── MemberDto.java
│   └── PostDto.java             ← 이미지 URL 포함
├── exception/                   ← 404 / 409 커스텀 예외
└── global/
    └── GlobalExceptionHandler.java ← @RestControllerAdvice
```

### Frontend
```
frontend/src/
├── api/axios.js          ← withCredentials: true
├── hooks/useAuth.jsx     ← 전역 로그인 상태
├── components/Navbar.jsx
└── pages/
    ├── LoginPage.jsx     ← 폼 로그인 + Google 버튼
    ├── RegisterPage.jsx
    ├── PostListPage.jsx  ← 페이징
    ├── PostDetailPage.jsx ← 이미지 표시, 본인 글 수정·삭제
    └── PostFormPage.jsx  ← 글쓰기·수정 + 이미지 업로드
```

---

## API 명세

### 인증
| 메서드 | 경로 | 설명 | 인증 |
|--------|------|------|------|
| POST | `/api/members` | 회원가입 | 불필요 |
| POST | `/api/auth/login` | 로그인 | 불필요 |
| GET | `/api/auth/me` | 내 정보 | 필요 |
| POST | `/api/auth/logout` | 로그아웃 | 불필요 |
| GET | `/oauth2/authorization/google` | 구글 로그인 | 불필요 |

### 게시글
| 메서드 | 경로 | 설명 | 인증 |
|--------|------|------|------|
| GET | `/api/posts?page=0&size=10` | 목록 (페이징) | 불필요 |
| GET | `/api/posts/{id}` | 상세 | 불필요 |
| POST | `/api/posts` | 등록 (multipart) | 필요 |
| PUT | `/api/posts/{id}` | 수정 (본인만) | 필요 |
| DELETE | `/api/posts/{id}` | 삭제 (본인만) | 필요 |

### 글 등록 요청 예시 (Postman)
```
POST /api/posts
Content-Type: multipart/form-data

title: 제목
content: 내용
files: (이미지 파일, 선택)
```

---

## HTTP 상태 코드

| 상황 | 코드 |
|------|------|
| 비로그인 글쓰기 | 401 |
| 타인 글 수정·삭제 | 403 |
| 없는 id 조회 | 404 |
| username 중복 가입 | 409 |
| 허용 안 된 확장자·용량 초과 | 400 |

---

## OAuth2 Google 설정 (작업형 4)

1. [Google Cloud Console](https://console.cloud.google.com) 접속
2. **사용자 인증 정보** → OAuth 2.0 클라이언트 ID 생성
3. 승인된 리다이렉션 URI 추가:
   ```
   http://localhost:8080/login/oauth2/code/google
   ```
4. 환경변수로 `GOOGLE_CLIENT_ID`, `GOOGLE_CLIENT_SECRET` 설정
5. 로그인 성공 후 `http://localhost:5173/posts`로 리다이렉트됨
