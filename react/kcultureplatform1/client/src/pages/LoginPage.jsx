import React, { useState } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom';
import {useAuth} from '../context/AuthContext'

function LoginPage() {
  const navigate = useNavigate();
  //react-router-dom에서 제공하는 훅
  //로그인 페이지 성공 -> 메인페이지 이동 또는 뒤로가기 처리 사용 ex) navigate('/email')
  const location = useLocation();
  //현재 url 정보를 담고 있음(로그인 후 원래보던 페이지 돌아가기)
  const {login} = useAuth(); //커스텀 훅 useAuth에서 login 함수를 구조분해 할당으로 가져온거
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const from = location.state?.from?.pathname || '/';
  //로그인 후 원래 가려던 페이지 되돌아가기 또는 / 홈으로 가기

  const submit = async (e) => {
    e.preventDefault();
    setError(null); //이전 에러 메세지 제거
    console.log('[로그인 시도]',{email, from})
    try{
      const result = await login(email, password); //성공 시 다음 코드로 진행
      console.log('[로그인 성공]', result)
      navigate(from, {replace : true}); //성공 시 이동
    }catch(err) {
      console.log('[로그인 실패]', err.data ?? err.message)
      setError(err.data?.error || err.message);
    }
  }


  return (
    <div>
      <h1>로그인</h1>
      <p className="hint">세션 쿠키(httpOnly) · <code>fetch(..., credentials: &apos;include&apos;)</code></p>
      {/* 프론트에서 쿠키를 직접 다루지 않는다. javascript - httpOnly는 접근 불가능한 쿠키 */}
      {/* 로그인 요청 - 서버(세션생성) - 쿠키발급(httpOnly) - 브라우저 저장 - fetch + credentials = include */}
      {/* 서버 세션 확인 - 로그인 유지 */}
      <form onSubmit={submit} className='form'>
         <label>
          이메일
          <input
            type="email"
            autoComplete="username"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <label>
          비밀번호
          <input
            type="password"
            autoComplete="current-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">로그인</button>
      </form>
      <p>
        테스트: tourist@test.com / 1234 · <Link to="/join">회원가입</Link>
      </p>
    </div>
  )
}

export default LoginPage