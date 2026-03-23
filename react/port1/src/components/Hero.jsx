import { FaGithub, FaLinkedin, FaEnvelope } from 'react-icons/fa'
import './Hero.css'

const Hero = () => {
  return (
    <section id="home" className="hero">
      <div className="container">
        <div className="hero-content">
          <div className="hero-text">
            <h1 className="hero-title">
              안녕하세요, <br />
              <span className="gradient-text">프론트엔드 개발자</span>입니다
            </h1>
            <p className="hero-description">
              사용자 경험을 중시하며, 아름답고 기능적인 웹 애플리케이션을 만드는 것을 좋아합니다.
              최신 기술을 학습하고 적용하는 것에 열정을 가지고 있습니다.
            </p>
            <div className="hero-buttons">
              <a href="#projects" className="btn btn-primary">
                프로젝트 보기
              </a>
              <a href="#contact" className="btn btn-secondary">
                연락하기
              </a>
            </div>
            <div className="hero-social">
              <a href="https://github.com" target="_blank" rel="noopener noreferrer" aria-label="GitHub">
                <FaGithub />
              </a>
              <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer" aria-label="LinkedIn">
                <FaLinkedin />
              </a>
              <a href="mailto:your.email@example.com" aria-label="Email">
                <FaEnvelope />
              </a>
            </div>
          </div>
          <div className="hero-image">
            <div className="hero-avatar">
              <div className="avatar-circle"></div>
            </div>
          </div>
        </div>
      </div>
    </section>
  )
}

export default Hero
