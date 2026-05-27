import { useNavigate } from 'react-router-dom'
import { useAuth } from '../hooks/useAuth'

export default function Navbar() {
  const { user, logout } = useAuth()
  const navigate = useNavigate()

  const handleLogout = async () => {
    await logout()
    navigate('/login')
  }

  return (
    <nav className="navbar">
      <div className="container">
        <span className="navbar-brand" onClick={() => navigate('/')}
              style={{ cursor: 'pointer' }}>
          Board
        </span>
        <div className="navbar-actions">
          {user ? (
            <>
              <span className="navbar-user">{user.nickname}님</span>
              <button className="btn btn-outline btn-sm"
                      onClick={() => navigate('/posts/new')}>
                글쓰기
              </button>
              <button className="btn btn-outline btn-sm" onClick={handleLogout}>
                로그아웃
              </button>
            </>
          ) : (
            <>
              <button className="btn btn-outline btn-sm"
                      onClick={() => navigate('/login')}>
                로그인
              </button>
              <button className="btn btn-primary btn-sm"
                      onClick={() => navigate('/register')}>
                회원가입
              </button>
            </>
          )}
        </div>
      </div>
    </nav>
  )
}
