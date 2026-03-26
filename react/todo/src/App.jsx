import './App.css';
import React from 'react'
import List from './components/List'
import Editor from './components/Editor';
import { useState } from 'react';
import { useRef } from 'react';

const mockData = [
  {
    id:0,
    isDone:false,
    content:"React 공부하기",
    date:new Date().getTime(),
  },

  {
    id: 1,
    isDone: false,
    content: "빨래하기",
    date: new Date().getTime(),
  },

  {
    id: 2,
    isDone: false,
    content: "노래 연습하기",
    date: new Date().getTime(),
  },
];


function App() {
  const [todos, setTodos] = useState(mockData); //mockData 초기값 입력
  const idRef = useRef(3); //새로운 todo id를 만들기 위한 값

  const onCreate = (content) => {
    const newTodo = {
      id : idRef.current++,
      isDone : false,
      content : content,
      date : new Date().getTime(),
    }
    setTodos([newTodo,...todos])
  };

  const onUpdate = (targetId) => {
    //todo state의 값들 중에
    //targetId와 일치하는 id를 갖는 todoItem의 isDone을 변경
    setTodos(
      todos.map((todo)=>
        todo.id === targetId ? {...todo, isDone:!todo.isDone} : todo
      )
    );
  }

  const onDelete = (targetId) => {
    setTodos(todos.filter((todo) => todo.id !== targetId))
  }

  return (
    <div className='App'>
      <Header/>
      <Editor onCreate={onCreate}/>
      {/* App -> Editor 로 함수 전달 */}
      <List todos={todos} onUpdate={onUpdate} onDelete={onDelete}/>
      
    </div>
  )
}

export default App