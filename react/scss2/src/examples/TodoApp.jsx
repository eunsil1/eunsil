import React from 'react'
import styles from './TodoApp.module.scss'
import {useState} from 'react'

function TodoApp() {

  const [todos, setTodos] = useState([]);
  const [inputValue, setInputValue] = useState('');
  const [filter, setFilter] = useState('all');

  const addTodo = () => {
    if (inputValue.trim()) {
      setTodos([...todos, { id: Date.now(), text: inputValue, completed: false }]);
      setInputValue('');
    }
  };

  const toggleTodo = (id) => {
    setTodos(todos.map(todo => 
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    ));
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter(todo => todo.id !== id));
  };

  const filteredTodos = todos.filter(todo => {
    if (filter === 'active') return !todo.completed;
    if (filter === 'completed') return todo.completed;
    return true;
  });

  const stats = {
    total: todos.length,
    active: todos.filter(t => !t.completed).length,
    completed: todos.filter(t => t.completed).length
  };

  return (
    <div className={styles.todoApp}>
      <div className={styles.container}>
        <h1 className={styles.title}>★할 일 목록</h1>
        <div className={styles.inputGroup}>
          <input type="text" 
            className={styles.input}
            value={inputValue}
            onChange={(e)=>setInputValue(e.target.value)}
            onKeyPress={(e)=>e.key === 'Enter' && addTodo()}
            placeholder='새로운 할 일을 입력하세요...'
          />
          <button className={styles.addButton} onClick={addTodo}>
            추가
          </button>
        </div>

        <div className={styles.stats}>
          <span>전체: {stats.total}</span>
          <span>진행중: {stats.active}</span>
          <span>완료: {stats.completed}</span>
        </div>

        <div className={styles.filters}>
          <button 
            className={filter === 'all' ? styles.active : ''}
            onClick={() => setFilter('all')}
          >
            전체
          </button>
          <button 
            className={filter === 'active' ? styles.active : ''}
            onClick={() => setFilter('active')}
          >
            진행중
          </button>
          <button 
            className={filter === 'completed' ? styles.active : ''}
            onClick={() => setFilter('completed')}
          >
            완료
          </button>
        </div>

        <ul className={styles.todoList}>
          {filteredTodos.map(todo => (
            <li key={todo.id} className={styles.todoItem}>
              <input
                type="checkbox"
                checked={todo.completed}
                onChange={() => toggleTodo(todo.id)}
                className={styles.checkbox}
              />
              <span className={todo.completed ? styles.completed : ''}>
                {todo.text}
              </span>
              <button 
                className={styles.deleteButton}
                onClick={() => deleteTodo(todo.id)}
              >
                삭제
              </button>
            </li>
          ))}
        </ul>
        
        {filteredTodos.length === 0 && (
          <p className={styles.emptyMessage}>
            {filter === 'all' && '할 일을 추가해보세요! 🎯'}
            {filter === 'active' && '진행중인 할 일이 없습니다 ✨'}
            {filter === 'completed' && '완료된 할 일이 없습니다 📋'}
          </p>
        )}

      </div>
    </div>
  )
}

export default TodoApp