import React from 'react'
import { AppProvider, useApp } from './context/AppContext';
import Toolbar from './components/Toolbar'
import ContentPanel from './components/ContentPanel';
//Context 방식 : 상태는 provider 안에 있고
//자식은 useApp()로 접근합니다.

function AppShell() {
  const {theme} = useApp();

  return (
    <div className={`app ${theme}`}>
      <header>
        <h1>Props 예제</h1>
        <span className="badge">상태는 App . Props로 전달</span>
      </header>
      <Toolbar/>
      <ContentPanel/>
    </div>
  )
}

export default function App() {
  return (
    <AppProvider>
      <AppShell/>
    </AppProvider>
  )
}

