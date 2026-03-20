import React from 'react'
// 비구조화 할당 (최근 많이 쓰는 방식)
function Welcome1({name,color}) {
  return (
    <h1 style={{color}}>안녕하세요, {name}</h1>
  )
}

export default Welcome1