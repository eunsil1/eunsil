import { Route, useLocation, Link, useNavigate, useParams } from "react-router-dom";
import { useState, useEffect, use } from "react";
import { Routes } from "react-router-dom";


const API = '/api/doits'
//기본경로

type DoIt = {
  num : number
  title : string
  content : string
}

//fetchJson - Api 요청 전담 함수
//기본은 미리셋팅 content-Type 같은 경우는 미리셋팅 + 세부설정은 (호출하는쪽)에서 결정(덮어씌우거나 추가)
//<T> 제네릭 : 요청의 응답 json이 어떤 타입인지 호출하는 쪽에서 지정할 수 있음
//Promise : fetch, axios, setTimeout 같은 비동기 작업 결과를 담는 객체
//<> : 비동기 작업 성공 시 최종적으로 얻는 타입
async function fetchJson<T>(url:string, options?:RequestInit):Promise<T> {
  const res = await fetch(url, {
    headers: { 'Content-Type': 'application/json', Accept: 'application/json' },
    ...options,
  })
  //options 내용이 없으면 get 인정(read)
  //...options 호출 시 추가로 옵션 넣을 예정(post, put, delete, json 변경)
  if (!res.ok) {
    const text = await res.text()
    throw new Error(text || res.statusText)
  }
  if (res.status === 204) return null as T
  return res.json()
}
//204 삭제시 응답은 성공, 내용은 없음

function Layout({children}:{children:React.ReactNode}){
  const location = useLocation()
  const flash = (location.state as {msg?:string} | null)?.msg
  //페이지가 이동할 때 전달할 메세지(msg)가 있으면 꺼내고 없으면 undefined
  return (
    <>
      <nav className="app-nav py-3 mb-4">
        <div className="container d-flex gap-3 align-items-center">
          <Link className="navbar-brand mb-0 text-decoration-none fw-bold" to="/list">
            crud2-Api
          </Link>
          <Link className="btn btn-sm btn-outline-primary" to="/list">
            목록
          </Link>
          <Link className="btn btn-sm btn-primary" to="/mains/add">
            글쓰기
          </Link>
        </div>
      </nav>
      {flash && (
        <div className="container flash-msg">
          <div className="alert alert-primary alert-dismissible fade show" role="alert">
            {flash}
            <button type="button" className="btn-close" data-bs-dismiss="alert" aria-label="Close" />
          </div>
        </div>  
      )}
      <div className="container pb-5">{children}</div>
      </>
  )
  
}

