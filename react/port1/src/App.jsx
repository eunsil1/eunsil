import React from 'react'
import {useState,useEffect} from 'react'
import './App.css';
import Header from './components/Header';
import About from './components/About';
import Hero from './components/Hero';
import Skills from './components/Skills';
import Projects from './components/Projects';
import Contact from './components/Contact';



function App() {
  const [isScrolled, setIsScrolled] = useState(false);
  useEffect(()=>{
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 50)
    }
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  },[]);

  return (
    <div className='App'>
      <Header isScrolled={isScrolled}/>
      <Hero/>
      <About/>
      <Skills/>
      <Projects/>
      <Contact/>
    </div>
  )
}

export default App

//Header isScrolled를 props로 전달