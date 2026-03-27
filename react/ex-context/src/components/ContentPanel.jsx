import React from 'react'
import { useApp } from '../context/AppContext'

function ContentPanel() {

  const {theme, count} = useApp()

  return (
    <section className="panel">
      <p className="note">
        <strong>ContentPanel</strong>도 <code>useApp()</code>으로 동일한 값을 구독합니다.
      </p>
      <p>현재 테마 문자열: <code>{theme}</code></p>
      <p className="counter">{count}</p>
    </section>
  )
}

export default ContentPanel