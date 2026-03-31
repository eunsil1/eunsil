import React from 'react'

function TodoList({todos, onToggle, onEdit, onDelete}) {
    if(todos.length === 0){
      return <p className='muted'>표시할 항목이 없습니다.</p>
    }
  return (
    
    <ul className='todo-list'>
      {todos.map((todo) => (
      <li key={todo.id} className={todo.done ? 'item done' : 'item'}>
        <label className="todo-label">
          <input type="checkbox" checked={todo.done} onChange={()=>onToggle(todo.id)} />
          <span className="todo-text">{todo.text}</span>
        </label>
        <div className="item-actions">
          <button className="small" onClick={()=> onEdit(todo.id)}>
            수정
          </button>
          <button className="small danger" onClick={()=> onDelete(todo.id)}>
            삭제
          </button>
        </div>
      </li>
      ))}
    </ul>
  )
}

export default TodoList