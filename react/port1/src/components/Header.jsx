import React from 'react'
import {useState} from 'react'
import { FaBars, FaTimes } from "react-icons/fa";
import './Header.css';

function Header({isScrolled}) {
  const [isMenuOpen,setIsMenuOpen] = useState(false);

  const menuItems = [
    { name: '홈', href: '#home' },
    { name: '소개', href: '#about' },
    { name: '기술', href: '#skills' },
    { name: '프로젝트', href: '#projects' },
    { name: '연락처', href: '#contact' },
  ]
  
  const handleMenuClick = () => {
    setIsMenuOpen(!isMenuOpen)
  }

  const handleLinkClick = () => {
    setIsMenuOpen(false)
  }
  
  return (
    <header className={`header ${isScrolled ? 'scrolled' : ''}`}>
      <div className="container">
        <nav className="nav">
          <div className="logo">
            <a href="#home">Portfolio</a>
          </div>
          <ul className={`nav-menu ${isMenuOpen ? 'active' : ''}`}>
            {menuItems.map((item, index)=>(
              <li key={index}>
                <a href={item.href} onClick={handleLinkClick}>
                  {item.name}
                </a>
              </li>
            ))}
          </ul>
          <button className="menu-toggle" onClick={handleMenuClick}>
            {isMenuOpen ? <FaTimes/> : <FaBars/>}
          </button>
            {/* 삼항 연산자 : 열려있을때는 X아이콘(FaTimes) 닫혀있을때는(FaBars) */}
        </nav>
      </div>


    </header>
  )
}

export default Header

// handleMenuClick : 햄버거 버튼 클릭 -> 메뉴 토글
// handleLinkClick : 메뉴 링크 클릭 -> 메뉴 닫기