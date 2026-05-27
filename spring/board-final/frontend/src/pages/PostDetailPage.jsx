import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api/axios'
import Navbar from '../components/Navbar'
import { useAuth } from '../hooks/useAuth'

export default function PostDetailPage() {
  const { id }  = useParams()
  const [post, setPost]       = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError]     = useState('')
  const { user } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    api.get('/posts/' + id)
      .then(res => setPost(res.data))
      .catch(() => setError('게시글을 찾을 수 없습니다.'))
      .finally(() => setLoading(false))
  }, [id])

  const handleDelete = async () => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return
    try {
      await api.delete('/posts/' + id)
      navigate('/')
    } catch (err) {
      alert(err.response?.data?.message || '삭제에 실패했습니다.')
    }
  }

  const fmt = iso => new Date(iso).toLocaleString('ko-KR', {
    year:'numeric', month:'2-digit', day:'2-digit',
    hour:'2-digit', minute:'2-digit'
  })

  const isAuthor = user && post && user.memberId === post.memberId

  return (
    <>
      <Navbar />
      <div className="page">
        <div className="container">
          {loading && <div className="loading">불러오는 중...</div>}
          {error   && <div className="alert alert-error">{error}</div>}

          {post && (
            <div className="card">
              <div className="post-detail-header">
                <h1 className="post-detail-title">{post.title}</h1>
                <div className="post-detail-meta">
                  <span>✍ {post.authorNickname}</span>
                  <span>🕐 {fmt(post.createdAt)}</span>
                </div>
              </div>

              {/* 이미지 목록 — 작업형 3 */}
              {post.images && post.images.length > 0 && (
                <div style={{ display:'flex', gap:12, flexWrap:'wrap', marginBottom:24 }}>
                  {post.images.map(img => (
                    <a key={img.id}
                       href={'http://localhost:8080' + img.url}
                       target="_blank" rel="noreferrer">
                      <img src={'http://localhost:8080' + img.thumbnailUrl}
                           alt="첨부 이미지"
                           style={{ width:120, height:120, objectFit:'cover',
                                    borderRadius:10, border:'1px solid var(--border)',
                                    cursor:'pointer' }} />
                    </a>
                  ))}
                </div>
              )}

              <div className="post-detail-body">{post.content}</div>

              <div className="post-detail-actions">
                <button className="btn btn-outline" onClick={() => navigate('/')}>
                  목록
                </button>
                {isAuthor && (
                  <>
                    <button className="btn btn-outline"
                            onClick={() => navigate('/posts/' + id + '/edit')}>
                      수정
                    </button>
                    <button className="btn btn-danger" onClick={handleDelete}>
                      삭제
                    </button>
                  </>
                )}
              </div>
            </div>
          )}
        </div>
      </div>
    </>
  )
}
