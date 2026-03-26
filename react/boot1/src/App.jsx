import React from 'react'

function App() {
  return (
    <>
       {/* Navbar */}
       {/* navbar-expand-lg 992px이상이면 메뉴를 펼치고, 그보다 작으면 햄버거 버튼 */}
       {/* sticky-top 스크롤 시 상단 고정 shadow-sm 작은 그림자 */}
       {/* fw-bold -> font-weight : bold */}
       {/* navbar-toggler 햄버거 버튼 */}
       {/* navbar-toggler-icon 햄버거 아이콘모양 */}
       {/* navbar-collapse 내비용 collapser(접었다 펼칠수 있는 영역) */}
       {/* ms-auto margin-start: auto -> 플렉스나 그리드에서 오른쪽으로 밀기
        mb-2 -> 아래 마진 : 기본 2
        mb-lg-0 -> lg이상에서는 0 */}
       {/* `data-bs-toggle="collapse"`, `data-bs-target="#mainNav"` 는 Bootstrap JS로 접기/펼치기 동작을 붙이는 **데이터 속성**입니다. */}
      <nav className="navbar navbar-expand-lg navbar-dark bg-primary sticky-top shadow-sm">
        <div className="container">
          <a className="navbar-brand fw-bold" href="#">
            Bootstrap Demo
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#mainNav"
            aria-controls="mainNav"
            aria-expanded="false"
            aria-label="메뉴"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="mainNav">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <a className="nav-link active" href="#home">
                  Home
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#features">
                  Features
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#cards">
                  Cards
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#contact">
                  Contact
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>

       {/* Hero */}
       {/* py-5 위아래 패딩 5 border-bottom 아래쪽 테두리 한줄 py-lg-5 lg 이상에서만 padding 5*/}
       {/* align-item-center 세로방향 가운데 정렬 */}
       {/* display-4 큰글씨  */}
      <header id="home" className="hero py-5 bg-light border-bottom">
        <div className="container py-lg-5">
          <div className="row align-items-center">
            <div className="col-lg-7">
              <h1 className="display-4 fw-bold text-primary mb-3">
                Bootstrap 5 + React
              </h1>
              {/* lead 부제/리드 문단용 큰 본문 스타일 */}
              <p className="lead text-secondary mb-4">
                그리드, 카드, 버튼, 내비게이션 등 Bootstrap 유틸리티 클래스로 빠르게
                레이아웃을 만드는 예제 페이지입니다.
              </p>
              {/* d-flex display : flex flex-wrap - 줄바꿈허용 */}
              <div className="d-flex flex-wrap gap-2">
                <button type="button" className="btn btn-primary btn-lg px-4">
                  시작하기
                </button>
                <button
                  type="button"
                  className="btn btn-outline-secondary btn-lg px-4"
                >
                  문서 보기
                </button>
              </div>
            </div>
            {/* ratio-4x3 4:3 비율 */}
            {/* fs-1 글자 크기 1단계 */}
            <div className="col-lg-5 mt-4 mt-lg-0">
              <div className="ratio ratio-4x3 rounded-3 overflow-hidden shadow bg-white border">
                <div className="d-flex align-items-center justify-content-center bg-light text-primary fs-1 fw-bold">
                  Demo
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>

        {/* Features */}
        {/* col-lg-8 mx-auto 최대크기 8열 너비, 가로 중앙정렬*/}
      <section id="features" className="py-5">
        <div className="container">
          <h2 className="text-center fw-bold mb-2">Features</h2>
          <p className="text-center text-secondary mb-5 col-lg-8 mx-auto">
            12열 그리드, 반응형 브레이크포인트, 스페이싱 유틸리티를 활용한 섹션입니다.
          </p>
          {/* g-4 행 + 열 사이 - gap 4단계 */}
          {/* bg-body-tertiary 본문과 어울리는 -삼차 배경- */}
          <div className="row g-4">
            <div className="col-md-4">
              <div className="p-4 h-100 border rounded-3 bg-body-tertiary">
                <div className="text-primary fs-2 mb-3">⚡</div>
                <h3 className="h5 fw-semibold">빠른 프로토타입</h3>
                <p className="text-secondary small mb-0">
                  클래스만 조합해 레이아웃과 스타일을 적용할 수 있습니다.
                </p>
              </div>
            </div>
            <div className="col-md-4">
              <div className="p-4 h-100 border rounded-3 bg-body-tertiary">
                <div className="text-primary fs-2 mb-3">📱</div>
                <h3 className="h5 fw-semibold">반응형</h3>
                <p className="text-secondary small mb-0">
                  sm, md, lg 등 브레이크포인트로 화면 크기에 맞게 배치합니다.
                </p>
              </div>
            </div>
            <div className="col-md-4">
              <div className="p-4 h-100 border rounded-3 bg-body-tertiary">
                <div className="text-primary fs-2 mb-3">🎨</div>
                <h3 className="h5 fw-semibold">테마 색상</h3>
                <p className="text-secondary small mb-0">
                  primary, secondary, success 등 컬러 유틸리티를 활용합니다.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

       {/* Cards */}
      <section id="cards" className="py-5 bg-light">
        <div className="container">
          <h2 className="text-center fw-bold mb-5">Cards</h2>
          {/* 기본 1열 row-cols-1 / row-cols-md-3 md이상 3열 */}
          <div className="row row-cols-1 row-cols-md-3 g-4">
            <div className="col">
              <div className="card h-100 shadow-sm border-0">
                <div className="card-body">
                  <h5 className="card-title">Card One</h5>
                  <p className="card-text text-secondary small">
                    Bootstrap 카드 컴포넌트로 콘텐츠 영역을 구분합니다.
                  </p>
                  <a href="#" className="btn btn-sm btn-primary">
                    자세히
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card h-100 shadow-sm border-0">
                <div className="card-body">
                  <h5 className="card-title">Card Two</h5>
                  <p className="card-text text-secondary small">
                    그림자와 테두리 없음으로 깔끔한 카드 스타일.
                  </p>
                  <a href="#" className="btn btn-sm btn-outline-primary">
                    자세히
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card h-100 shadow-sm border-0">
                <div className="card-body">
                  <h5 className="card-title">Card Three</h5>
                  <p className="card-text text-secondary small">
                    row-cols로 같은 높이의 카드 그리드를 맞춥니다.
                  </p>
                  <a href="#" className="btn btn-sm btn-primary">
                    자세히
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Alert + form snippet */}
      <section className="py-5">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-lg-8">
              <div
                className="alert alert-info d-flex align-items-center"
                role="alert"
              >
                <span className="me-2">ℹ️</span>
                <div>
                  <strong>Tip:</strong> <code>npm install bootstrap</code> 후 CSS·JS를
                  import하면 React와 함께 사용할 수 있습니다.
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Contact */}
      <section id="contact" className="py-5 bg-primary text-white">
        <div className="container text-center">
          <h2 className="fw-bold mb-3">Contact</h2>
          <p className="opacity-75 mb-4 col-lg-6 mx-auto">
            이 예제는 학습용입니다. 실제 프로젝트에서는 폼 전송·API 연동을 추가하세요.
          </p>
          <button type="button" className="btn btn-light btn-lg text-primary">
            이메일 보내기
          </button>
        </div>
      </section>

       {/* Footer */}
      <footer className="py-4 bg-dark text-white-50">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-md-6 text-center text-md-start small">
              © {new Date().getFullYear()} Bootstrap Demo · React + Vite
            </div>
            {/* text-md-end - md이상 오른쪽 정렬 */}
            <div className="col-md-6 text-center text-md-end mt-2 mt-md-0">
              <a href="#" className="text-white-50 text-decoration-none me-3">
                Privacy
              </a>
              <a href="#" className="text-white-50 text-decoration-none">
                Terms
              </a>
            </div>
          </div>
        </div>
      </footer>
    </>
  )
}

export default App