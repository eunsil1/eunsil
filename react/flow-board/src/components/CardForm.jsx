import React from 'react'
import { useEffect } from 'react';
import { useRef } from 'react';
import { useState } from 'react';
//새카드 추가용 폼, textarea와 추가 / 취소 버튼 제공

function CardForm({onSubmit, onCancel, placeholder}) {
  const [text,setText] = useState('');
  const textareaRef = useRef(null); //마운트 focus 호출

  useEffect(()=>{
    textareaRef.current?.focus();
    //? -> 값이 있으면 실행 없으면 가만히
  },[])
  //자동 포커스

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(text);
  }

  return (
    <form className='card-form' onSubmit={handleSubmit}>
      <textarea 
        ref={textareaRef}
        value={text}
        onChange={(e)=>setText(e.target.value)}
        placeholder={placeholder}
        rows={2}
        onKeyDown={(e) => {
          if(e.key === 'Escape') oncancel();

        }}
      />
      <div className="card-form-actions">
        <button type='submit' className="btn-save" disabled={!text.trim()}>
          추가
        </button>
        <button type='button' className="btn-cancel" onClick={onCancel}>
          추가
        </button>
      </div>
    </form>
  )
}

export default CardForm