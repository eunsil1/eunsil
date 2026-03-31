import React from 'react'
import { useState, useEffect } from 'react';

function TodoForm({editing, onCreate, onUpdate, onCancelEdit}) {
  const [text, setText] = useState('');

  useEffect(() => {
    setText(editing ? editing.text : '');
  }, [editing]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const trimmed = text.trim();
    if(!trimmed) return;
    if(editing){ //수정이면 업데이트
      onUpdate({...editing, text: trimmed});
    }else{
      onCreate(trimmed); //아니면 새로 만듦
      setText('');
    }
  }
  // 사용자 목록에서 수정버튼을 클릭하면, editing 상태에 할일 개체가 담김
  // useEffect input 필드에 상태(text)를 수정할 할일의 내용으로 채워줌
  return (
    <div className='todo-form' onSubmit={handleSubmit}>
      <input type="text" 
      placeholder={editing ? '할 일 수정..' : '할 일을 입력하세요'}
      value={text}
      onChange={(e)=> setText(e.target.value)}
      autoComplete='off'
      />
      <button type='submit'>{editing ? '수정 저장' : '추가'}</button>
      {editing ? (
      <button type='button' className='ghost' onClick={onCancelEdit}>취소</button>
      ) : null}
    </div>
  )
}

export default TodoForm