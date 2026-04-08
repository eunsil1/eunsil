import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function WritePage() {
  const navigate = useNavigate();
  const { member, loading } = useAuth();
  const [categories, setCategories] = useState([]);
  const [categoryId, setCategoryId] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [file, setFile] = useState(null);
  const [error, setError] = useState(null);
//코드   역할
// useNavigate   등록 성공 후 상세 페이지로 이동 (/posts/:id)
// api   getCategories, createPost(FormData)
// useAuth   member, loading — 로그인 여부

  useEffect(() => { //AuthContext가 member를 불러오는 동안 loading이 true일 수 있음
    if (!loading && !member) { //로딩이 끝났는데 member가 없으면 비로그인 -> login으로 보냄
      navigate('/login', { replace: true, state: { from: '/write' } });
      //state: { from: '/write' } 로그인 후 다시 글쓰기로 오기 쉽게 
    }
  }, [loading, member, navigate]);

  useEffect(() => {
    api.getCategories().then((cats) => { //api.getCategories() 한번 호출
      setCategories(cats); //받은 목록으로 categories 설정
      if (cats[0]) setCategoryId(String(cats[0].id)); //첫 카테고리 id를 categoryid에 넣음
    });
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      const fd = new FormData();
      fd.append('categoryId', String(categoryId));
      fd.append('title', title);
      fd.append('content', content);
      if (file) fd.append('image', file); //이미지 파일이 있으면 추가
      const { id } = await api.createPost(fd); //-> multipart로 post /api/posts
      navigate(`/posts/${id}`); //저장 성공하면 -> 방금 쓴 글 상세로 이동
    } catch (err) { //실패 시 에러
      setError(err.data?.error || err.message);
    }
  };

  if (loading || !member) { 
    return <p>확인 중…</p>;
  } //로그인 판별전이거나 비로그인이면 확인 중만 표시

  //로그인 한 사람만 글쓰기 폼을 보여주고, formData로 글 + 이미지를 등록한 뒤 새 글 상세로 이동하는 페이지

  return (
    <div>
      <h1>글쓰기</h1>
      <p className="hint">
        이미지는 선택 사항입니다. 서버 <code>server/uploads</code>에 저장됩니다.
      </p>
      <form onSubmit={submit} className="form">
        <label>
          카테고리
          <select value={categoryId} onChange={(e) => setCategoryId(e.target.value)}>
            {categories.map((c) => (
              <option key={c.id} value={c.id}>
                {c.icon} {c.nameEn}
              </option>
            ))}
          </select>
        </label>
        <label>
          제목
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </label>
        <label>
          내용
          <textarea value={content} onChange={(e) => setContent(e.target.value)} rows={12} />
        </label>
        <label>
          이미지 (JPEG / PNG / GIF / WebP, 최대 5MB)
          <input
            type="file"
            accept="image/jpeg,image/png,image/gif,image/webp"
            onChange={(e) => setFile(e.target.files?.[0] ?? null)}
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">등록</button>
      </form>
    </div>
  );
}
