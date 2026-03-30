import { useState, useRef } from 'react'
import './App.css';
import Header from './component/Header';
import TodoEditor from './component/TodoEditor';
import TodoList from './component/TodoList';
import type { Todo } from './types';

const mockTodo: Todo[] = [
  {
    id:0,
    isDone : false,
    content:"react 공부하기",
    createdDate : new Date().getTime(),
  },
    {
    id: 1,
    isDone: false,
    content: "빨래 널기",
    createdDate: new Date().getTime(),
  },
  {
    id: 2,
    isDone: false,
    content: "노래 연습하기",
    createdDate: new Date().getTime(),
  },

]



export default function App() {
  const [todo, setTodo] = useState<Todo[]>(mockTodo);
  const idRef =useRef<number>(3);

  const onCreate = (content :string) => {
    const newItem: Todo = {  
      id:idRef.current,
      content,
      isDone : false,
      createdDate: new Date().getTime(),
     }
     setTodo([newItem, ...todo]);
     idRef.current += 1;
  };

const onUpdate = (targetId: number) => {
  //targetId 는 클릭된 Todo 고유 id로 전달
  setTodo(todo.map((it: Todo)=>{
   
  return  it.id === targetId ? {...it, isDone : !it.isDone} : it

  }))
}
const onDelete = (targetId:number) =>{
  setTodo(todo.filter((it: Todo) => it.id !== targetId));
};


  return (
    <div className='App'>
        <Header />
        <TodoEditor onCreate = {onCreate}/>
        <TodoList todo={todo} onUpdate={onUpdate} onDelete={onDelete}/>
    </div>
  )
}
