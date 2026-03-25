import React from 'react'
import {Routes, Route, Link} from 'react-router-dom'
import ProductList from './pages/ProductList'
import Home from './pages/Home'
import ProductDetail from './pages/ProductDetail'

function App() {
  return (
    <div>
      <nav>
        <Link to="/">홈</Link> | {" "}
        <Link to="/products">상품목록</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/products" element={<ProductList/>}/>
        <Route path="/product/:id" element={<ProductDetail/>}/>
      </Routes>
      
    </div>
  )
}

export default App

// BrowserRouter - main.jsx(vite), index.js(cdn) - 라우터 관리자 
// import { BrowserRouter } from "react-router-dom";

// <BrowserRouter>
//   <App />
// </BrowserRouter>


//Routes / Route - URL 컴포넌트 연결
//path - URL 주소
//element - 보여줄 컴포넌트
{/* <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/products" element={<ProductList/>}/>
      <Route path="/products/:id" element={<ProductDetail/>}/>
    </Routes> */}


// Link - 페이지 이동 - import {Link} -> a태그 대체
{/* <Link to="/products">상품목록</Link> */}

// useNavigate - 코드로 페이지 이동
// import { useNavigate } from "react-router-dom";

// const navigate = useNavigate();
// navigate(-1); - 뒤로가기
// navigate(1); - 앞으로 가기
// navigate("/about"); - about으로 이동

// useParams - URL 파라미터 가져오기
// import { useParams } from "react-router-dom";

// const { id } = useParams();
// /product/3 -> id=3

//useLocation - 현재의 URL 정보 가져오기
// import { useLocation } from "react-router-dom";

// const location = useLocation();

// console.log(location.pathname); // 현재 경로



// useSearchParams
// import { useSearchParams } from "react-router-dom";

// const [params, setParams] = useSearchParams();

// const page = params.get("page"); // ?page=1

// /product?page=2

// const [params, setParams] = useSearchParams();
// setParams({page:3});
// 결과 /products?page=3


// Outlet(중첩 라우팅) - 레이아웃 안에서 페이지 바뀌는 자리
// import { Outlet } from "react-router-dom";

// function Layout() {
//   return (
//     <div>
//       <h1>공통 레이아웃</h1>
//       <Outlet />
//     </div>
//   );
// }


// Navigate(리다이렉트) - 강제 이동
// import { Navigate } from "react-router-dom";

// return <Navigate to="/login" />;
// 로그인 안했을때 이동
