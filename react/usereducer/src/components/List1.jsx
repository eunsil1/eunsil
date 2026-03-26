import React from 'react'
import TodoItem from './TodoItem'
import './List.css';

function List({todos}) {
  
  return (
    <div className='List'>
      <h4>Todo List</h4>
      <input placeholder='검색어를 입력하세요' />
      <div className="todos_wrapper">
        {todos.map((todo)=>{
          return <TodoItem key={todo.id}{...todo}/>
        })}
        {/* () 쓰면 자동 return */}
      </div>
    </div>
    //todos 배열 -> map 실행 -> TodoItem 여러개를 생성 -> 화면출력
    //key={todo.id} 리액트가 리스트를 구분하기 위한 값 항상 필요
    //{...todo} 객체를 props로 펼쳐서 전달
  )
}

export default List