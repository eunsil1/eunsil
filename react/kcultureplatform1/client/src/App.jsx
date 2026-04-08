import React from 'react'
import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import Layout from './components/Layout';
import JoinPage from './pages/joinPage';
import HomePage from './pages/HomePage';
import WritePage from './pages/WritePage';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Layout/>}>
        <Route index element={<HomePage/>}/>
        <Route path='login' element={<LoginPage/>}/>
        <Route path='join' element={<JoinPage/>}/>
        <Route path='write' element={<WritePage/>}/>
      </Route>
    </Routes>
  )
}

export default App