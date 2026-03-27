import React from 'react'
import {useState} from 'react'
import Toolbar from './components/Toolbar'
import ContentPanel from './components/ContentPanel';

function App() {
  const [theme, setTheme] = useState('light');
  const [count, setCount] = useState(0);

  const toggleTheme = () => {
    setTheme((t) => (t === 'light' ? 'dark' : 'light'));
  };

  return (
    <div className={`app ${theme}`}>
      <header>
        <h1>Props 예제</h1>
        <span className="badge">상태는 App . Props로 전달</span>
      </header>
      <Toolbar
        theme={theme}
        onToggleTheme={toggleTheme}
        count={count}
        onIncrement={()=> setCount((c) => c + 1)}
      />
      <ContentPanel theme={theme} count={count} />
    </div>
  )
}

export default App
// App에서 props로 전달
// theme 현재 테마
// onToggleTheme 테마 변경
// count 현재 숫자
// onIncrement 숫자 증가함수
// Toolbar (조작담당)