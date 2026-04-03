import React from 'react'
import UseForm from './components/UseForm'
import TodoList from './components/TodoList'
import LoginToggle from './components/LoginToggle'
import ProductList from './components/ProductList'


function App() {
  return (
    <div>
      <UseForm/>
      <TodoList/>
      <LoginToggle/>
      <ProductList/>
    </div>
  )
}

export default App