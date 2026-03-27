import React from 'react'
import TabMenu from './components/TabMenu'
import AccordionMenu from './components/AccordionMenu'
import Slider from './components/Slider'
import './App.css'


function App() {
  return (
    <div className='app'>
      <div className="app-container">
        <h1>리액트 탭 & 아코디언 & 슬라이드</h1>
          <TabMenu/>
          <AccordionMenu/>
          <Slider/>
      </div>
    </div>
  )
}

export default App