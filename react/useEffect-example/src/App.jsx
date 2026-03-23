import { useState } from 'react';
import Example1 from './Example1';
import Example2 from './Example2';
import Example3 from './Example3';
import Example4 from './Example4';

function App() {
  const [activeTab, setActiveTab] = useState(1);

  const tabs = [
    { id: 1, label: '예제1: 마운트 1회' },
    { id: 2, label: '예제2: 값 변경 시' },
    { id: 3, label: '예제3: Cleanup' },
    { id: 4, label: '예제4: 통합' },
  ];

  return (
    <div className="app">
      <h1>🔧 useEffect 실전 예제</h1>
      <p style={{ marginBottom: 20, color: '#666' }}>콘솔(F12)을 열어서 로그를 확인하세요.</p>

      <div className="tabs">
        {tabs.map((tab) => (
          <button
            key={tab.id}
            className={`tab ${activeTab === tab.id ? 'active' : ''}`}
            onClick={() => setActiveTab(tab.id)}
          >
            {tab.label}
          </button>
        ))}
      </div>

      <div className="example-box">
        {activeTab === 1 && <Example1 />}
        {activeTab === 2 && <Example2 />}
        {activeTab === 3 && <Example3 />}
        {activeTab === 4 && <Example4 />}
      </div>
    </div>
  );
}

export default App;
