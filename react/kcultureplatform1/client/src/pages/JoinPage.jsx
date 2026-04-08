import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function JoinPage() {
  const navigate = useNavigate();
  const { join } = useAuth();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [nationality, setNationality] = useState('');
  const [language, setLanguage] = useState('en');
  const [error, setError] = useState(null);

  const submit = async (e) => {
    e.preventDefault();
    setError(null); //직전 에러메세지 지우기
    try {
      await join({ email, password, name, nationality, language });
      navigate('/'); //회원가입 후 - 메인(목록)으로 이동
      //AuthContext의 join이 api.join -> 서버 Post /api/auth/join 매핑
    } catch (err) {
      setError(err.data?.error || err.message);
    }
  };

  return (
    <div>
      <h1>회원가입</h1>
      <form onSubmit={submit} className="form">
        <label>
          이메일
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </label>
        <label>
          비밀번호
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </label>
        <label>
          이름
          <input value={name} onChange={(e) => setName(e.target.value)} required />
        </label>
        <label>
          국적
          <input value={nationality} onChange={(e) => setNationality(e.target.value)} />
        </label>
        <label>
          선호 언어
          <input value={language} onChange={(e) => setLanguage(e.target.value)} />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">가입</button>
      </form>
      <p>
        <Link to="/login">로그인</Link>
      </p>
    </div>
  );
}
