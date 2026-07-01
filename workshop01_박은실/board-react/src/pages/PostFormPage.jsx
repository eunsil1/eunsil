import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api/axios'
import Navbar from '../components/Navbar'
import { useAuth } from '../hooks/useAuth'

export default function PostFormPage() {
  const { id }  = useParams()
  const isEdit  = Boolean(id)
  const [title, setTitle]     = useState('')
  const [content, setContent] = useState('')
  const [files, setFiles]     = useState([])
  const [error, setError]     = useState('')
  const { user } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    if (!user) navigate('/login')
  }, [user, navigate])

  useEffect(() => {
    if (isEdit) {
      api.get(`/posts/${id}`)
        .then(res => {
          setTitle(res.data.title)
          setContent(res.data.content)
        })
    }
  }, [id, isEdit])

  const handleSubmit = async e => {
    e.preventDefault()
    setError('')
    try {
      const formData = new FormData()
      formData.append('title', title)
      formData.append('content', content)
      files.forEach(f => formData.append('files', f))
      const config = { headers: { 'Content-Type': 'multipart/form-data' } }

      if (isEdit) {
        await api.put(`/posts/${id}`, formData, config)
        navigate(`/posts/${id}`)
      } else {
        const res = await api.post('/posts', formData, config)
        navigate(`/posts/${res.data.id}`)
      }
    } catch (err) {
      setError(err.response?.data?.message || '저장 실패')
    }
  }

  return (
    <>
      <Navbar />
      <div style={{ maxWidth:700, margin:'40px auto', padding:24 }}>
        <h2>{isEdit ? '글 수정' : '글쓰기'}</h2>
        {error && <p style={{ color:'red' }}>{error}</p>}
        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom:12 }}>
            <input placeholder="제목" value={title}
                   onChange={e => setTitle(e.target.value)}
                   style={{ width:'100%', padding:8 }} required />
          </div>
          <div style={{ marginBottom:12 }}>
            <textarea placeholder="내용" value={content}
                      onChange={e => setContent(e.target.value)}
                      rows={12} style={{ width:'100%', padding:8 }} required />
          </div>
          <div style={{ marginBottom:12 }}>
            <input type="file" accept=".jpg,.jpeg,.png" multiple
                   onChange={e => setFiles(Array.from(e.target.files))} />
          </div>
          <div style={{ display:'flex', gap:10 }}>
            <button type="button" onClick={() => navigate(isEdit ? `/posts/${id}` : '/')}>
              취소
            </button>
            <button type="submit">{isEdit ? '수정 완료' : '등록'}</button>
          </div>
        </form>
      </div>
    </>
  )
}