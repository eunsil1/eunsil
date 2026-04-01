import React from 'react'
import { useState, useMemo } from 'react';
import TodoForm from '../src/components/TodoForm';
import TodoStats from '../src/components/TodoStats';
import TodoFilterBar from './components/TodoFilterBar';
import TodoList from './components/TodoList';
import { countByDone, filterTodos } from './lib/todoQueries';
import type{Todo, TodoFilter} from './types/todo';

function App() {
  const [todos, setTodos] = useState<Todo[]>([
    { id: 1, text: "TypeScript interface 연습", done: true },
    { id: 2, text: "Todo CRUD 완성하기", done: false },
    { id: 3, text: "filterTodos 타입 따라가 보기", done: false },
  ]);

  const [editing, setEditing] = useState<Todo | null>(null); //수정중인 todos
  const [filter, setFilter] = useState<TodoFilter>('all'); //현재 필터(all, active, completed)

  const visible = useMemo(()=>filterTodos(todos,filter),[todos,filter])
  const {active, completed} = useMemo(() => countByDone(todos), [todos])
 

  function nextId(list:Todo[]):number{
    if(list.length === 0) return 1; //목록이 비어있으면 1을 반환
    return Math.max(...list.map((t) => t.id)) + 1;
    //할일 객체배열에서 id값들만 모아서 새로운 배열 [1,2,3] 만듭니다.
    //여기서 최대값 3  +1(증가)한 4를 id로 부여
  }

  const handleCreate = (text:string) => { 
    setTodos((prev) => [...prev, {id: nextId(prev), text, done: false}]);
  }

  const handleUpdate = (updated:Todo) => { //수정된 Todo를 기존 리스트에서 찾아서 교체
    setTodos((prev) => prev.map((t) => t.id === updated.id ? updated : t));
    setEditing(null);
  }
  //0. 사용자가 수정 -> updated 객체 생성
  //1. handleUpdate 호출
  //2. todos 배열에서 해당 id를 찾음(t.id === updated.id)
  //3. 그 요소만 교체(updated)
  //4. 화면 갱신

  const handleToggle = (id:number) =>{
    setTodos((prev) => prev.map((t)=> (t.id === id ? {...t, done: !t.done} : t)))
  }

  const handleDelete = (id:number) => { 
    setTodos((prev) => prev.filter((t) => t.id !== id)) //삭제 대상이 수정중이면 null(취소)
    setEditing((e)=> (e?.id === id ? null : e))
  }

  const handleEdit = (id:number) => { 
    const found = todos.find((t) => t.id ===id) //수정하려는 id 찾아 found 저장
    setEditing(found ?? null) //수정할 대상 지정 - found가 있음 found 돌려줌, 없으면 null
  }
  // handleEdit -> editing 상태 변경 -> TodoForm 감지(useEffect) -> input 기존 값 자동 입력
  // -> 사용자가 수정 가능 

  return (
    <div className='app'>

      <header className="header">
        <h1>Todo · TypeScript</h1>
        <p className="lead">
          <code>Todo</code> 모델 · <code>useState&lt;Todo[]&gt;</code> ·{" "}
          <code>filterTodos</code> / <code>countByDone</code> · 컴포넌트 Props로 CRUD를 연습합니다.
        </p>
      </header>

      <main className="main">
        <section className="panel">
            <h2>{editing ? '할일 수정' : '할일 추가'}</h2>
            <TodoForm
              editing={editing}
              onCreate={handleCreate}
              onUpdate={handleUpdate}
              onCancelEdit={() => setEditing(null)}
            />
    
        </section>
          <TodoStats todos={todos}/>

          <section className='panel'>
            <h2>목록</h2>
            <TodoFilterBar
              filter={filter}
              onChange={setFilter}
              activeCount={active}
              completedCount={completed}
            />

            <TodoList
              todos={visible}
              onToggle={handleToggle}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </section>
        
      </main>
    </div>
  )
}

export default App

// 전체구조
// todos 상태 (원본 데이터) -> filterTodos 보여줄 목록 필터링 -> countByDone (개수 계산) - ui 렌더링
