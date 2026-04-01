import React, { useState } from 'react'
import './TodoList.css';
import TodoItem from './TodoItem';
import type {Todo} from '../types';

interface TodoListProps {
  todo : Todo[];
  onUpdate : (id:number) => void;
  onDelete : (id:number) => void;
}



export default function TodoList({todo, onUpdate, onDelete}: TodoListProps) {
  const [search, setSearch] = useState<string>("");
  const onChangeSearch = (e: React.ChangeEvent<HTMLInputElement>) =>{
    // 입력 이벤트 타입 - change 이벤트 객체를 안전하게 다루기 위해

    //textarea
    // (e: React.ChangeEvent<HTMLTextAreaElement>)
    //select
    // (e: React.ChangeEvent<HTMLSelectElement>)

    setSearch(e.target.value);
    console.log(e.target.value);
  }
  const getSearchResult = (): Todo[] =>{
      return search === "" ? todo : todo.filter((it) => it.content.toLowerCase().includes(search.toLowerCase()));
      
    };


  return (
    <div className='TodoList'>
       <h4>Todo LIst 🌱</h4>
       <input 
        className="searchbar" 
        placeholder="검색어를 입력하세요" 
        onChange={onChangeSearch} 
        value={search}       
        />

       <div className='list_wrapper'>
           {getSearchResult().map((it)=>(
                 <TodoItem key={it.id} {...it} onUpdate={onUpdate} onDelete={onDelete}/>
               
           ))}
       </div>
    </div>
  )
}
