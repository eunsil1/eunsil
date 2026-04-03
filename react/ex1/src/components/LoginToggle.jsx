import { useState } from "react";

function LoginToggle() {
  const [isLogin, setIsLogin] = useState(false);

  const handleToggle = () => {
    setIsLogin((prev) => !prev);
  };

  return (
    <div>
      <div className='block'>
      <h2>3번 — 조건부 렌더링</h2>

      <button onClick={handleToggle}>
        {isLogin ? "로그아웃" : "로그인"}
      </button>

      <p>
        {isLogin ? "환영합니다" : "로그인이 필요합니다"}
      </p>
      </div>
    </div>

  );
}

export default LoginToggle;