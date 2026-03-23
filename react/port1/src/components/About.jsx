import React from 'react'
import './About.css';

function About() {
  return (
    <section id='about' className='about section'>
      <div className="container">
        <h2 className="section-title">소개</h2>
        <div className="about-content">
          <div className="about-text">
            <p>
              안녕하세요! 저는 사용자 중심의 웹 애플리케이션을 개발하는 프론트엔드 개발자입니다.
              깔끔한 코드와 직관적인 사용자 인터페이스를 만드는 것에 열정을 가지고 있습니다.
            </p>
            <p>
              최신 웹 기술에 관심이 많으며, React, Vue.js 등의 모던 프레임워크를 활용하여
              확장 가능하고 유지보수가 쉬운 애플리케이션을 구축합니다.
            </p>
            <p>
              단순히 기능을 구현하는 것을 넘어, 사용자 경험을 개선하고 비즈니스 가치를 창출하는
              개발자가 되기 위해 노력하고 있습니다.
            </p>
          </div>
          <div className="about-stat">
            <div className="stat-item">
              <div className="stat-number">50+</div>
              <div className="stat-label">프로젝트</div>
            </div>
            <div className="stat-item">
              <div className="stat-number">3+</div>
              <div className="stat-label">년 경력</div>
            </div>
            <div className="stat-item">
              <div className="stat-number">100+</div>
              <div className="stat-label">커밋</div>
            </div>
          </div>
        </div>
      </div>

    </section>
  )
}

export default About