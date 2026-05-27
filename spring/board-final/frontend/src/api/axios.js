import axios from 'axios'

// 세션 쿠키를 자동으로 포함시켜 Spring Boot 세션 인증에 사용
const api = axios.create({
  baseURL: '/api',
  withCredentials: true,          // JSESSIONID 쿠키 자동 전송
  headers: { 'Content-Type': 'application/json' },
})

export default api
