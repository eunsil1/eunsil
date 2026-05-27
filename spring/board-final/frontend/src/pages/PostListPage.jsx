import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api/axios'
import Navbar from '../components/Navbar'

export default function PostListPage() {
  const [posts, setPosts]   = useState([])
  const [page, setPage]     = useState(0)
  const [totalPages, setTotalPages] = useState(0)
  const [loading, setLoading] = useState(true)
  const navigate = useNavigate()

  useEffect(() => {
    setLoading(true)
    api.get(`/posts?page=${page}&size=10`)
      .then(res => {
        setPosts(res.data.content)
        setTotalPages(res.data.totalPages)
      })
      .finally(() => setLoading(false))
  }, [page])

  const formatDate = iso =>
    new Date(iso).toLocaleDateString('ko-KR', {
      year: 'numeric', month: '2-digit', day: '2-digit'
    })

  return (
    <>
      <Navbar />
      <div className="page">
        <div className="container">
          <div className="page-header">
            <h1>게시판</h1>
            <p>총 {totalPages > 0 ? '여러' : '0'}개의 게시글</p>
          </div>

          {loading ? (
            <div className="loading">불러오는 중...</div>
          ) : posts.length === 0 ? (
            <div className="empty">
              <div className="empty-icon">📝</div>
              <p>아직 게시글이 없습니다.</p>
            </div>
          ) : (
            <div className="post-list">
              {posts.map(post => (
                <div key={post.id} className="post-item"
                     onClick={() => navigate(`/posts/${post.id}`)}>
                  <div className="post-item-title">{post.title}</div>
                  <div className="post-item-meta">
                    <span>{post.authorNickname}</span>
                    <span>{formatDate(post.createdAt)}</span>
                  </div>
                </div>
              ))}
            </div>
          )}

          {totalPages > 1 && (
            <div className="pagination">
              <button className="page-btn"
                      disabled={page === 0}
                      onClick={() => setPage(p => p - 1)}>
                ‹
              </button>
              {Array.from({ length: totalPages }, (_, i) => (
                <button key={i}
                        className={`page-btn ${page === i ? 'active' : ''}`}
                        onClick={() => setPage(i)}>
                  {i + 1}
                </button>
              ))}
              <button className="page-btn"
                      disabled={page === totalPages - 1}
                      onClick={() => setPage(p => p + 1)}>
                ›
              </button>
            </div>
          )}
        </div>
      </div>
    </>
  )
}
