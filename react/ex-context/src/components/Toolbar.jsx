import React from 'react'
import { useApp } from '../context/AppContext'

function Toolbar() {
  
  const {theme, count, toggleTheme, increment}= useApp()

  return (
    <section className="panel">
      <p className="note">
        <strong>Toolbar</strong>는 props 없이 <code>useApp()</code>만 사용합니다.
      </p>
      <button type="button" className="btn-toggle" onClick={toggleTheme}>
        테마: {theme === 'light' ? '라이트' : '다크'} (전환)
      </button>
      <button type="button" className="btn-toggle" onClick={increment} style={{ marginLeft: '0.5rem' }}>
        카운트 +1 (현재 {count})
      </button>
    </section>
  )
}

export default Toolbar