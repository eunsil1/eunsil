import { Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import HeroSlider from '../components/HeroSlider';
import FeatureCards from '../components/FeatureCards';

export default function Home() {
  return (
    <>
      <HeroSlider />
      <FeatureCards />
      <section className="py-5">
        <Container>
          <div className="row align-items-center g-4">
            <div className="col-lg-6">
              <h2 className="fw-bold mb-3">Boot2 데모에 오신 것을 환영합니다</h2>
              <p className="text-muted mb-4">
                상단 네비게이션, Slick 메인 슬라이드, 이미지 카드, 푸터가 포함되어 있습니다.
                아래 링크로 서비스·문의 페이지를 확인해 보세요.
              </p>
              <div className="d-flex flex-wrap gap-2">
                <Link to="/services" className="btn btn-primary">
                  서비스 소개
                </Link>
                <Link to="/contact" className="btn btn-outline-secondary">
                  문의하기
                </Link>
              </div>
            </div>
            <div className="col-lg-6">
              <img
                className="img-fluid rounded-3 shadow"
                src="https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=900&q=80"
                alt="팀 협업"
              />
            </div>
          </div>
        </Container>
      </section>
    </>
  );
}
