import React from 'react'
import { useState } from 'react'

function SingleInput() {
  const [text, setText] = useState('');

  const onChange = (e) => {
    setText(e.target.value); //입력값으로 상태 업데이트
  }

  const onReset = () => {
    setText('');
  }

  return (
    <div>
      <input onChange={onChange} value={text} placeholder='내용을 입력하세요'/>
      <button onClick={onReset}>초기화</button>
      <div>
        <b>값 : {text}</b>
      </div>
    </div>
  )
}

export default SingleInput