import { useNavigate } from 'react-router-dom'
import { useAuth } from '../hooks/useAuth'

export default function Navbar() {
  const { user, loading, logout } = useAuth()
  const navigate = useNavigate()

  const handleLogout = async () => {
    await logout()
    navigate('/login')
  }

  return (
    <nav style={{ background:'#fff', borderBottom:'1px solid #eee',
                  padding:'12px 24px', display:'flex',
                  justifyContent:'space-between', alignItems:'center' }}>
      <span style={{ fontWeight:'bold', fontSize:20, cursor:'pointer' }}
            onClick={() => navigate('/')}>Board</span>
      <div style={{ display:'flex', gap:10 }}>
        {/* loading 중일 때도 버튼 표시 */}
        {!loading && user ? (
          <>
            <span style={{ color:'#888', fontSize:14 }}>{user.name}님</span>
            <button onClick={() => navigate('/posts/new')}>글쓰기</button>
            <button onClick={handleLogout}>로그아웃</button>
          </>
        ) : !loading && (
          <>
            <button onClick={() => navigate('/login')}>로그인</button>
            <button onClick={() => navigate('/register')}>회원가입</button>
          </>
        )}
      </div>
    </nav>
  )
}