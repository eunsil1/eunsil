import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import GameList from "./pages/GameList.jsx";
import {Route, Routes} from "react-router-dom";
import Ranking from "./pages/Ranking.jsx";
import Login from "./pages/Login.jsx";
import Signup from "./pages/Signup.jsx";
import MyPage from "./pages/MyPage.jsx";

function App() {
  return (
      <Routes>
          <Route path="/" element={<GameList/>}/>
          <Route path="/ranking/:gameId" element={<Ranking/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/signup" element={<Signup/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
      </Routes>
  );
}
export default App;
