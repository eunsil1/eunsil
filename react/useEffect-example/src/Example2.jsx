import { useState, useEffect } from 'react';

function Example2() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log('예제2: count 변경됨 →', count);
    document.title = `카운트: ${count}`;
  }, [count]);

  return (
    <>
      <h3>예제 2: 값이 바뀔 때마다 실행</h3>
      <p>[count] → count가 변경될 때만 실행. 브라우저 탭 제목이 바뀝니다.</p>
      <div className="count-display">{count}</div>
      <button className="btn" onClick={() => setCount(count + 1)}>+1</button>
      <div className="console-hint">💡 브라우저 탭 제목을 확인하세요!</div>
    </>
  );
}

export default Example2;
