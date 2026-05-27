import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import api from '../api/axios'

export default function RegisterPage() {
  const [form, setForm]   = useState({ username: '', password: '', nickname: '' })
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const handleChange = e =>
    setForm(p => ({ ...p, [e.target.name]: e.target.value }))

  const handleSubmit = async e => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      await api.post('/members', form)
      navigate('/login')
    } catch (err) {
      const msg = err.response?.data?.message
        || err.response?.data?.fieldErrors
          ? Object.values(err.response.data.fieldErrors).join(', ')
          : '회원가입에 실패했습니다.'
      setError(msg)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-box">
        <div className="brand">Board</div>
        <p className="subtitle">새 계정을 만들어보세요</p>

        <div className="card">
          {error && <div className="alert alert-error">{error}</div>}

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>아이디</label>
              <input name="username" value={form.username}
                     onChange={handleChange} placeholder="3~50자"
                     autoFocus required />
            </div>
            <div className="form-group">
              <label>비밀번호</label>
              <input name="password" type="password" value={form.password}
                     onChange={handleChange} placeholder="4자 이상"
                     required />
            </div>
            <div className="form-group">
              <label>닉네임</label>
              <input name="nickname" value={form.nickname}
                     onChange={handleChange} placeholder="화면에 표시될 이름"
                     required />
            </div>
            <button className="btn btn-primary" style={{ width: '100%' }}
                    disabled={loading}>
              {loading ? '처리 중...' : '회원가입'}
            </button>
          </form>
        </div>

        <p className="auth-footer">
          이미 계정이 있으신가요? <Link to="/login">로그인</Link>
        </p>
      </div>
    </div>
  )
}
