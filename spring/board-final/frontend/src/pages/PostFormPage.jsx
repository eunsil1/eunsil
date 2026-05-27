import { useEffect, useRef, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api/axios'
import Navbar from '../components/Navbar'
import { useAuth } from '../hooks/useAuth'

export default function PostFormPage() {
  const { id }    = useParams()
  const isEdit    = Boolean(id)
  const [title, setTitle]       = useState('')
  const [content, setContent]   = useState('')
  const [files, setFiles]       = useState([])
  const [previews, setPreviews] = useState([])
  const [existingImages, setExistingImages] = useState([])
  const [error, setError]       = useState('')
  const [loading, setLoading]   = useState(false)
  const fileRef = useRef()
  const { user } = useAuth()
  const navigate = useNavigate()

  useEffect(() => { if (!user) navigate('/login') }, [user, navigate])

  useEffect(() => {
    if (isEdit) {
      api.get('/posts/' + id)
        .then(res => {
          setTitle(res.data.title)
          setContent(res.data.content)
          setExistingImages(res.data.images || [])
        })
        .catch(() => navigate('/'))
    }
  }, [id, isEdit, navigate])

  const handleFiles = e => {
    const selected = Array.from(e.target.files).slice(0, 3)
    setFiles(selected)
    setPreviews(selected.map(f => URL.createObjectURL(f)))
  }

  const handleSubmit = async e => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      const formData = new FormData()
      formData.append('title', title)
      formData.append('content', content)
      files.forEach(f => formData.append('files', f))
      const config = { headers: { 'Content-Type': 'multipart/form-data' } }

      if (isEdit) {
        await api.put('/posts/' + id, formData, config)
        navigate('/posts/' + id)
      } else {
        const res = await api.post('/posts', formData, config)
        navigate('/posts/' + res.data.id)
      }
    } catch (err) {
      setError(err.response?.data?.message || '저장에 실패했습니다.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <>
      <Navbar />
      <div className="page">
        <div className="container">
          <div className="page-header">
            <h1>{isEdit ? '글 수정' : '새 글 쓰기'}</h1>
          </div>
          <div className="card">
            {error && <div className="alert alert-error">{error}</div>}
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>제목</label>
                <input value={title} onChange={e => setTitle(e.target.value)}
                       placeholder="제목을 입력하세요" autoFocus required />
              </div>
              <div className="form-group">
                <label>내용</label>
                <textarea value={content} onChange={e => setContent(e.target.value)}
                          placeholder="내용을 입력하세요" rows={10} required />
              </div>
              <div className="form-group">
                <label>이미지 첨부 (jpg/png, 최대 3장, 각 10MB 이하)</label>
                <input type="file" ref={fileRef} accept=".jpg,.jpeg,.png"
                       multiple onChange={handleFiles} style={{ padding: '8px 0' }} />
                {previews.length > 0 && (
                  <div style={{ display:'flex', gap:10, marginTop:10, flexWrap:'wrap' }}>
                    {previews.map((src, i) => (
                      <img key={i} src={src} alt="미리보기"
                           style={{ width:100, height:100, objectFit:'cover',
                                    borderRadius:8, border:'1px solid var(--border)' }} />
                    ))}
                  </div>
                )}
                {existingImages.length > 0 && (
                  <div style={{ marginTop:10 }}>
                    <p style={{ fontSize:13, color:'var(--muted)', marginBottom:6 }}>기존 이미지</p>
                    <div style={{ display:'flex', gap:10, flexWrap:'wrap' }}>
                      {existingImages.map(img => (
                        <img key={img.id}
                             src={'http://localhost:8080' + img.thumbnailUrl}
                             alt=""
                             style={{ width:100, height:100, objectFit:'cover',
                                      borderRadius:8, border:'1px solid var(--border)' }} />
                      ))}
                    </div>
                  </div>
                )}
              </div>
              <div style={{ display:'flex', gap:10 }}>
                <button type="button" className="btn btn-outline"
                        onClick={() => navigate(isEdit ? '/posts/' + id : '/')}>
                  취소
                </button>
                <button type="submit" className="btn btn-primary" disabled={loading}>
                  {loading ? '저장 중...' : (isEdit ? '수정 완료' : '등록')}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  )
}
