import React from 'react'
import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { api } from '../api/client';
import { useAuth } from '../context/AuthContext';


function EditPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { member, loading } = useAuth(); //member 로그인정보, loading 로그인 상태 확인중
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [imageFilename, setImageFilename] = useState(null);
  const [file, setFile] = useState(null);
  const [removeImage, setRemoveImage] = useState(false);
  const [ownerId, setOwnerId] = useState(null);
  const [error, setError] = useState(null);

  //로그인체크 -> 게시글 데이터 불러오고 -> 작성자 본인인지 확인 -> 수정폼 입력 -> formdate 서버전송
  useEffect(() => {
    if (!loading && !member) {
      navigate('/login', { replace: true });
    }
  }, [loading, member, navigate]);

  //로그인이 안되어있으면 -> /login 페이지 이동

  //서버에서 수정용 데이터를 가져옴
  useEffect(() => {
    api
      .getPostForEdit(id)
      .then(({ post }) => {
        setTitle(post.title); //제목
        setContent(post.content ?? ''); //내용
        setImageFilename(post.imageFilename ?? null); //기존 이미지 파일명
        setOwnerId(post.memberId); //작성자
        setRemoveImage(false);
        setFile(null);
      })
      .catch((e) => setError(e.message));
  }, [id]);

  useEffect(() => {
    if (member && ownerId != null && member.id !== ownerId) {
      navigate('/', { replace: true });
    }
  }, [member, ownerId, navigate]);
  //member - 로그인한 멤버
  //ownerId - 수정하려는 id
  //내가 쓴글이 아니면 -> 홈으로 보냄

  const submit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      const fd = new FormData();
      fd.append('title', title);
      fd.append('content', content);
      if(removeImage) fd.append('removeImage','1'); //서버에서 이 값 보고 이미지 삭제
      if (file) fd.append('image', file); //이미지 파일이 있으면 추가
      await api.updatePost(id, fd); //-> multipart로 post /api/posts
      navigate(`/posts/${id}`); //저장 성공하면 -> 방금 쓴 글 상세로 이동
    } catch (err) { //실패 시 에러
      setError(err.data?.error || err.message);
    }
  };

  if (loading || !member) {
    return <p>확인 중…</p>;
  }

  if (error && ownerId == null) {
    return (
      <p className="error">
        {error} <Link to="/">목록</Link>
      </p>
    );
  }


  return (
     <div>
      <h1>수정</h1>
      <p className="hint">새 이미지를 올리면 기존 파일은 삭제됩니다.</p>
      <form onSubmit={submit} className="form">
        <label>
          제목
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </label>
        <label>
          내용
          <textarea value={content} onChange={(e) => setContent(e.target.value)} rows={12} />
        </label>
        {imageFilename && !file && ( //기존 이미지가 있고 새 파일이 선택 안했을때 이미지 보여줌
          <div className="current-image">
            {!removeImage && (
              <>
                <p>현재 이미지</p>
                <img src={`/uploads/${imageFilename}`} alt="" className="preview" />
              </>
            )}
            {/* 체크하면 removeImage == true */}
            <label className="check"> 
              <input
                type="checkbox"
                checked={removeImage}
                onChange={(e) => setRemoveImage(e.target.checked)}
              />
              이미지 제거
            </label>
          </div>
        )}
        <label>
          새 이미지로 교체 (선택)
          <input
            type="file"
            accept="image/jpeg,image/png,image/gif,image/webp"
            onChange={(e) => { //새이미지를 선택하면
              setFile(e.target.files?.[0] ?? null);
              if (e.target.files?.[0]) setRemoveImage(false);
            }}
            //기존 이미지 삭제 체크 자동해제
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">저장</button>
      </form>
    </div>
  );
  //이미지 처리 -
  //기존이미지 -> imageFilename
  //새이미지 -> file
  //삭제여부 -> removeImage
}

export default EditPage