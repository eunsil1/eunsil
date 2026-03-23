import React, { useState } from 'react'
import TodoItem from './TodoItem'
import "./List.css"

function List({todos, onUpdate, onDelete}) {
  const [search, setSearch] = useState("");
  const onChangeSearch = (e) => {
    setSearch(e.target.value);
  }
// 검색 전체흐름
// input 입력 -> search 상태 변경 -> 필터함수 실행 -> 화면에 필터된 목록 출력
  const getFilteredData = () => {
    if(search ===""){
      return todos; //입력된 데이터가 없으면 모든 데이터 반환
    }
    return  todos.filter((todo) => 
      todo.content.toLowerCase().includes(search.toLowerCase())
    );
    //toLowerCase() 대소문자 무시 includes()->부분문자열 검색
  };
  const filteredTodos = getFilteredData();




  return (
    <div className='List'>
        <h4>Todo List</h4>
        <input 
        value={search}
        onChange={onChangeSearch}
        placeholder='검색어를 입력하세요'/>
        <div className="todos_wrapper">
           {filteredTodos.map((todo)=>(
             <TodoItem key={todo.id} onUpdate={onUpdate} onDelete={onDelete} {...todo} />
            ))}
            {/* () 쓰면 자동 return  */}
        </div>
    </div>
    // todos배열 - >map 실행 -> TodoItem 여러개를 생성 -> 화면출력
    // key={todo.id} 리액트가 릿틀 구분하기위한값 항상필요
    //{...todo} 객체를 props로 펼쳐서 전달
  )
}

export default List