import { FaGithub, FaExternalLinkAlt } from 'react-icons/fa'
import './Projects.css'

const Projects = () => {
  const projects = [
    {
      title: 'E-Commerce 웹사이트',
      description: 'React와 Node.js를 활용한 풀스택 전자상거래 플랫폼. 사용자 인증, 결제 시스템, 관리자 대시보드 포함.',
      tech: ['React', 'Node.js', 'MongoDB', 'Stripe'],
      github: 'https://github.com',
      demo: 'https://example.com',
      image: 'project1'
    },
    {
      title: '실시간 채팅 애플리케이션',
      description: 'Socket.io를 사용한 실시간 메시징 앱. 그룹 채팅, 파일 공유, 읽음 확인 기능 구현.',
      tech: ['React', 'Socket.io', 'Express', 'MongoDB'],
      github: 'https://github.com',
      demo: 'https://example.com',
      image: 'project2'
    },
    {
      title: '날씨 대시보드',
      description: 'OpenWeatherMap API를 활용한 반응형 날씨 대시보드. 위치 기반 날씨 정보 제공.',
      tech: ['React', 'API', 'CSS3', 'Chart.js'],
      github: 'https://github.com',
      demo: 'https://example.com',
      image: 'project3'
    },
    {
      title: '할일 관리 앱',
      description: '드래그 앤 드롭 기능이 있는 모던한 할일 관리 애플리케이션. 로컬 스토리지 지원.',
      tech: ['React', 'TypeScript', 'Tailwind CSS'],
      github: 'https://github.com',
      demo: 'https://example.com',
      image: 'project4'
    },
  ]

  return (
    <section id="projects" className="projects section">
      <div className="container">
        <h2 className="section-title">프로젝트</h2>
        <div className="projects-grid">
          {projects.map((project, index) => (
            <div key={index} className="project-card">
              <div className="project-image">
                <div className="project-placeholder">
                  <span>{project.title}</span>
                </div>
                <div className="project-overlay">
                  <a href={project.github} target="_blank" rel="noopener noreferrer" className="project-link">
                    <FaGithub />
                  </a>
                  <a href={project.demo} target="_blank" rel="noopener noreferrer" className="project-link">
                    <FaExternalLinkAlt />
                  </a>
                </div>
              </div>
              <div className="project-content">
                <h3 className="project-title">{project.title}</h3>
                <p className="project-description">{project.description}</p>
                <div className="project-tech">
                  {project.tech.map((tech, techIndex) => (
                    <span key={techIndex} className="tech-tag">{tech}</span>
                  ))}
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}

export default Projects
