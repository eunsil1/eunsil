import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../hooks/useAuth'

export default function LoginPage() {
  const [form, setForm]       = useState({ username: '', password: '' })
  const [error, setError]     = useState('')
  const [loading, setLoading] = useState(false)
  const { login } = useAuth()
  const navigate  = useNavigate()

  const handleSubmit = async e => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      await login(form.username, form.password)
      navigate('/')
    } catch (err) {
      setError(err.response?.data?.message || '로그인 실패')
    } finally {
      setLoading(false)
    }
  }

  const BASE = 'http://localhost:8087'

  return (
    <div style={{ maxWidth:400, margin:'100px auto', padding:24 }}>
      <h2>로그인</h2>
      {error && <p style={{ color:'red' }}>{error}</p>}

      {/* 폼 로그인 */}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom:12 }}>
          <input placeholder="아이디" value={form.username}
                 onChange={e => setForm(p => ({...p, username: e.target.value}))}
                 style={{ width:'100%', padding:8 }} required />
        </div>
        <div style={{ marginBottom:12 }}>
          <input type="password" placeholder="비밀번호" value={form.password}
                 onChange={e => setForm(p => ({...p, password: e.target.value}))}
                 style={{ width:'100%', padding:8 }} required />
        </div>
        <button type="submit" disabled={loading}
                style={{ width:'100%', padding:10, background:'#4f46e5',
                         color:'#fff', border:'none', borderRadius:6,
                         cursor:'pointer', fontSize:15 }}>
          {loading ? '로그인 중...' : '로그인'}
        </button>
      </form>

      {/* 구분선 */}
      <div style={{ display:'flex', alignItems:'center', margin:'20px 0', gap:10 }}>
        <hr style={{ flex:1, borderColor:'#eee' }} />
        <span style={{ color:'#aaa', fontSize:13 }}>소셜 로그인</span>
        <hr style={{ flex:1, borderColor:'#eee' }} />
      </div>

      {/* Google */}
      <a href={`${BASE}/oauth2/authorization/google`}
         style={socialBtn('#fff', '#ddd', '#333')}>
        <svg width="18" height="18" viewBox="0 0 48 48">
          <path fill="#4285F4" d="M45.12 24.5c0-1.56-.14-3.06-.4-4.5H24v8.51h11.84c-.51 2.75-2.06 5.08-4.39 6.64v5.52h7.11c4.16-3.83 6.56-9.47 6.56-16.17z"/>
          <path fill="#34A853" d="M24 46c5.94 0 10.92-1.97 14.56-5.33l-7.11-5.52c-1.97 1.32-4.49 2.1-7.45 2.1-5.73 0-10.58-3.87-12.31-9.07H4.34v5.7C7.96 41.07 15.4 46 24 46z"/>
          <path fill="#FBBC05" d="M11.69 28.18C11.25 26.86 11 25.45 11 24s.25-2.86.69-4.18v-5.7H4.34C2.85 17.09 2 20.45 2 24c0 3.55.85 6.91 2.34 9.88l7.35-5.7z"/>
          <path fill="#EA4335" d="M24 10.75c3.23 0 6.13 1.11 8.41 3.29l6.31-6.31C34.91 4.18 29.93 2 24 2 15.4 2 7.96 6.93 4.34 14.12l7.35 5.7c1.73-5.2 6.58-9.07 12.31-9.07z"/>
        </svg>
        Google로 로그인
      </a>

      {/* 네이버 */}
      <a href={`${BASE}/oauth2/authorization/naver`}
         style={{ ...socialBtn('#03c75a', '#03c75a', '#fff'), marginTop:10 }}>
        <span style={{ fontWeight:'bold', fontSize:16, lineHeight:1 }}>N</span>
        네이버로 로그인
      </a>

      {/* 카카오 */}
      <a href={`${BASE}/oauth2/authorization/kakao`}
         style={{ ...socialBtn('#fee500', '#fee500', '#191919'), marginTop:10 }}>
        <svg width="18" height="18" viewBox="0 0 24 24" fill="#191919">
          <path d="M12 3C6.477 3 2 6.477 2 10.5c0 2.611 1.564 4.904 3.926 6.264L5 21l4.326-2.87C10.17 18.37 11.07 18.5 12 18.5c5.523 0 10-3.477 10-7.75S17.523 3 12 3z"/>
        </svg>
        카카오로 로그인
      </a>

      <p style={{ marginTop:20, textAlign:'center' }}>
        계정이 없으신가요? <Link to="/register">회원가입</Link>
      </p>
    </div>
  )
}

// 소셜 버튼 공통 스타일
function socialBtn(bg, border, color) {
  return {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 10,
    padding: '10px 0',
    background: bg,
    border: `1px solid ${border}`,
    borderRadius: 6,
    textDecoration: 'none',
    color: color,
    fontSize: 14,
    cursor: 'pointer',
    width: '100%',
  }
}