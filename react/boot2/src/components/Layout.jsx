import { Outlet, NavLink, Link } from 'react-router-dom';
import { Container, Navbar, Nav } from 'react-bootstrap';

const navLink = ({ isActive }) =>
  isActive ? 'nav-link active fw-semibold' : 'nav-link';

export default function Layout() {
  return (
    <>
      <Navbar bg="white" expand="lg" className="shadow-sm sticky-top border-bottom">
        <Container>
          <Navbar.Brand as={Link} to="/" className="fw-bold text-primary">
            <i className="bi bi-bootstrap-fill me-2" />
            Boot2
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="boot2-nav" />
          <Navbar.Collapse id="boot2-nav">
            <Nav className="ms-auto">
              <Nav.Link as={NavLink} to="/" end className={navLink}>
                홈
              </Nav.Link>
              <Nav.Link as={NavLink} to="/services" className={navLink}>
                서비스
              </Nav.Link>
              <Nav.Link as={NavLink} to="/contact" className={navLink}>
                문의
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <div className="main-wrap">
        <Outlet />
      </div>

      <footer className="site-footer mt-auto py-5">
        <Container>
          <div className="row g-4">
            <div className="col-md-4">
              <h5 className="text-white mb-3">Boot2</h5>
              <p className="small mb-0">
                React + Bootstrap + Slick 슬라이더로 만든 데모 사이트입니다.
              </p>
            </div>
            <div className="col-md-4">
              <h6 className="text-white text-uppercase small mb-3">바로가기</h6>
              <ul className="list-unstyled small mb-0">
                <li className="mb-2">
                  <Link to="/">홈</Link>
                </li>
                <li className="mb-2">
                  <Link to="/services">서비스</Link>
                </li>
                <li>
                  <Link to="/contact">문의</Link>
                </li>
              </ul>
            </div>
            <div className="col-md-4">
              <h6 className="text-white text-uppercase small mb-3">연락</h6>
              <p className="small mb-1">
                <i className="bi bi-envelope me-2" />
                hello@boot2.example
              </p>
              <p className="small mb-0">
                <i className="bi bi-geo-alt me-2" />
                서울시 · 데모 주소
              </p>
            </div>
          </div>
          <hr className="border-secondary my-4 opacity-25" />
          <p className="text-center small mb-0 text-secondary">
            © {new Date().getFullYear()} Boot2. 교육용 예제입니다.
          </p>
        </Container>
      </footer>
    </>
  );
}