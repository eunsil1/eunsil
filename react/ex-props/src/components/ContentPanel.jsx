import React from 'react'

function ContentPanel({theme, count}) {
  return (
    <section className="panel">
      <p className="note">
        <strong>ContentPanel</strong>은 부모가 넘긴 props로 화면을 그립니다.
      </p>
      <p>현재 테마 문자열: <code>{theme}</code></p>
      <p className="counter">{count}</p>
    </section>
  )
}

export default ContentPanel