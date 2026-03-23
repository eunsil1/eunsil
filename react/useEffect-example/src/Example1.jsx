import { useEffect } from 'react';

function Example1() {
  useEffect(() => {
    console.log('✅ [예제1] 환영합니다! (마운트 시 1회만 실행)');
  }, []);

  return (
    <>
      <h3>예제 1: 마운트 시 한 번만 실행</h3>
      <p>빈 배열 [] → 컴포넌트가 처음 나타날 때 1번만 실행</p>
      <p>이 탭을 선택하면 콘솔에 "환영합니다!"가 한 번 출력됩니다.</p>
      <div className="console-hint">💡 F12 → Console 탭에서 확인</div>
    </>
  );
}

export default Example1;
