import React from 'react'
import { useState } from "react";

function TodoList() {
  const [todos, setTodos] = useState([]);
  const [text, setText] = useState("");

  const handleAdd = () => {
    if (!text) return;

    const newTodo = {
      id: Date.now(),
      content: text,
    };

    setTodos((prev) => [...prev, newTodo]);
    setText("");
  };

  const handleDelete = (id) => {
    setTodos((prev) => prev.filter((todo) => todo.id !== id));
  };

  return (
    <div>
      

      <div className='block'>
        <h2>2번 — 리스트 렌더링 + 삭제</h2>
      <input
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="할 일"
      />
      <button type="button" onClick={handleAdd}>추가</button>
      

      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.content}
            <button onClick={() => handleDelete(todo.id)}>삭제</button>
          </li>
        ))}
      </ul>
      </div>
    </div>
  );
}

export default TodoList;