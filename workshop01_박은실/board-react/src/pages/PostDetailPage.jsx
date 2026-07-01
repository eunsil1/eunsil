import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api/axios'
import Navbar from '../components/Navbar'
import { useAuth } from '../hooks/useAuth'

export default function PostDetailPage() {
  const { id }  = useParams()
  const [post, setPost]     = useState(null)
  const [loading, setLoading] = useState(true)
  const { user } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    api.get(`/posts/${id}`)
      .then(res => setPost(res.data))
      .finally(() => setLoading(false))
  }, [id])

  const handleDelete = async () => {
    if (!window.confirm('삭제하시겠습니까?')) return
    await api.delete(`/posts/${id}`)
    navigate('/')
  }

  const isAuthor = user && post && user.id === post.authorId

  return (
    <>
      <Navbar />
      <div style={{ maxWidth:700, margin:'40px auto', padding:24 }}>
        {loading && <p>불러오는 중...</p>}
        {post && (
          <>
            <h2>{post.title}</h2>
            <p style={{ color:'#888', fontSize:13 }}>
              {post.authorName} · {post.createdAt?.slice(0, 10)}
            </p>
            <hr />
            <p style={{ marginTop:24, lineHeight:1.8, whiteSpace:'pre-wrap' }}>
              {post.content}
            </p>
            {post.images?.length > 0 && (
              <div style={{ display:'flex', gap:10, marginTop:20, flexWrap:'wrap' }}>
                {post.images.map(img => (
                  <img key={img.id} src={'http://localhost:8087' + img.url} alt=""
                       style={{ maxWidth:'100%', height:'auto', borderRadius:8, border: '1px solid #eee' }} />
                ))}
              </div>
            )}
            <div style={{ display:'flex', gap:10, marginTop:32 }}>
              <button onClick={() => navigate('/')}>목록</button>
              {isAuthor && (
                <>
                  <button onClick={() => navigate(`/posts/${id}/edit`)}>수정</button>
                  <button onClick={handleDelete} style={{ color:'red' }}>삭제</button>
                </>
              )}
            </div>
          </>
        )}
      </div>
    </>
  )
}