//요청해서 페이지를 가져오는 rest api 주소는 /api/doits
// /list : 사람이 보는 페이지(React Router 처리함)
function ListPage() {
  const location = useLocation() //use 정보 가져옴
  const [items, setItems] = useState<DoIt[]>([]) //게시글 목록 저장
  const [error, setError] = useState<string | null>(null) //Api 호출 시 에러메세지
  const [loading, setLoading] = useState(true) //목록을 가지고 오는 중인지 여부

  useEffect(() => {
    setLoading(true)
    setError(null)
    fetchJson<DoIt[]>(API)
      .then(setItems)
      .catch((e : unknown) => setError(e instanceof Error ?e.message : String(e)))
      .finally(() => setLoading(false))
  }, [location.key]) //페이지 이동 시 호출

  if (loading) return <p className="text-muted">불러오는 중…</p>
  if (error) return <div className="alert alert-danger">{error}</div>

  return (
    <>
      <h1 className="h3 mb-3">게시판</h1>
      <table className="table table-bordered bg-white">
        <thead>
          <tr>
            <th>Num</th>
            <th>Title</th>
            <th>Content</th>
            <th>액션</th>
          </tr>
        </thead>
        <tbody>
          {items.map((row) => (
            <tr key={row.num}>
              <td>{row.num}</td>
              <td>
                <Link to={`/list/${row.num}`}>{row.title}</Link>
              </td>
              <td>{row.content}</td>
              <td>
                <Link className="btn btn-sm btn-outline-primary me-1" to={`/list/${row.num}/edit`}>
                  수정
                </Link>
                <DeleteButton num={row.num} />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {items.length === 0 && <p className="text-muted">데이터가 없습니다.</p>}
    </>
  )
}

function DeleteButton({ num } : {num : number}) {
  const navigate = useNavigate()
  const onDelete = async () => {
    if (!confirm('삭제할까요?')) return
    try {
      const res = await fetch(`${API}/${num}`, { method: 'DELETE' })
      if (res.status === 204) {
        navigate('/list', { replace: true, state: { msg: '삭제가 완료되었습니다.' } })
      } else {
        alert('삭제 실패')
      }
      //타입스크립트에서 e는 기본이 unknown이라서 e.message를 쓰면 message가 있는지 확실치 않다는 오류가 남
    } catch (e : unknown) {
      alert(e instanceof Error ? e.message : String(e))
      //e가 진짜 error 객체이면 e.message 출력 아니면 강제로 String(e)로 문자열로 만들어 표시
    }
  }
  return (
    <button type="button" className="btn btn-sm btn-outline-danger" onClick={onDelete}>
      삭제
    </button>
  )
}

function DetailPage(){
  //사용자가 /list/123 으로 접속하면 useParams() num : 123 객체를 반환
  const {num} = useParams<{num:string}>()
  const [item, setItem] = useState<DoIt | null>(null)
  const [error, setError] = useState<string | null>(null)
  useEffect(() => {
    fetchJson<DoIt>(`${API}/${num}`)
      .then(setItem)
      .catch(() => setError('없는 글이거나 오류가 났습니다.'));
    
  },[num])

  if(error) return <div className="alert alert-warning">{error}</div>
  if(!item) return <p className="text-muted">불러오는 중...</p>

  return (
    <>
      <h1 className="h3 mb-3">글 상세</h1>
      <div className="card">
          <div className="card-body">
            <h2 className="h5">{item.title}</h2>
            <p className="text-muted small">번호: {item.num}</p>
            <p className="mb-0">{item.content}</p>
          </div>
      </div>
      <div className="mt-3">
        <Link className="btn btn-primary me-2" to={`/list/${num}/edit`}>
          수정
        </Link>
        <Link className="btn btn-secondary" to="/list">
          목록
        </Link>
      </div>
      
    </>
  )
  
}

function AddPage(){
  //useNavigate : 지도 내비게이션
  //return "redirect:/list" -> 프론트 엔드 navigate("/list") 
  const navigate = useNavigate()
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')
  const [err, setErr] = useState<string | null>(null)

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setErr(null)
    try {
      const saved = await fetchJson<DoIt>(API, {
        method: 'POST',
        body: JSON.stringify({title, content}),
        
      })
      navigate(`/list/${saved.num}`, {replace:true})
    }catch(e2 : unknown){
      setErr(e2 instanceof Error ? e2.message : String(e2))
    }
  }

  return(
    <>
      <h1 className="h3 mb-3">글 작성</h1>
      {err && <div className="alert alert-danger">{err}</div>}
      <form onSubmit={onSubmit} className="card card-body">

        <div className="mb-3">
          <label className="form-label">제목</label>
          <input type="text" className="form-control" value={title} onChange={(e) => setTitle(e.target.value)} required/>
        </div>

         <div className="mb-3">
          <label className="form-label">내용</label>
          <textarea className="form-control" rows={5} value={content} onChange={(e) => setContent(e.target.value)} required />
        </div>
        <button type="submit" className="btn btn-primary">
          등록
        </button>

        <Link className="btn btn-link" to="/list">
          취소
        </Link>
      </form>
    </>
  );
}

function EditPage(){
  const {num} = useParams<{num:string}>()
  const navigate = useNavigate()
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')
  const [loading, setLoading] = useState(true)
  const [err, setErr] = useState<string | null>(null)

  useEffect(() => {
    fetchJson<DoIt>(`${API}/${num}`)
      .then((row)=>{
        setTitle(row.title ?? '')
        setContent(row.content ?? '')
      })
      .catch(() => setErr('글을 불러올 수 없습니다.'))
      .finally(() => setLoading(false))
  },[num])

  const onSubmit = async (e : React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setErr(null)
    try {
      const saved = await fetchJson<DoIt>(`${API}/${num}`, {
        method: 'PUT',
        body: JSON.stringify({title, content}),
        
      })
      navigate(`/list/${saved.num}`, {replace:true})
    }catch(e2 : unknown){
      setErr(e2 instanceof Error ? e2.message : String(e2))
    }
}
  if (loading) return <p className="text-muted">불러오는 중…</p>
  if (err && !title && !content) return <div className="alert alert-warning">{err}</div>
  return (
    <>
      <h1 className="h3 mb-3">글 수정</h1>
      {err && <div className="alert alert-danger">{err}</div>}
      <form onSubmit={onSubmit} className="card card-body">

        <div className="mb-3">
          <label className="form-label">제목</label>
          <input type="text" className="form-control" value={title} onChange={(e) => setTitle(e.target.value)} required/>
        </div>

         <div className="mb-3">
          <label className="form-label">내용</label>
          <textarea className="form-control" rows={5} value={content} onChange={(e) => setContent(e.target.value)} required />
        </div>
        <button type="submit" className="btn btn-primary">
          저장
        </button>

        <Link className="btn btn-link" to="{`/list/${num}`}">
          취소
        </Link>
      </form>
    </>
  )
}

export default function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<ListPage/>}/>
        <Route path="/list" element={<ListPage/>}/>
        <Route path="/list/:num" element={<DetailPage/>}/>
        <Route path="/list/:num/edit" element={<EditPage/>}/>
        <Route path="/mains/add" element={<AddPage/>}/>
      </Routes>
    </Layout>
  )
}
