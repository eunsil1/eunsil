import React from 'react'
import { FiDownload, FiChevronDown } from "react-icons/fi";
import "./hero.css";

function Hero() {
  return (
    <section id='Home' className='hero'>

      <div className="hero-content">
        <div className="hero-greeting">
          <div className="greeting-text">Hello, I'm</div>
        </div>
        {/* name */}
        <h1 className="hero-name">Your name</h1>
        {/* title */}
        <h2 className="hero-title">Full Stack Developer</h2>

        {/* description */}
        <p className="hero-description">
          I create beautiful and functional web experiences. Passionate about
          clean code and modern design.
        </p>

        <div className="hero-buttons">
          <button className="btn-primary">
            Get In touch
          </button>
          <button
           className="btn-secondary"
           onClick={()=> window.open("/cv.pdf","_blank")}>
           <FiDownload /> Download CV
          </button>
        </div>

        <div className="scroll-indicator">
          <div className="scroll-content">
            <div className="scroll-text">Scroll down</div>
            <FiChevronDown size={24}/>
          </div>
        </div>


      </div>
    </section>
  )
}

export default Hero