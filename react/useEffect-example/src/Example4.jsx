import { useState, useEffect } from 'react';

function Example4() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log('✅ [예제4] 컴포넌트 마운트됨');
    return () => console.log('🧹 [예제4] 컴포넌트 언마운트됨');
  }, []);

  useEffect(() => {
    document.title = `클릭 ${count}회`;
  }, [count]);

  return (
    <>
      <h3>예제 4: 통합 (마운트 + 값 변경 + Cleanup)</h3>
      <p>마운트 시 로그, count 변경 시 타이틀 변경, 다른 탭으로 가면 Cleanup</p>
      <div className="count-display">{count}</div>
      <button className="btn" onClick={() => setCount(count + 1)}>클릭</button>
      <div className="console-hint">💡 콘솔 + 탭 제목 확인. 다른 탭으로 이동하면 Cleanup 로그</div>
    </>
  );
}

export default Example4;
