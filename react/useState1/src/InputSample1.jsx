import React, { useState } from 'react'

function InputSample1() {
  const [text,setText] = useState('');
  const onChange = (e) => {
    setText(e.target.value);
    //e.target 이벤트를 발생하는 DOM
    //e.target.value 를 조회하면 현재 input 입력한 값이 무엇인지 안다.
  } 
  const onReset = () => {
    setText("");
  }

  return (
    <div>
      <input onChange={onChange} value={text} />
      <button onClick={onReset}>초기화</button>
      <div>
        <b>값: {text}</b>
      </div>
    </div>
  )
}

export default InputSample1