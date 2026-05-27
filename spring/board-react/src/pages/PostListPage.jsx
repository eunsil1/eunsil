import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api/axios'
import Navbar from '../components/Navbar'

export default function PostListPage() {
  const [posts, setPosts]     = useState([])
  const [loading, setLoading] = useState(true)
  const navigate = useNavigate()

  useEffect(() => {
    api.get('/posts')
      .then(res => {
        // 배열로 오면 그대로, 페이징 객체로 오면 content 꺼내기
        const data = res.data
        if (Array.isArray(data)) {
          setPosts(data)
        } else if (data.content) {
          setPosts(data.content)
        } else {
          setPosts([])
        }
      })
      .catch(() => setPosts([]))
      .finally(() => setLoading(false))
  }, [])

  return (
    <>
      <Navbar />
      <div style={{ maxWidth:700, margin:'40px auto', padding:24 }}>
        <h2>게시판</h2>
        {loading && <p>불러오는 중...</p>}
        {!loading && posts.length === 0 && <p>게시글이 없습니다.</p>}
        {posts.map(post => (
          <div key={post.id}
               onClick={() => navigate(`/posts/${post.id}`)}
               style={{ padding:16, borderBottom:'1px solid #eee', cursor:'pointer' }}>
            <div style={{ fontWeight:'bold' }}>{post.title}</div>
            <div style={{ color:'#888', fontSize:13 }}>
              {post.authorName} · {post.createdAt?.slice(0, 10)}
            </div>
          </div>
        ))}
      </div>
    </>
  )
}