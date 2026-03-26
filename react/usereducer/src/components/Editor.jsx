import React from 'react'
import './Editor.css';
import { useState } from 'react';
import { useRef } from 'react';

function Editor({onCreate}) {
  const [content, setContent] = useState("");
  const inputRef = useRef(); //커서이동

  const onChangeContent = (e) => {
    setContent(e.target.value); //input 입력 -> 상태 업데이트
  };

  const onKeydown = (e) => {
    if(e.keyCode === 13){ //Enter키를 누르면 추가 실행
      onsubmit();
    }
    //if(e.key === 'Enter')
  };

  const onsubmit = () => {
    if(content === ''){ //content 박스에 내용이 없으면
      inputRef.current.focus(); //자동으로 커서 이동
      return;
    }
    onCreate(content); //부모호출(내용)
    //Editor -> App.jsx로 데이터 전달
    setContent('');
  };

  return (
    <div className='Editor'>
      <input 
      ref={inputRef}
      onChange={onChangeContent}
      onKeyDown={onKeydown}
      value={content}
      placeholder='새로운 Todo...' />
      <button onClick={onsubmit}>추가</button>
    </div>
  )
}

export default Editor

//동작순서
//1. 내용비었는지 검사
//2. 비었으면 input focus
//3. 아니면 onCreate 실행(부모호출)
//4. 입력값 초기화

