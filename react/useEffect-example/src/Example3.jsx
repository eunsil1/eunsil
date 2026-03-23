import { useState, useEffect } from 'react';

function TimerChild() {
  useEffect(() => {
    console.log('✅ [예제3] 타이머 컴포넌트 마운트됨');
    return () => {
      console.log('🧹 [예제3] 타이머 컴포넌트 언마운트됨 (Cleanup)');
    };
  }, []);

  return <div style={{ padding: 12, background: '#e8f5e9', borderRadius: 8 }}>타이머 컴포넌트</div>;
}

function Example3() {
  const [show, setShow] = useState(true);

  return (
    <>
      <h3>예제 3: Cleanup (언마운트 시 정리)</h3>
      <p>return () =&gt; {} → 컴포넌트가 사라질 때 실행</p>
      <button className="btn" onClick={() => setShow(!show)}>
        {show ? '숨기기' : '보이기'}
      </button>
      {show && <TimerChild />}
      <div className="console-hint">💡 "숨기기" 클릭 시 콘솔에 Cleanup 로그 출력</div>
    </>
  );
}

export default Example3;
