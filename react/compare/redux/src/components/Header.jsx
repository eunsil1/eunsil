import React from "react";
import { useSelector } from "react-redux";
// import { useAppContext } from "../context/AppContext";

export default function Header() {
  // const {user} = useAppContext();
  const user = useSelector((state) => state.user)
  //useSelector -> redux store에서 데이터를 꺼내는 훅
  //store에 있는 state 읽기 - 값이 바뀌면 컴포넌트 자동 렌더링
  return (
    <header className="header">
      <h1>⚛️ Redux 방식</h1>
      <p>
        현재 사용자: {user.name} ({user.age}세)
      </p>
    </header>
  );
}