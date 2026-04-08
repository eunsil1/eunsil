import { Link, NavLink, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Layout() {
  const { member, logout } = useAuth();

  return (
    // Link / NavLink 로고 메뉴 링크 . NavLink 현재 Url과 맞으면 active 클래스를 줌
    // <Outlet/> 여기 자리에 자식 라우트의 페이지가 렌더링 예) '/' Homepage, /login LoginPage
    // useAuth() 로그인 여부(member) 로그인/회원가입 vs 이름 로그아웃을 바꿔서 보여줌
    <div className="layout">
      <header className="header">
        <Link to="/" className="brand">
          K-Culture Platform 1
        </Link>
        <nav className="nav">
          <NavLink to="/" end>
            목록
          </NavLink>
          <NavLink to="/write">글쓰기</NavLink>
          {member ? (
            <>
              <span className="user">{member.name}</span>
              <button type="button" className="linkish" onClick={() => logout()}>
                로그아웃
              </button>
            </>
          ) : (
            <>
              <NavLink to="/login">로그인</NavLink>
              <NavLink to="/join">회원가입</NavLink>
            </>
          )}
        </nav>
      </header>
      <main className="main">
        <Outlet />
      </main>
      <footer className="footer">Vite · React Router · 이미지 uploads · Express session</footer>
    </div>
  );
}
