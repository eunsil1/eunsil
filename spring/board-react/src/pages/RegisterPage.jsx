import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import api from '../api/axios'

export default function RegisterPage() {
  const [form, setForm]   = useState({ username: '', password: '', name: '' })
  const [error, setError] = useState('')
  const navigate = useNavigate()

  const handleSubmit = async e => {
    e.preventDefault()
    setError('')
    try {
      await api.post('/members', form)
      navigate('/login')
    } catch (err) {
      setError(err.response?.data?.message || '회원가입 실패')
    }
  }

  return (
    <div style={{ maxWidth:400, margin:'100px auto', padding:24 }}>
      <h2>회원가입</h2>
      {error && <p style={{ color:'red' }}>{error}</p>}
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
        <div style={{ marginBottom:12 }}>
          <input placeholder="이름" value={form.name}
                 onChange={e => setForm(p => ({...p, name: e.target.value}))}
                 style={{ width:'100%', padding:8 }} required />
        </div>
        <button type="submit" style={{ width:'100%', padding:10 }}>회원가입</button>
      </form>
      <p style={{ marginTop:12 }}>이미 계정이 있으신가요? <Link to="/login">로그인</Link></p>
    </div>
  )
}